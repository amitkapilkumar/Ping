package com.ping.model; 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import com.ping.service.ReportService;
import com.ping.util.PingUtil;

public class TcpIPTask implements Runnable {

	private String host;
	private int timeout;
	public TcpIPTask(String host, int timeout) {
		this.host = host;
		this.timeout = timeout;
	}
	
	@Override
	public void run() {
		System.out.println("Starting TcpIp Ping for : " + host);
		if(PingUtil.tcpIpTaskContains(host)) {
			return;
		}
		PingUtil.putTcpIpTask(host);
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(mapToHttpURL(host)).openConnection();
			con.setConnectTimeout(timeout);
			con.connect();
			BufferedReader br  = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			StringBuilder sb = new StringBuilder();
			while( ( line=br.readLine() ) != null) {
				if(line.isEmpty()) {
					sb.append(line);
				}
				else {
					sb.append(line + "\r\n");
				}
			}
			String file = PingUtil.getTCPIPLogFile(host); 
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			System.out.println(sb.toString());
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			br.close();
		}
		catch(SocketTimeoutException | UnknownHostException e) {
			try {
				System.out.println(ReportService.getReport(host));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		PingUtil.removeTcpIpTask(host);
	}
	
	private String mapToHttpURL(String url) {
		if(url.indexOf("http") == -1) {
			return "http://" + url;
		}
		return url;
	}
}