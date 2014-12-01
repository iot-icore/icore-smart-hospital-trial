package com.iCore.SAM.rest;

import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;


/**
 * The Class RESTServer
 * 
 *  @author: CNET
 *  @version: 1.0
 *  @since: 01.11.2014 
 */
public class RESTServer {
		
	/** The server. */
	private HttpServer server;
	
	/**
	 * Instantiates a new REST server.
	 *
	 * @param server the server
	 */
	public RESTServer(HttpServer server) {
		this.server = server;		
	}
		
	/**
	 * Start.
	 */
	private void start(){
		server.start();
	}
	
	/**
	 * The main method: Add the desired url to be used as the server address
	 *
	 * @param args the IP address to be configured as the server
	 */
	public static void main(String[] args){
		
		try {			
//			
//			if (args.length > 0) {
			
//			String localUrl = args[0];
			String localUrl = "http://localhost:8182/rest";
			HttpServer localServer = HttpServerFactory.create(localUrl);
			RESTServer restServer = new RESTServer(localServer);
			restServer.start();
//			}
//			else {
//				System.out.println("Missing command line parameter!");
//			}
			
		} catch (IllegalArgumentException e){
			e.printStackTrace();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}	
}