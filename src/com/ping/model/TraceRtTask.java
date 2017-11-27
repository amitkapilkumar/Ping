package com.ping.model;

import static com.ping.util.Constants.SPACE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.ping.util.PingUtil;

public class TraceRtTask implements Runnable {
	private String host;
	private String command;
	public TraceRtTask(String host, String command) {
		this.host = host;
		this.command = command;
	}
	
	@Override
	public void run() {
		System.out.println("Starting Trace for : " + host);
		Process process = null;
		if(PingUtil.traceRtTaskContains(host)) {
			return;
		}
		PingUtil.traceRtTaskContains(host);
		try {
			process = Runtime.getRuntime().exec(command + SPACE + host);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		BufferedReader br  = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		try {
			StringBuilder sb = new StringBuilder();
			while( ( line = br.readLine() ) != null) {
				if(line.isEmpty()) {
					sb.append(line);
				}
				else {
					sb.append(line + "\r\n");
				}
			}
			String file = PingUtil.getTRACERTLogFile(host); 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			System.out.println(sb.toString());
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PingUtil.removeTraceRtTask(host);
	}
}
