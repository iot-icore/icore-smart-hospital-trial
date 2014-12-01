package com.iCore.SAM.rest.resource;

import iCore.CVOTemplates.Locate_CVOT;
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
 * The Class Locate: Give the coordinates of an Object
 * 
 * @param path is rest/Locate/{ObjectId}
 * @param ObjectId integer of the database key 
 */
@Path("Locate")
public class Locate {
	
	/** The coord. */
	static HashMap<String, Object> coord = new HashMap<String, Object>();

	/**
	 * Instantiates a new locate.
	 */
	public Locate() {};

	//Get a special object by ObjectId 
	/**
	 * Gets the data item.
	 *
	 * @param uriInfo the uri info
	 * @return the data item
	 */
	@GET
	@Path("{key}")
	@Produces(MediaType.APPLICATION_JSON)

	public Map<String,Object> getDataItem(@Context UriInfo uriInfo) {
		
		System.out.println(uriInfo.getPath());
		
		String path = uriInfo.getPath();
		String[] parts = path.split("/");
		
		iCore_EntryPoint iCore_FW = new iCore_EntryPoint();
		iCore_FW.Trial_iCore(parts[0].concat(" ").concat(parts[1]));
		
		Locate_CVOT locate =  new Locate_CVOT();
		coord = locate.getCoords();
		 
		System.out.println("Coordinates of Object "+parts[1]+" were sent");
	
		return coord;
	}

	/**
	 * Gets the data items.
	 *
	 * @return the data items
	 */
	@GET
	@Path("Test")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDataItems() {
		
		System.out.println("Coordinates were sent");
		return coord;

	}


}
