package iCore.ServiceLevel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class ServiceTranslator.
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class ServiceTranslator {

	/**
	 * Translate.
	 *
	 * @param sr_params the service request parameters
	 * @return the map
	 */
	public Map<String,List<String>> Translate(Map<String, String> sr_params) {
	
		Map<String,List<String>> SER = new HashMap<>();	
		
		
		List<String> list_VOs = new ArrayList<String>();
		List<String> list_CVOs = new ArrayList<String>();
		List<String> list_path = new ArrayList<String>();
		
		//If need for complex resolutions for VO types, use the workflow Drools based method is chosen
		Extract_SRparams st = new Extract_SRparams();
		st.execute( sr_params.get("methodParameter1"),sr_params.get("methodParameter2"));
//		list_VOs = st.getVOs();
//		list_CVOs = st.getCVOs();
		
		//A simpler method extractig the input parameters directly from the service request
		list_VOs.add(sr_params.get("VO-SR"));
		list_CVOs.add(sr_params.get("CVO-SR"));

		list_path.add(sr_params.get("methodParameter1"));
		list_path.add(sr_params.get("methodParameter2"));
		
		SER.put("VO",list_VOs);
		SER.put("CVO",list_CVOs);
		SER.put("TemplateDetails",list_path);		
		
		return SER;
	}
	
	
	

}
