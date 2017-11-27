package com.ping.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.ping.service.ReportService;
import com.ping.util.PingUtil;

import static com.ping.util.Constants.*;

public class ICMPTask implements Runnable {

	private String host;
	private String command;
	public ICMPTask(String host, String command) {
		this.host = host;
		this.command = command;
	}
	
	@Override
	public void run() {
		System.out.println("Starting ICMP Ping for : " + host);
		Process process = null;
		if(PingUtil.icmpTaskContains(host)) {
			return;
		}
		PingUtil.putICMPTask(host);
		try {
			process = Runtime.getRuntime().exec(command + SPACE + host);
		} catch (IOException e) {
			e.printStackTrace(System.out);
			return;
		}
		BufferedReader br  = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		try {
			StringBuilder sb = new StringBuilder();
			while( ( line = br.readLine() ) != null) {
				if(line.contains("Packets: Sent")) {
					String[] tokens = line.split(",");
					if(Integer.parseInt(tokens[0].replaceAll("[\\D]", "")) != Integer.parseInt(tokens[1].replaceAll("[\\D]", ""))) {
						System.out.println(ReportService.getReport(host));
					}
				}
				if(line.isEmpty()) {
					sb.append(line);
				}
				else {
					sb.append(line + "\r\n");
				}
			}
			String file = PingUtil.getICMPLogFile(host); 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			System.out.println(sb.toString());
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		PingUtil.removeICMPTask(host);
	}
}