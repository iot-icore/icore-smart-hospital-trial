package com.iCore.SAM.rest.resource;

import iCore.CVOTemplates.Trace_CVOT;
import iCore.Main.iCore_EntryPoint;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * The Class Trace:Give the coordinates of an Object
 * 
 * The path is rest/Trace/{ObjectId}
 * ObjectId should be a integer of the database key 
 */
@Path("Trace")
public class Trace {
	
	/**
	 * Instantiates a new trace.
	 */
	public Trace() {};

	/**
	 * Gets the special object by ObjectId 
	 *
	 * @param uriInfo the uri info
	 * @return the data item
	 */
	@GET
	@Path("{key}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Map<String,Object> getDataItem(@Context UriInfo uriInfo) {
		
		HashMap<String, Object> allalarms = new HashMap<String, Object>();

		String path = uriInfo.getPath();
		String[] parts = path.split("/");
		
		iCore_EntryPoint iCore_FW = new iCore_EntryPoint();
		iCore_FW.Trial_iCore(parts[0].concat(" ").concat(parts[1]));

		Trace_CVOT trace =  new Trace_CVOT();
		allalarms = trace.getAlarms();
		 
		System.out.println("Alarms of Object "+parts[1]+" were sent");
	
		return allalarms;
	}

}
