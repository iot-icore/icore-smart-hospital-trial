package iCore.CVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class CVO_Management_Unit: Initiates Approximation and Reuse Detector(AROD), CVO Factory, CVO Container
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class CVO_Management_Unit{
	
	/** The arod. */
	AROD arod = new AROD();
	
	/** The cvo_factory. */
	CVO_Factory cvo_factory = new CVO_Factory();
	
	/** The cvo_container. */
	CVO_Container cvo_container = new CVO_Container();
	
	/** The list_ cv os. */
	List<String> list_CVOs =  new ArrayList<String>();
	
	/** The parameter map. */
	Map<String, Object> parameterMap = new HashMap<String, Object>();

	/**
	 * Initializes the CVO 
	 *
	 * @param ser the ser
	 */
	public void Init(Map<String, List<String>> ser) {

		//AROD
		arod.Lookup();		
		list_CVOs = arod.get_CVOs();

		
		//CVO Factory
//		System.out.println("----CVO_MU_Factory: Lookup for uninstantiated CVOs----");
		parameterMap=cvo_factory.Lookup(ser);
		
		//CVO Container
//		System.out.println("----CVO Container Initiation----");
//		System.out.println("");
		
		cvo_container.setParams(parameterMap);
		cvo_container.start(parameterMap);
//		cvo_container.get_deployment_status();
		
//		System.out.println("----CVO Initiation Complete----");
	}

	/**
	 * Gets the service status.
	 *
	 * @return the service status
	 */
	public boolean getServiceStatus() {
		return false;
	}
	
	/**
	 * Stop.
	 */
	public void Stop(){
		cvo_container.stop();
	}


}
