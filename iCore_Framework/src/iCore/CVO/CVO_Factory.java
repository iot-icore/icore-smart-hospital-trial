package iCore.CVO;

import iCore.CVOTemplates.CVOT_Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  Lookup uninstantiated CVOs 
 * (Fill in the details of the cvo, vo)
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class CVO_Factory {

	/**
	 * Lookup.
	 *
	 * @param ser the ser
	 * @return the map
	 */
	public Map<String, Object> Lookup(Map<String, List<String>> ser) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		CVOT_Config cvot_config = new CVOT_Config();		
		Service_config config = new Service_config();	

		List<String> list_CVOs = new ArrayList<String>();
		list_CVOs = ser.get("CVO");
		config.setCVO(list_CVOs);

		String exec_path = ser.get("exec_path").get(0);

		//TO-DO: If a list of VOs,CVOs
		config.setVOName(ser.get("VO").get(0).toString());
		cvot_config.setVOName(ser.get("VO").get(0).toString());

		if(exec_path.equals("RLTS"))
		{
			config.setCVOName(ser.get("CVO").get(0).toString());
			cvot_config.setCVOName(ser.get("CVO").get(0).toString());
		}

//		else if(exec_path.equalsIgnoreCase("Prediction")){
//
//			config.setCVOName(ser.get("CVO").get(0).toString());
//			cvot_config.setCVOName(ser.get("CVO").get(0).toString());
//
//		}

		config.setTemplatePath(ser.get("TemplateDetails").get(0));
		config.setRuleFlow(ser.get("TemplateDetails").get(1));
		config.setRuleFile("Track_Rules.drl");
		parameterMap.put("config", config);



		//		String path = ("iCore.CVOTemplates.").concat(ser.get("CVO").get(0).toString()).concat("_Config");
		//		ClassLoader myClassLoader = ClassLoader.getSystemClassLoader();
		//	  	Class<?> myClass;
		//		try {
		//			myClass = myClassLoader.loadClass(path);
		//			Object newInstance = myClass.newInstance();
		//			
		//			Method myMethod1 = myClass.getMethod("getVOName", new Class[] {});
		//			Method myMethod2 = myClass.getMethod("getCVOName", new Class[] {});
		//			String VO =  (String) myMethod1.invoke(newInstance, new Object[] {});
		//			String CVO =  (String) myMethod2.invoke(newInstance, new Object[] {});
		//		cvot_config.setCVOName(CVO);
		//		cvot_config.setCVOName(VO);

		//		} catch (ClassNotFoundException e) {
		//			e.printStackTrace();
		//		} catch (InstantiationException e) {
		//			e.printStackTrace();
		//		} catch (IllegalAccessException e) {
		//			e.printStackTrace();
		//		} catch (NoSuchMethodException e) {
		//			e.printStackTrace();
		//		} catch (SecurityException e) {
		//			e.printStackTrace();
		//		} catch (IllegalArgumentException e) {
		//			e.printStackTrace();
		//		} catch (InvocationTargetException e) {
		//			e.printStackTrace();
		//		} 		
		return parameterMap;	
	}


}
