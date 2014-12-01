package com.iCore.SAM.rest.resource;

import iCore.RWK.DB_Functionalities2;
import iCore.RWK.MedicalDevices;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iCore.SAM.transport.DataResultset;

/**
 * The Class ObjectListResource: Give a Map of all Objects in the database 
 * 
 * all attributes which should be included can be seen in Dataresutset.java
 */
@Path("ObjectList")
public class ObjectListResource {

	/** The m map. */
	private Map<String, Object> mMap = new HashMap<String, Object>();


	/**
	 * Instantiates a new object list resource.
	 */
	public ObjectListResource() {

		DB_Functionalities2 query_DB = new DB_Functionalities2();
		List<MedicalDevices> devices = new ArrayList<MedicalDevices>();
		devices = query_DB.QueryAll();

		for (int i = 1; i < devices.size(); i++) {

			Map<String, Object> map = new HashMap<String, Object>();

			DataResultset rs = new DataResultset();		

			MedicalDevices current_device = devices.get(i);
			String temp = "Network";
			if(!temp.equals(current_device.getDeviceCategory()))
			{
				rs.setObjectId(Long.parseLong(current_device.getDeviceAddress()));
				rs.setObjectName(current_device.getDeviceName());
				rs.setObjectCathegory(current_device.getDeviceCategory());
				rs.setMaintainenceDate(new Date().getTime());

				map.put("ObjectId", rs.getObjectId());
				map.put("ObjectName", rs.getObjectName());
				map.put("ObjectCat", rs.getObjectCathegory());
				map.put("MainDate", rs.getMaintainenceDate());

				mMap.put(Long.toString(rs.getObjectId()), map);
			}

		}
		System.out.println("MAP created");

	};


	/**
	 * Gets the the whole list of objects
	 *
	 * @return the data items
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getDataItems() {

		return mMap;

	};

	/**
	 * Gets the data item, special object by ObjectId 
	 *
	 * @param key the key
	 * @return the data item
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Path("{key}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getDataItem(@PathParam("key") String key) {

		return (Map<String, Object>) mMap.get(key);
	};

}
