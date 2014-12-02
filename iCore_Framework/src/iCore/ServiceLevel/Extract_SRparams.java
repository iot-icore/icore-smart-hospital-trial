package iCore.ServiceLevel;

import iCore.STemplates.ServiceTemplate;

import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;

/**
 * The Class Extract_SRparams.
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class Extract_SRparams implements ServiceTemplate{
	
	/** The list_ v os. */
	static List<String> list_VOs = new ArrayList<String>();
	
	/** The list_ cv os. */
	static List<String> list_CVOs = new ArrayList<String>();
 
	/* (non-Javadoc)
	 * @see iCore.STemplates.ServiceTemplate#execute(java.lang.String, java.lang.String)
	 */
	public void execute(String filepath_ruleflow, String file){
	
        try {
            // load the knowledge base
            KnowledgeBase kbase = readKnowledgeBase(file);
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "debug");
           
            // start a new process instance
            ksession.setGlobal("flag", 1);
        	ksession.setGlobal("list_VOs", list_VOs);    	
    		ksession.setGlobal("list_CVOs", list_CVOs);
    	    		
            @SuppressWarnings("unused")
			ProcessInstance pID = ksession.startProcess(filepath_ruleflow);      
            ksession.fireAllRules();

            logger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Read knowledge base.
     *
     * @param file the file
     * @return the knowledge base
     * @throws Exception the exception
     */
    private static KnowledgeBase readKnowledgeBase(String file) throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource(file), ResourceType.DRF);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

    /* (non-Javadoc)
     * @see iCore.STemplates.ServiceTemplate#getVOs()
     */
    public List<String> getVOs(){
    	return list_VOs;
    }
    
    /* (non-Javadoc)
     * @see iCore.STemplates.ServiceTemplate#getCVOs()
     */
    public List<String> getCVOs(){
    	return list_CVOs;
    }

	    
}
