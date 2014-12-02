package iCore.CVO;

import java.util.List;

/**
 * The Class Service_config.
 */
public class Service_config {

	/** The Template path. */
	private String TemplatePath = null;

	/** The VO name. */
	private String VOName;

	/** The CVO name. */
	static private String CVOName;

	/** The CV os. */
	static List<String> CVOs;

	/** The ruleflow. */
	private String ruleflow;

	/** The rule file. */
	private String ruleFile;


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
		this.VOName = name;
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
		Service_config.CVOName = name;
	}

	/**
	 * Gets the cvo.
	 *
	 * @return the cvo
	 */
	public List<String> getCVO(){
		return CVOs;

	}

	/**
	 * Sets the cvo.
	 *
	 * @param CVO the new cvo
	 */
	public void setCVO(List<String> CVO) {
		Service_config.CVOs = CVO;
	}

	/**
	 * Gets the template path.
	 *
	 * @return the template path
	 */
	public String getTemplatePath() {
		return TemplatePath;
	}

	/**
	 * Sets the template path.
	 *
	 * @param path the new template path
	 */
	public void setTemplatePath(String path) {
		this.TemplatePath = path;
	}

	/**
	 * Gets the rule flow.
	 *
	 * @return the rule flow
	 */
	public String getRuleFlow(){
		return ruleflow;
	}

	/**
	 * Sets the rule flow.
	 *
	 * @param ruleflow the new rule flow
	 */
	public void setRuleFlow(String ruleflow) {
		this.ruleflow = ruleflow;
	}

	/**
	 * Gets the rule file.
	 *
	 * @return the rule file
	 */
	public String getRuleFile() {

		return ruleFile;
	}

	/**
	 * Sets the rule file.
	 *
	 * @param ruleFile the new rule file
	 */
	public void setRuleFile(String ruleFile) {
		this.ruleFile = ruleFile;
	}
}
