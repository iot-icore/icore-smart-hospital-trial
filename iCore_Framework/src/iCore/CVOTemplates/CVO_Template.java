package iCore.CVOTemplates;

import java.util.HashMap;

/**
 * The Interface CVO_Template.
 */
public interface CVO_Template {	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
//	public String getFunctionality();
	/**
 * Execute.
 *
 * @param VO the vo
 * @return the hash map
 */
public HashMap<String,Object> execute(String VO);
//	public List<String> getMembers();
//	public Method[] getTasks();
}
