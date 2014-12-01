package com.iCore.SAM.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iCore.SAM.transport.Alarms2;
import com.iCore.SAM.transport.AlarmsResultset;
import com.iCore.SAM.transport.DB_Functionalities;

/**
 * The Class Alarms.
 */
@Path("Alarms")
public class Alarms {
	
	/** The all alarms. */
	private HashMap<String, Object> allAlarms = new HashMap<String, Object>();
	
	
	/**
	 * Instantiates new alarms.
	 */
	public Alarms() {
		
		DB_Functionalities db = new DB_Functionalities();
		List<Alarms2> alarms = new ArrayList<Alarms2>();
		alarms = db.QueryAll_Alarms();
		String type;
		int index = alarms.size();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		AlarmsResultset rs = new AlarmsResultset();
		
		if((alarms.get(index-1).getGeofence_id()!=null) && (alarms.get(index-1).getGeofence_direction() != null))	
		{
			type = "Geo-fence";
		}
		else
		{
			type = "Time-fence";		
		}
			
		
		rs.setAlarmId(Integer.parseInt(alarms.get(index-1).getGeofence_id()));
		rs.setObjectId(Long.parseLong(alarms.get(index-1).getDevice_id()));
		rs.setDate(Long.parseLong(alarms.get(0).getFired_time()));
		rs.setType(type);
		rs.setData(alarms.get(index-1).getGeofence_direction());
		
		map.put("ObjectId", rs.getObjectId());
		map.put("AlarmId", rs.getAlarmId());
		map.put("Type", rs.getType());
		map.put("Data", rs.getData());
		map.put("Date", rs.getDate());
		
		allAlarms.put(String.valueOf(rs.getAlarmId()), map);

		
	};

	/**
	 * Gets the data items,whole list of alarms
	 *
	 * @return the data items
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> getDataItems() {
		
		System.out.println("Alarms were sent");
		return allAlarms;

	}

}
