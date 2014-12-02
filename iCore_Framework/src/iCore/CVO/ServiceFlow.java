package iCore.CVO;

import iCore.CVOTemplates.CVOT_Config;
import iCore.CVO.SimpleWorkItemHandler;

import java.util.Map;
import java.util.Properties;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * The Class ServiceFlow:: Invokes the CVO Container which uses a Drools workflow based model in order to execute the sequence of operations
 * 
 *  @author: Swaytha Sasidharan
 *  @version: 1.0
 *  @since: 01.11.2014 
 */
public class ServiceFlow{
	
	/**
	 * Start service 
	 *
	 * @throws Exception the exception
	 */
	private void StartService() throws Exception  {

		
		CVO_Container container = new CVO_Container(); 
		CVOT_Config params = new CVOT_Config();
		Service_config config = new Service_config();

		Map<String, Object> parameterMap = container.getParams();
		config = (Service_config) parameterMap.get("config");
		
		Properties props = new Properties();
	    props.setProperty("drools.dialect.java.compiler", "JANINO");
	    
		KnowledgeBase knowledgeBase = readKnowledgeBase(config.getRuleFlow(),config.getRuleFile());
		StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();				 

		SimpleWorkItemHandler handler = new SimpleWorkItemHandler();

		ksession.getWorkItemManager().registerWorkItemHandler("Log", handler);
		ksession.getWorkItemManager().registerWorkItemHandler("Rule Task", handler);

//		SO_Params so = new SO_Params();
//		so.setSO_flag(false);
//		SO_Params.setSer_exec_flag(true);
//		RWObjects rwo = new RWObjects("Object1");
//		rwo.setName("Object1");
//		rwo.setPredictionAccuracy(82);
//		
//		ksession.insert(so);		
//		ksession.insert(rwo);
	
		ksession.setGlobal("flag", 0);
		ksession.insert(config);
		ksession.insert(params);
		ksession.startProcess(config.getTemplatePath(), parameterMap);
		ksession.fireAllRules();
	}
	
	/**
	 * Read knowledge base.
	 *
	 * @param ruleflow the ruleflow
	 * @param ruleFile the rule file
	 * @return the knowledge base
	 * @throws Exception the exception
	 */
	private static KnowledgeBase readKnowledgeBase(String ruleflow, String ruleFile) throws Exception {
		
	
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		kbuilder.add(ResourceFactory.newClassPathResource(ruleflow), ResourceType.DRF);
		kbuilder.add(ResourceFactory.newClassPathResource(ruleFile), ResourceType.DRL);
		return kbuilder.newKnowledgeBase();
	}
	
	/**
	 * Run.
	 */
	public void run() {	
		try {
			StartService();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}


}


