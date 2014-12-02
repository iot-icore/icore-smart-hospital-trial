package iCore.CVOTemplates;

import iCore.RWK.DB_Functionalities2;
import iCore.RWK.MedicalDevices;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Class Locate_CVOT
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class Locate_CVOT implements CVO_Template {
	
	/** The coord. */
	static private HashMap<String, Object> coord = new HashMap<String, Object>();

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template#getName()
	 */
	public String getName() {
		String cvo_name = "Locate";
		return cvo_name;
	}

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template#execute(java.lang.String)
	 */
	public HashMap<String, Object> execute(String VO) {
		
		List<MedicalDevices> devices = new ArrayList<MedicalDevices>();
		
		DB_Functionalities2 db = new DB_Functionalities2();		
		devices = db.Query(VO);
		
		coord.put("x", devices.get(0).getPosition_x());
		coord.put("y", devices.get(0).getPosition_y());
		
		return coord;		
	}
	
	/**
	 * Gets the coords.
	 *
	 * @return the coords
	 */
	public HashMap<String, Object> getCoords(){		
		return Locate_CVOT.coord;
		
	}


}
