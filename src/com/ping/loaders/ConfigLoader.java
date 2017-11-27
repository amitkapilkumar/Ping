package com.ping.loaders;

import static com.ping.util.Constants.CONFIG_FILENAME;
import static com.ping.util.Constants.ICMP_COMMAND;
import static com.ping.util.Constants.ICMP_DELAY;
import static com.ping.util.Constants.TCP_IP_DELAY;
import static com.ping.util.Constants.TRACE_ROUTE_COMMAND;
import static com.ping.util.Constants.TRACE_ROUTE_DELAY;
import static com.ping.util.Constants.HOSTS;
import static com.ping.util.Constants.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ping.exception.InvalidHostException;
import com.ping.model.AppConfig;
import com.ping.model.Host;

public class ConfigLoader {
	private static AppConfig config;
	private static List<Host> hosts;
	
	public static AppConfig loadConfig() throws IOException {
		if(config == null) {
			config = new AppConfig();
			InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILENAME);
			Properties prop = new Properties();
			prop.load(is);
			config.setIcmpDelay(Long.parseLong(prop.getProperty(ICMP_DELAY)));
			config.setTcpIpDelay(Long.parseLong(prop.getProperty(TCP_IP_DELAY)));
			config.setTraceRouteDelay(Long.parseLong(prop.getProperty(TRACE_ROUTE_DELAY)));
			config.setIcmpCommand(prop.getProperty(ICMP_COMMAND));
			config.setTraceRouteCommand(prop.getProperty(TRACE_ROUTE_COMMAND));
			config.setTcpIpTimeout(Integer.parseInt(prop.getProperty(TCP_IP_TIMEOUT)));
		}
		return config;
	}
	
	public static List<Host> getHosts() throws IOException, InvalidHostException {
		if(hosts == null) {
			hosts = new ArrayList<>();
			InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(HOSTS_FILENAME);
			Properties prop = new Properties();
			prop.load(is);
			String[] hostsToken = prop.getProperty(HOSTS).split(DELIMITER);
			for(String host : hostsToken) {
				if(host == null || host.trim().isEmpty()) {
					throw new InvalidHostException("Invalid host url");
				}
				hosts.add(new Host(host.trim()));
			}
		}
		return hosts;
	}
}