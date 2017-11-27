package com.ping.util;

import static com.ping.util.Constants.*;

import java.util.concurrent.ConcurrentHashMap;

public class PingUtil {
	
	private static ConcurrentHashMap<String, String> icmpMap;
	private static ConcurrentHashMap<String, String> tcpIpMap;
	private static ConcurrentHashMap<String, String> traceRtMap;
	
	public static String getICMPLogFile(String host) {
		String file = USER_DIR + "/" + host + UNDERSCORE + ICMP + LOG;
		return file;
	}
	
	public static String getTCPIPLogFile(String host) {
		String file = USER_DIR + "/" + host + UNDERSCORE + TCPIP + LOG;
		return file;
	}
	
	public static String getTRACERTLogFile(String host) {
		String file = USER_DIR + "/" + host + UNDERSCORE + TRACERT + LOG;
		return file;
	}
	
	public static boolean isTimeout(String str) {
		if(str.matches("^.*[Tt]imeout.*$")) {
			return true;
		}
		return false;
	}
	
	public static boolean isCouldNotFindHost(String str) {
		if(str.toLowerCase().contains("could not find host")) {
			return true;
		}
		return false;
	}
	
	public static void putICMPTask(String key) {
		if(icmpMap == null) {
			icmpMap = new ConcurrentHashMap<>();
		}
		icmpMap.put(key, null);
	}
	
	public static boolean icmpTaskContains(String key) {
		return icmpMap.containsKey(key);
	}
	
	public static void removeICMPTask(String key) {
		icmpMap.remove(key);
	}
	
	public static void putTcpIpTask(String key) {
		if(tcpIpMap == null) {
			tcpIpMap = new ConcurrentHashMap<>();
		}
		tcpIpMap.put(key, null);
	}
	
	public static boolean tcpIpTaskContains(String key) {
		return tcpIpMap.containsKey(key);
	}
	
	public static void removeTcpIpTask(String key) {
		tcpIpMap.remove(key);
	}
	
	public static void putTraceRtTask(String key) {
		if(traceRtMap == null) {
			traceRtMap = new ConcurrentHashMap<>();
		}
		traceRtMap.put(key, null);
	}
	
	public static boolean traceRtTaskContains(String key) {
		return traceRtMap.containsKey(key);
	}
	
	public static void removeTraceRtTask(String key) {
		traceRtMap.remove(key);
	}
}
