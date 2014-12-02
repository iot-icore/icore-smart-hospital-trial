package iCore.RWK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import iCore.RWK.MedicalDevices;

/**
 * The Class DB_Functionalities2: Handles all the query and updates to the database
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class DB_Functionalities2 {

	/** The connect. */
	private Connection connect = null;
	
	/** The statement. */
	private Statement statement = null;
	
	/** The prepared statement. */
	private PreparedStatement preparedStatement = null;
	
	/** The result set. */
	private ResultSet resultSet = null;

	/** The driver. */
	String driver = "com.mysql.jdbc.Driver";
	
	/** The db_details. */
	String db_details = "jdbc:mysql://localhost/***?"+ "user=***&password=****"; //Please update the Database connection credentials here


	/**
	 * Update_ position.
	 *
	 * @param id the id
	 * @param position_x the position_x
	 * @param position_y the position_y
	 */
	public void Update_Position(String id, String position_x, String position_y){

		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(db_details);
			preparedStatement = connect.prepareStatement("UPDATE medicaldevices SET position_x =?, position_y =?  WHERE medicaldevices.deviceAddress = ?");
			preparedStatement.setString(1,position_x);
			preparedStatement.setString(2, position_y);
			preparedStatement.setString(3, id);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("Database issue:" +e.getMessage());
		} catch (SQLException e) {			
			System.out.println("Database issue:" +e.getMessage());
		}		
	}

	/**
	 * Query.
	 *
	 * @param address the address
	 * @return the medical device with the particular device address
	 */
	public List<MedicalDevices> Query(String address){

		List<MedicalDevices> med_obs = new ArrayList<MedicalDevices>();

		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(db_details);

			statement = connect.createStatement();
			String query = "SELECT * FROM medicaldevices Where deviceAddress = ?";
			PreparedStatement pt = connect.prepareStatement(query);
			pt.setString(1,address);
			resultSet = pt.executeQuery();

			if (resultSet.next() ) 
			{
				do {
					MedicalDevices med_dev = new MedicalDevices();

					med_dev.setDeviceAddress(resultSet.getString("deviceAddress"));
					med_dev.setDeviceName(resultSet.getString("deviceName"));
					med_dev.setDeviceCategory(resultSet.getString("deviceCategory"));
					med_dev.setPosition_x(resultSet.getString("position_x"));
					med_dev.setPosition_y(resultSet.getString("position_y"));
					med_dev.setPower_state(resultSet.getString("power_state"));
					med_dev.setGeofence_perimeter(resultSet.getString("geofence_perimeter"));
					med_obs.add(med_dev);

				} while (resultSet.next());
			}

			else {
				MedicalDevices dev = new MedicalDevices();

				dev.setDeviceAddress(address);
				dev.setDeviceName("0");
				dev.setDeviceCategory("0");
				dev.setPosition_x("0");
				dev.setPosition_y("0");
				dev.setPower_state("on");
				dev.setGeofence_perimeter("0");
				med_obs.add(0, dev);					
			}			

		} 
		catch (ClassNotFoundException e) {
			System.out.println("Database issue:" +e.getMessage());
		}
		catch (SQLException e) {			 
			System.out.println("Database issue:" +e.getMessage());
		}
		return med_obs;
	}

	/**
	 * Query all.
	 *
	 * @return the list
	 */
	public List<MedicalDevices> QueryAll(){

		List<MedicalDevices> med_obs = null;
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(db_details);
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from medicaldevices");
			med_obs = writeResultSet(resultSet);

		} catch (ClassNotFoundException e) {
			System.out.println("Database issue:" +e.getMessage());
		} catch (SQLException e) {
			System.out.println("Database issue:" +e.getMessage());
		}
		return med_obs;
	}



	/**
	 * Update_ alarms.
	 *
	 * @param id the id
	 * @param geofence_id the geofence_id
	 * @param fired_time the fired_time
	 * @param geofence_direction the geofence_direction
	 */
	public void Update_Alarms(String id, String geofence_id, String fired_time,String geofence_direction) {
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(db_details);
			preparedStatement = connect.prepareStatement("INSERT INTO alarms (device_id, geofence_id,fired_time, geofence_direction) values(?,?,?,?)");
			preparedStatement.setString(1, id);
			preparedStatement.setString(2,geofence_id);
			preparedStatement.setString(3, fired_time);
			preparedStatement.setString(4, geofence_direction);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {			
			System.out.println("Database issue:" +e.getMessage());
		}		
	}

	/**
	 * Query_ alarms.
	 *
	 * @param address the address
	 * @return the list
	 */
	public List<Alarms> Query_Alarms(String address) {	

		List<Alarms> alarms = new ArrayList<Alarms>();
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(db_details);

			String query = "SELECT * FROM alarms WHERE device_id = ?";
			PreparedStatement pt = connect.prepareStatement(query);
			pt.setString(1,address);
			resultSet = pt.executeQuery();

			if (resultSet.next() ) {

				do {
					Alarms alarm_obs = new Alarms();

					alarm_obs.setDevice_id(resultSet.getString("device_id"));
					alarm_obs.setFired_time(resultSet.getString("fired_time"));
					alarm_obs.setGeofence_id(resultSet.getString("geofence_id"));
					alarm_obs.setGeofence_direction(resultSet.getString("geofence_direction"));			

					alarms.add(alarm_obs);
					System.out.println("success");
				} while (resultSet.next());
			}
			else
			{			

				Alarms alarm_obs = new Alarms();
				alarm_obs.setDevice_id(address);
				alarm_obs.setFired_time("0");
				alarm_obs.setGeofence_id("0");
				alarm_obs.setGeofence_direction("0");			

				alarms.add(alarm_obs);
				System.out.println("no data");
			}




		} catch (ClassNotFoundException e) {		
			System.out.println("Database issue:" +e.getMessage());
		} catch (SQLException e) {	
			System.out.println("Database issue:" +e.getMessage());
		}
		return alarms;
	}
	
	/**
	 * Write result set.
	 *
	 * @param resultSet the result set
	 * @return the list
	 * @throws SQLException the SQL exception
	 */
	private List<MedicalDevices> writeResultSet(ResultSet resultSet) throws SQLException {

		List<MedicalDevices> devices = new ArrayList<MedicalDevices>();

		while (resultSet.next())
		{
			MedicalDevices med_obs = new MedicalDevices();

			med_obs.setDeviceAddress(resultSet.getString("deviceAddress"));
			med_obs.setDeviceName(resultSet.getString("deviceName"));
			med_obs.setDeviceCategory(resultSet.getString("deviceCategory"));
			med_obs.setPosition_x(resultSet.getString("position_x"));
			med_obs.setPosition_y(resultSet.getString("position_y"));
			med_obs.setPower_state(resultSet.getString("power_state"));
			devices.add(med_obs);
		}
		return devices;
	}
	
	/**
	 * Update_ maintenance.
	 *
	 * @param id the id
	 * @param date the date
	 */
	public void Update_Maintenance(String id, Long date) {
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(db_details);
			preparedStatement = connect.prepareStatement("UPDATE medicaldevices SET maintenance_date =?  WHERE medicaldevices.deviceAddress = ?");
			
			String main_date = Long.toString(date);
			
			preparedStatement.setString(1,main_date);
			preparedStatement.setString(2, id);
			preparedStatement.executeUpdate();
			System.out.println("Database Updated");
		} catch (SQLException e) {			
			System.out.println("Database issue:" +e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
}
