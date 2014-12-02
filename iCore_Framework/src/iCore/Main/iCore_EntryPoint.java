package iCore.Main;

import iCore.CVO.CVO_Management_Unit;
import iCore.ServiceLevel.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opennlp.tools.util.InvalidFormatException;

/**
 * The Class iCore_EntryPoint - Holds the main entry point of the program control flow
 * Calls the three tier iCore architectural components. 
 * This is called by the server for instantiating of the iCore Framework services.
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014  
 */
public final class iCore_EntryPoint {
	
	/**  The service execution request. */
	static Map<String, List<String>> SER = new HashMap<>();	
	
	/** The sr_formulate. */
	static Map<String,String> sr_formulate = new HashMap<>();
	
	/** The service_id. */
	static Integer service_id = 0;
	
	/**  The service request. */
	static String SR = null;
	
	/**
	 * Trial_iCore.
	 *
	 * @param SR represents the service request in Natural Language
	 * 
	 */
	public void Trial_iCore(String SR){			
		try {
			System.out.println(SR); 
			
			/*Service Level Functions*/	
			ServiceLevel SL = new ServiceLevel();
			
			/*Register Service and call NLP to extract tokens from SR*/
			service_id = SL.RegisterService();
			sr_formulate = SL.SR_Formulate(SR);
			
			/*Service Request Analysis block*/
			SER = SL.SR_Analysis(sr_formulate);

			/*RWK Lookup to decide the execution path*/
			SER = SL.RWK_Lookup(SER);
			
			//CVO Management Unit
			CVO_Management_Unit CVO_MU  = new CVO_Management_Unit();

			CVO_MU.Init(SER);	
//			CVO_MU.getServiceStatus();
			

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			}
}

	