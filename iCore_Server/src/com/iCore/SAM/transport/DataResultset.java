package com.iCore.SAM.transport;


import java.util.Date;

/**
 * The Class DataResultset: Class to add hold the object details
 */
public class DataResultset {

	/** The object id. */
	long objectId = -1;
	
	/** The object name. */
	String objectName = null;
	
	/** The object category. */
	String objectCathegory = null;
	
	/** The maintenance status. */
	String maintainenceStatus = null;
	
	/** The maintenance date. */
	long maintainenceDate = -1;
	
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
	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * Gets the object name.
	 *
	 * @return the object name
	 */
	public String getObjectName() {
		return objectName;
	}
	
	/**
	 * Sets the object name.
	 *
	 * @param objectName the new object name
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * Gets the object cathegory.
	 *
	 * @return the object cathegory
	 */
	public String getObjectCathegory() {
		return objectCathegory;
	}
	
	/**
	 * Sets the object cathegory.
	 *
	 * @param objectCathegory the new object cathegory
	 */
	public void setObjectCathegory(String objectCathegory) {
		this.objectCathegory = objectCathegory;
	}
	
	/**
	 * Gets the maintainence date.
	 *
	 * @return the maintainence date
	 */
	public long getMaintainenceDate() {
		return maintainenceDate;
	}
	
	/**
	 * Gets the maintainence status.
	 *
	 * @return the maintainence status
	 */
	public String getMaintainenceStatus() {
		return maintainenceStatus;
	}
	
	/**
	 * Sets the maintainence status.
	 *
	 * @param maintainenceStatus the new maintainence status
	 */
	public void setMaintainenceStatus(String maintainenceStatus) {
		this.maintainenceStatus = maintainenceStatus;
	}
	
	/**
	 * Sets the maintainence date.
	 *
	 * @param maintainenceDate the new maintainence date
	 */
	public void setMaintainenceDate(long maintainenceDate) {
		
		
		
		
//		Log.i("", "getTime: "+ maintainenceDate);
//		Log.i("","now: "+new Date().getTime());	
		if (maintainenceDate != -1) {
			
			if (maintainenceDate < new Date().getTime()) {
				maintainenceStatus = "out of service";
			}
			else {
				maintainenceStatus = "in service";
			}
		} 
		
		this.maintainenceDate = maintainenceDate;
	}
	
	
	
}
