package iCore.CVO;

import java.util.Map;

/**
 * The Class CVO_Container 
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class CVO_Container {

	/** The parameter map. */
	private static Map<String, Object> parameterMap;

	/** The erun. */
	static ServiceFlow erun = new ServiceFlow();

	/**
	 * Start.
	 * 
	 * @param parameterMap CVO Instances
	 */
	public void start(Map<String, Object> parameterMap) {

		try {
			final ServiceFlow execute = new ServiceFlow();
			execute.run();
			erun = execute;
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * Stop.
	 */
	public void stop(){
		//		erun.cancel();		
	}

	/**
	 * Gets the _deployment_status.
	 *
	 * @return the _deployment_status
	 */
	public boolean get_deployment_status() {
		return false;			
	}	

	/**
	 * Gets the params.
	 *
	 * @return the params
	 */
	public Map<String, Object> getParams(){
		return parameterMap;
	}

	/**
	 * Sets the params.
	 *
	 * @param params the params
	 */
	public void setParams(Map<String, Object> params){
		CVO_Container.parameterMap = params;		
	}
}
