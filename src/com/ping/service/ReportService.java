package com.ping.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ping.util.PingUtil;

public class ReportService {
	public static String getReport(String host) throws IOException {
		String icmpStr = fetchLogContent(PingUtil.getICMPLogFile(host));
		String tcpStr = fetchLogContent(PingUtil.getTCPIPLogFile(host));
		String traceRtStr = fetchLogContent(PingUtil.getTRACERTLogFile(host));
		
		return constructJson(host, icmpStr.toString(), tcpStr.toString(), traceRtStr.toString());
	}
	
	private static String fetchLogContent(String file) throws IOException {
		File log = new File(file);
		StringBuilder sb = new StringBuilder();
		String line = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(log)));
		while( (line=br.readLine() ) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}
	
	private static String constructJson(String host, String icmpStr, String tcpStr, String traceRtStr) {
		return "{" 
		+ "\"host\": \""+ host +"\","
		+ "\"icmp_ping\": \""+ icmpStr +"\","
		+ "\"tcp_ping\": \""+ tcpStr +"\","
		+ "\"trace_ping\": \""+ traceRtStr +"\""
		+ "}";
	}
}