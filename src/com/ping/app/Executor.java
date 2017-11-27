package com.ping.app;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ping.exception.InvalidHostException;
import com.ping.loaders.ConfigLoader;
import com.ping.model.AppConfig;
import com.ping.model.Host;
import com.ping.model.ICMPTask;
import com.ping.model.TcpIPTask;
import com.ping.model.TraceRtTask;

public class Executor {
	public static void main(String[] args) throws IOException, InvalidHostException, InterruptedException {
		AppConfig config = ConfigLoader.loadConfig();
		List<Host> hosts = ConfigLoader.getHosts();
		
		ScheduledExecutorService ses =  Executors.newScheduledThreadPool(6);
		for(Host host : hosts) {
			Runnable command = new ICMPTask(host.getUrl(), config.getIcmpCommand());
			ses.scheduleAtFixedRate(command, 0, config.getIcmpDelay(), TimeUnit.SECONDS);
			
			command = new TcpIPTask(host.getUrl(), config.getTcpIpTimeout());
			ses.scheduleAtFixedRate(command, 0, config.getTcpIpDelay(), TimeUnit.SECONDS);
			
			command = new TraceRtTask(host.getUrl(), config.getTraceRouteCommand());
			ses.scheduleAtFixedRate(command, 0, config.getTraceRouteDelay(), TimeUnit.SECONDS);
		}
		ses.shutdown();
	}
}