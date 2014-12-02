package iCore.CVOTemplates;

/**
 * The Class CVOT_Config.
 */
public class CVOT_Config {
	
	/** The VO name. */
	static private String VOName;
	
	/** The CVO name. */
	static private String CVOName;

	/**
	 * Gets the VO name.
	 *
	 * @return the VO name
	 */
	public String getVOName(){
		return VOName;
		
	}
	
	/**
	 * Sets the VO name.
	 *
	 * @param name the new VO name
	 */
	public void setVOName(String name) {
		CVOT_Config.VOName = name;
	}
	
	/**
	 * Gets the CVO name.
	 *
	 * @return the CVO name
	 */
	public String getCVOName(){
		return CVOName;
		
	}
	
	/**
	 * Sets the CVO name.
	 *
	 * @param name the new CVO name
	 */
	public void setCVOName(String name) {
		CVOT_Config.CVOName = name;
	}

}
