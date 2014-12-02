package iCore.RWK;

import java.sql.Date;

/**
 * The Class MedicalDevices - Represents all the information about the medical devices stored in the database (RWK overall model)
 * 
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class MedicalDevices {

	/** The device address. */
	private String deviceAddress;
	
	/** The device name. */
	private String deviceName;
	
	/** The device category. */
	private String deviceCategory;
	
	/** The prediction model. */
	private String predictionModel;
	
	/** The prediction accuracy. */
	private String predictionAccuracy;
	
	/** The position_x. */
	private String position_x;
	
	/** The position_y. */
	private String position_y;
	
	/** The maintenance_date. */
	private Date maintenance_date;
	
	/** The power_state. */
	private String power_state;
	
	/** The geofence_perimter. */
	private String geofence_perimter;

	/**
	 * Gets the device address.
	 *
	 * @return the device address
	 */
	public String getDeviceAddress() {
		return deviceAddress;
	}

	/**
	 * Sets the device address.
	 *
	 * @param deviceAddress the new device address
	 */
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}

	/**
	 * Gets the device name.
	 *
	 * @return the device name
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * Sets the device name.
	 *
	 * @param deviceName the new device name
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * Gets the device category.
	 *
	 * @return the device category
	 */
	public String getDeviceCategory() {
		return deviceCategory;
	}

	/**
	 * Sets the device category.
	 *
	 * @param deviceCategory the new device category
	 */
	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	/**
	 * Gets the prediction model.
	 *
	 * @return the prediction model
	 */
	public String getPredictionModel() {
		return predictionModel;
	}

	/**
	 * Sets the prediction model.
	 *
	 * @param predictionModel the new prediction model
	 */
	public void setPredictionModel(String predictionModel) {
		this.predictionModel = predictionModel;
	}

	/**
	 * Gets the prediction accuracy.
	 *
	 * @return the prediction accuracy
	 */
	public String getPredictionAccuracy() {
		return predictionAccuracy;
	}

	/**
	 * Sets the prediction accuracy.
	 *
	 * @param predictionAccuracy the new prediction accuracy
	 */
	public void setPredictionAccuracy(String predictionAccuracy) {
		this.predictionAccuracy = predictionAccuracy;
	}

	/**
	 * Gets the position_x.
	 *
	 * @return the position_x
	 */
	public String getPosition_x() {
		return position_x;
	}

	/**
	 * Sets the position_x.
	 *
	 * @param position_x the new position_x
	 */
	public void setPosition_x(String position_x) {
		this.position_x = position_x;
	}

	/**
	 * Gets the position_y.
	 *
	 * @return the position_y
	 */
	public String getPosition_y() {
		return position_y;
	}

	/**
	 * Sets the position_y.
	 *
	 * @param position_y the new position_y
	 */
	public void setPosition_y(String position_y) {
		this.position_y = position_y;
	}

	/**
	 * Gets the maintenance_date.
	 *
	 * @return the maintenance_date
	 */
	public Date getMaintenance_date() {
		return maintenance_date;
	}

	/**
	 * Sets the maintenance_date.
	 *
	 * @param maintenance_date the new maintenance_date
	 */
	public void setMaintenance_date(Date maintenance_date) {
		this.maintenance_date = maintenance_date;
	}

	/**
	 * Gets the power_state.
	 *
	 * @return the power_state
	 */
	public String getPower_state() {
		return power_state;
	}

	/**
	 * Sets the power_state.
	 *
	 * @param power_state the new power_state
	 */
	public void setPower_state(String power_state) {
		this.power_state = power_state;
	}

	/**
	 * Ge geo fence_perimeter.
	 *
	 * @return the string
	 */
	public String geGeoFence_perimeter() {
		return geofence_perimter;
	}

	/**
	 * Sets the geofence_perimeter.
	 *
	 * @param geofence_perimeter the new geofence_perimeter
	 */
	public void setGeofence_perimeter(String geofence_perimeter) {
		this.geofence_perimter = geofence_perimeter;
	}


} 