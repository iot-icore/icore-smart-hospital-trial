package iCore.CVOTemplates;

import iCore.Main.LatLonPoint;
import java.util.List;


/**
 * The Class Forecast_CVOT: Sample implementation of the WEKA Timeseries forecaster trained with sample data generated as per the test-bed settings.
 * 
 * Input-  Represents data including the movement patterns across rooms. An additional step is required to perform the room level classification 
 * Output- Based on the predicted locaiton values, a further mapping to the room level information is done
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class Forecast_CVOT implements CVO_Template2 {


	@Override
	public String getName() {
		String cvo_name = "Forecast";
		return cvo_name;
	}


	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template2#execute()
	 */
	@Override
	public void execute() {

		LatLonPoint S1 = new LatLonPoint(45.890741004756954, 11.029031597372988, 0.5d), //
				S2 = new LatLonPoint(45.890803513493466, 11.029064767859778, 0.5d), //
				S3 = new LatLonPoint(45.89083654969529, 11.029090222787572, 0.5d), //
				S4 = new LatLonPoint(45.890778854759425, 11.02912024654856, 0.5d), //
				S5 = new LatLonPoint(45.890737059965325, 11.029113066953538, 0.5d), //
				S6 = new LatLonPoint(45.89071298251531, 11.029082390502094, 0.5d), //
				S7 = new LatLonPoint(10.89071298251531,10.89071298251531,0.0d);


		TimeForecast forecast = new TimeForecast();
		String temp;
		double X = 0,Y = 0; 
		double Z=0.5d;
		
		List<Double> forecast_result;
		forecast_result = forecast.Execute();
		
		for(int i = 0; i<forecast_result.size();i++){

			temp = forecast_result.get(i).toString();


			switch (temp) {
			
			case "5.0":
				X = S1.getLatitude();
				Y = S1.getLongitude();
				break;
				
			case "6.0":
				X = S2.getLatitude();
				Y = S2.getLongitude();
				break;
				
			case "7.0":
				X = S3.getLatitude();
				Y = S3.getLongitude();
				break;
				
			case "8.0":
				X = S4.getLatitude();
				Y = S4.getLongitude();
				break;		

			default:
				break;

			}
			
			System.out.println("X, Y, Z:" + X +"," + Y +"," + Z);
			
		}

	}

//	/* (non-Javadoc)
//	 * @see iCore.CVOTemplates.CVO_Template2#getMembers()
//	 */
//	@Override
//	public List<String> getMembers() {
//		return null;
//	}
//
//	/* (non-Javadoc)
//	 * @see iCore.CVOTemplates.CVO_Template2#getTasks()
//	 */
//	@Override
//	public Method[] getTasks() {
//		return null;
//
//	}
//
//	/* (non-Javadoc)
//	 * @see iCore.CVOTemplates.CVO_Template2#getFunctionality()
//	 */
//	@Override
//	public String getFunctionality() {
//		return null;
//	}

}
