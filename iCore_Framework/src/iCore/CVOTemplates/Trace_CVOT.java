package iCore.CVOTemplates;

import iCore.MQTT.Publish_MQTT;
import iCore.RWK.DB_Functionalities2;
import iCore.RWK.Alarms;
import iCore.RWK.MedicalDevices;
import it.trilogis.mepi.bus.dto.requests.AddAssetsToGeoFenceRequestDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.gson.Gson;


/**
 * The Class Trace_CVOT.
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class Trace_CVOT implements CVO_Template {

	/** The all alarms. */
	static private HashMap<String, Object> allAlarms = new HashMap<String, Object>();

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template#getName()
	 */
	@Override
	public String getName() {
		String cvo_name = "Trace";
		return cvo_name;
	}


	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template#execute(java.lang.String)
	 */
	@Override
	public HashMap<String, Object> execute(String VO) {

		List<Alarms> alarms = new ArrayList<Alarms>();

		DB_Functionalities2 db = new DB_Functionalities2();
		List<MedicalDevices> query_geofenceID =  new ArrayList<MedicalDevices>();
		query_geofenceID =	db.Query(VO);
		String geofenceID = query_geofenceID.get(0).geGeoFence_perimeter();
		//		String geofenceID = Long.toString(RxEco);

		try {
			System.out.println(VO);				
			callMQTTAddAssetToGeofence(getAddAssetsToGeoFenceRequestDTO(Long.parseLong(geofenceID), Long.parseLong(VO)));
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		alarms = db.Query_Alarms(VO);

		allAlarms.put("ObjectId", alarms.get(0).getDevice_id());
		allAlarms.put("AlarmId", alarms.get(0).getGeofence_id());
		allAlarms.put("Type", "Geo-fence");
		allAlarms.put("Data", alarms.get(0).getGeofence_direction());

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String fired_time = df.format(Long.parseLong(alarms.get(0).getFired_time()));

		allAlarms.put("Date", fired_time);					

		return allAlarms; 			
	}

	/**
	 * Gets the adds the assets to geo fence request dto.
	 *
	 * @param geoFenceID the geo fence id
	 * @param assetID the asset id
	 * @return the adds the assets to geo fence request dto
	 */
	public static AddAssetsToGeoFenceRequestDTO getAddAssetsToGeoFenceRequestDTO(Long geoFenceID, Long assetID) {
		if (null == assetID || assetID < 0L || null == geoFenceID || geoFenceID < 0L) {
			System.err.println("Input data is not valid");
			return null;
		}
		AddAssetsToGeoFenceRequestDTO dto = new AddAssetsToGeoFenceRequestDTO();
		dto.getAssets().add(assetID);
		dto.setGeofenceId(geoFenceID);
		return dto;
	}

	/**
	 * Call mqtt add asset to geofence.
	 *
	 * @param addAssetsToGeoFenceRequestDTO the add assets to geo fence request dto
	 */
	public static void callMQTTAddAssetToGeofence(AddAssetsToGeoFenceRequestDTO addAssetsToGeoFenceRequestDTO) {
		// TODO call the MQTT Topic
		try {
			Publish_MQTT mqtt = new Publish_MQTT();
			Gson gson = new Gson();
			String json = gson.toJson(addAssetsToGeoFenceRequestDTO);
			System.out.println(json);
			mqtt.Publish("tri_test/addGeoFenceAssets",json);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the alarms.
	 *
	 * @return the alarms
	 */
	public HashMap<String, Object> getAlarms(){		
		return Trace_CVOT.allAlarms;

	}


}
