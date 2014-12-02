package iCore.MQTT;

import iCore.RWK.DB_Functionalities2;
import it.trilogis.mepi.bus.dto.GeoFenceEventDTO;
import it.trilogis.mepi.bus.dto.requests.UpdateAssetPositionDTO;

import com.google.gson.Gson;

/**
 * The Class PayloadProperties defines how the incoming MQTT payload is handled
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014  
 */
public class PayloadProperties {

	/** The payload. */
	private String payload;
	
	/**
	 * Gets the payload.
	 *
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 * Update positions in the database
	 *
	 * @param payload the MQTT payload
	 */
	public void updateDB_positions(String payload) {
		Gson gson = new Gson();
	
		UpdateAssetPositionDTO dto = gson.fromJson(payload, UpdateAssetPositionDTO.class);
		
		double x = dto.getPosition().getX();
		double y = dto.getPosition().getY();
		long id = dto.getAssetId();
		
		DB_Functionalities2 update = new DB_Functionalities2();
		update.Update_Position(Long.toString(id),Double.toString(x), Double.toString(y));
		
		System.out.println("Database update successful");
		
	}
	
	/**
	 * Update database with the alarms (time-fence and geo-fence).
	 *
	 * @param payload the payload
	 */
	public void updateDB_alarms(String payload) {
		Gson gson = new Gson();
	
		GeoFenceEventDTO dto = gson.fromJson(payload, GeoFenceEventDTO.class);
		Long id = dto.getAssetId();
		Long fired_time = dto.getEventFiredTime();
		Long geofence_id = dto.getGeofenceId();
		String geofence_direction = dto.getGeoFenceDirection();		
	
		
		DB_Functionalities2 update = new DB_Functionalities2();
		update.Update_Alarms(Long.toString(id),Long.toString(geofence_id),Long.toString(fired_time),geofence_direction);
		
		System.out.println("Database update successful");
	}

}
