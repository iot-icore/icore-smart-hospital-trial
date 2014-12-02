package iCore.ServiceLevel;

import iCore.RWK.DB_Functionalities2;
import iCore.RWK.MedicalDevices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import opennlp.tools.util.InvalidFormatException;

/**
 * The Class ServiceLevel :Service Level functions including service registration, service request formulation
 * and service request analysis
 * 
 * The methods are called from the main entry point * 
 * 
 *  
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class ServiceLevel {

	/**
	 * Register service: Assigns a service Id for every incoming service request
	 *
	 * @return the integer
	 */
	public Integer RegisterService(){

		//Register the service request
		Random rn = new Random();
		int serviceID = rn.nextInt();
		//		System.out.println("----Service Request Registration Complete----");
		//		System.out.println("Service ID:"+serviceID);

		return serviceID;

	}

	/**
	 * Service Request Formulation:Calls the NLP(Open NLP) module inorder to parse the incoming SR into tokens.
	 *
	 * @param sR service request coming in from the end user
	 * @return formulated service request
	 * @throws InvalidFormatException the invalid format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Map<String, String> SR_Formulate(String sR) throws InvalidFormatException, IOException{

		String tokens[];
		Map<String,String> sr_formulate = new HashMap<>();
		tokens = sR.split(" ");

		//Formulate Service Request parameters		
		String path     = "iCore.STemplates.";
		String SR_token =  tokens[0];
		String string3  = path.concat(SR_token);					

		sr_formulate.put("methodParameter1", string3);	
		sr_formulate.put("methodParameter2", SR_token.concat(".rf"));
		sr_formulate.put("VO-SR", tokens[1]);
		sr_formulate.put("CVO-SR", tokens[0]);
		return sr_formulate;		
	}

	/**
	 * Service Request Analysis: Takes in the tokens and based on the ST returns the list of required VO, CVOs 
	 * required to fulfill the incoming service request .
	 *
	 * @param sr_params the sr_params
	 * @return hashmap of the service execution request parameters
	 */
	public Map<String, List<String>> SR_Analysis(Map<String,String> sr_params){		

		Map<String,List<String>> SER = new HashMap<>();		

		//		System.out.println("----Initiate Service Request Translation----");
		ServiceTranslator serTranslate = new ServiceTranslator();
		SER = serTranslate.Translate(sr_params);	

		//		System.out.println("..Required VO,CVOs..");
		//		System.out.println("----Service Request Analysis Complete----");

		return SER;
	}

	/**
	 * RWK lookup: Performs a lookup of the RWK DB in order to decide the decision path
	 *
	 * @param ser Service Execution Request
	 * @return the map
	 */
	public Map<String, List<String>> RWK_Lookup(Map<String, List<String>> ser) {

		String VO = ser.get("VO").get(0).toString();

		List<MedicalDevices> devices = new ArrayList<MedicalDevices>();
		DB_Functionalities2 db = new DB_Functionalities2();		
		devices = db.Query(VO);

		String state = devices.get(0).getPower_state();
		List<String> db_lookup_exec = new ArrayList<String>();

		if(state.equalsIgnoreCase("on"))
		{
			db_lookup_exec.add("RLTS");
		}

		else if(state.equalsIgnoreCase("off"))
		{
			db_lookup_exec.add("Prediction");
			if(Float.parseFloat(devices.get(0).getPredictionAccuracy()) < 80)
			{
				db_lookup_exec.add("RLTS");
			}
		}

		ser.put("exec_path",db_lookup_exec);
		return ser;

	}



}
