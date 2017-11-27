package com.ping.model;

public class AppConfig {
	private long icmpDelay;
	private long tcpIpDelay;
	private long traceRouteDelay;
	private String icmpCommand;
	private String traceRouteCommand;
	private int tcpIpTimeout;
	
	public long getIcmpDelay() {
		return icmpDelay;
	}
	
	public void setIcmpDelay(long icmpDelay) {
		this.icmpDelay = icmpDelay;
	}
	
	public long getTcpIpDelay() {
		return tcpIpDelay;
	}
	
	public void setTcpIpDelay(long tcpIpDelay) {
		this.tcpIpDelay = tcpIpDelay;
	}
	
	public long getTraceRouteDelay() {
		return traceRouteDelay;
	}
	
	public void setTraceRouteDelay(long traceRouteDelay) {
		this.traceRouteDelay = traceRouteDelay;
	}
	
	public String getIcmpCommand() {
		return icmpCommand;
	}
	
	public void setIcmpCommand(String icmpCommand) {
		this.icmpCommand = icmpCommand;
	}
	
	public String getTraceRouteCommand() {
		return traceRouteCommand;
	}
	
	public void setTraceRouteCommand(String traceRouteCommand) {
		this.traceRouteCommand = traceRouteCommand;
	}

	public int getTcpIpTimeout() {
		return tcpIpTimeout;
	}

	public void setTcpIpTimeout(int tcpIpTimeout) {
		this.tcpIpTimeout = tcpIpTimeout;
	}
}