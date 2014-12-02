package iCore.STemplates;

import java.util.List;

/**
 * The Interface ServiceTemplate to be implemented by all service templates
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public interface ServiceTemplate {
	
	/**
	 * Execute.
	 *
	 * @param filepath_ruleflow the filepath_ruleflow
	 * @param file the file
	 */
	public void execute(String filepath_ruleflow, String file);
	
	/**
	 * Gets the v os.
	 *
	 * @return the v os
	 */
	public List<String> getVOs();
	
	/**
	 * Gets the CV os.
	 *
	 * @return the CV os
	 */
	public List<String> getCVOs();

}
