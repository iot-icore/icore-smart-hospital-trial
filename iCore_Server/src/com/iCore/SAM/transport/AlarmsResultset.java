package com.iCore.SAM.transport;

/**
 * The Class AlarmsResultset.
 */
public class AlarmsResultset {

	/** The alarm id. */
	int alarmId;
	
	/** The object id. */
	Long objectId;
	
	/** The type. */
	String type = null;
	
	/** The data. */
	String data = null;
	
	/** The date. */
	long date = -1;
		
	/**
	 * Gets the alarm id.
	 *
	 * @return the alarm id
	 */
	public int getAlarmId() {
		return alarmId;
	}
	
	/**
	 * Sets the alarm id.
	 *
	 * @param id the new alarm id
	 */
	public void setAlarmId(int id) {
		this.alarmId = id;
	}
	
	/**
	 * Gets the object id.
	 *
	 * @return the object id
	 */
	public long getObjectId() {
		return objectId;
	}
	
	/**
	 * Sets the object id.
	 *
	 * @param objectId the new object id
	 */
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public long getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(long date) {
		this.date = date;
	}
	
	
	
}
