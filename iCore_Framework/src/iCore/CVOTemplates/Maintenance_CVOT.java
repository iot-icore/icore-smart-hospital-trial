package iCore.CVOTemplates;

import iCore.RWK.DB_Functionalities2;
import iCore.RWK.MedicalDevices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Class Maintenance_CVOT.
 */
public class Maintenance_CVOT implements CVO_Template {
	
	/** The main_date to be updated in the RWK database */
	static private HashMap<String, Object> main_date = new HashMap<String, Object>();

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template#getName()
	 */
	@Override
	public String getName() {
		String cvo_name = "Maintenance";
		return cvo_name;
	}

	
	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template#execute(java.lang.String)
	 */
	@Override
	public HashMap<String, Object> execute(String VO) {			
			
		List<MedicalDevices> devices = new ArrayList<MedicalDevices>();

		DB_Functionalities2 db = new DB_Functionalities2();
		devices = db.Query(VO);
		
		main_date.put("main_date", devices.get(0).getMaintenance_date());
		
		return main_date;					
	}
	
	/**
	 * Gets the coords.
	 *
	 * @return the coords
	 */
	public HashMap<String, Object> getCoords(){		
		return Maintenance_CVOT.main_date;
		
	}


}
