package iCore.CVO;

import iCore.VORegistry.VORegistryClient_Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Search for existing CVOs, composition of new CVOs 
 * Get/Set APIs for returning status, available (C)VOs that satisfy the constraints	
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
@SuppressWarnings("unused")
public class AROD {

	/** The list of VOs. */
	static List<String> ll =  new ArrayList<String>();

	/**
	 * Lookup for existing CVOs : Search for existing CVOs in the CVO registry
	 */
	public void Lookup() {

		boolean CVO_reuse_status = Lookup_CVO();

		//If else, search VOs in the VO registry
		if(!CVO_reuse_status)
		{
			try {
				boolean VO_status = Lookup_VO();
			} catch (URISyntaxException e) {				
				e.printStackTrace();
			}	
		}

	}

	//Search for existing CVOs in the CVO registry
	/**
	 * Lookup_ cvo: currently not implemented
	 *
	 * @return true, if successful
	 */
	private static boolean Lookup_CVO(){

		return false;

	}


	/**
	 * Lookup VOs in the VO Registry :	Should retrieve the available VOs and return the ID of the requested VO
	 *
	 * @return true, if successful
	 * @throws URISyntaxException the URI syntax exception
	 */
	private static boolean Lookup_VO() throws URISyntaxException{

		String api_key = "0iL373db9OKnJbSf4X9T6A==";				
		VORegistryClient_Test vo_registry = new VORegistryClient_Test(api_key);
		vo_registry.Init("DISCOVERY");//DISCOVERY, DELETE, SEARCH_BY_FUNCTION_URI, REGISTRATION
		ll.add("VO1");
		return true;
	}


	/**
	 * Gets the cvos.
	 *
	 * @return the cvos
	 */
	public List<String> get_CVOs(){		

		return ll;
	}

	/**
	 * Gets the vos.
	 *
	 * @return the vos
	 */
	public List<String> get_VOs(){

		return ll;
	}




}
