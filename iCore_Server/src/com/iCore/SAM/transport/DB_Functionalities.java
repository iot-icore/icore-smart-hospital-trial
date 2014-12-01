package com.iCore.SAM.transport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class DB_Functionalities.
 */
public class DB_Functionalities {

	/** The connect. */
	private Connection connect = null;
	
	/** The result set. */
	private ResultSet resultSet = null;

	/** The driver. */
	String driver = "com.mysql.jdbc.Driver";
	
	/** The db_details: Please update the required credentials for the database*/
	String db_details = "jdbc:mysql://localhost/****?"+ "user=*****&password=****";

	/**
	 * Query all_ alarms.
	 *
	 * @return the list of alarms
	 */
	public List<Alarms2> QueryAll_Alarms(){

		List<Alarms2> alarms = new ArrayList<Alarms2>();
		try {
			Class.forName(driver);
			connect = DriverManager.getConnection(db_details);

			String query = "SELECT * FROM alarms";
			PreparedStatement pt = connect.prepareStatement(query);
			resultSet = pt.executeQuery();

			if (resultSet.next() ) {

				do {
					Alarms2 alarm_obs = new Alarms2();

					alarm_obs.setDevice_id(resultSet.getString("device_id"));
					alarm_obs.setFired_time(resultSet.getString("fired_time"));
					alarm_obs.setGeofence_id(resultSet.getString("geofence_id"));
					alarm_obs.setGeofence_direction(resultSet.getString("geofence_direction"));			

					alarms.add(alarm_obs);
				} while (resultSet.next());
			}
			else
			{	
				Alarms2 alarm_obs = new Alarms2();
				alarm_obs.setDevice_id("0");
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
}
