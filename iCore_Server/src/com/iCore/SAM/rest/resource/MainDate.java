package com.iCore.SAM.rest.resource;

import iCore.RWK.DB_Functionalities2;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * The Class MainDate.
 */
@Path("MainDate")
public class MainDate {

	/**
	 * Instantiates a new main date.
	 */
	public MainDate() {

	}

	/**
	 * Save new date.
	 *
	 * @param id the id
	 * @param date the date
	 */
	@POST
	public void saveNewDate(@HeaderParam("id") long id, @HeaderParam("date") long date) {

		System.out.println("ObjectId: "+id);
		System.out.println("Date: "+date);//new Date(date));

		DB_Functionalities2 db = new DB_Functionalities2();
		db.Update_Maintenance(Long.toString(id), date);
	}

}
