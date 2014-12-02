package iCore.CVO;

import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;

/**
 * The Class SimpleWorkItemHandler.
 */
public class SimpleWorkItemHandler implements WorkItemHandler{

    /* (non-Javadoc)
     * @see org.drools.runtime.process.WorkItemHandler#executeWorkItem(org.drools.runtime.process.WorkItem, org.drools.runtime.process.WorkItemManager)
     */
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        System.out.println("Executing work item " + workItem);
        manager.completeWorkItem(workItem.getId(), null);
    }
   
    /* (non-Javadoc)
     * @see org.drools.runtime.process.WorkItemHandler#abortWorkItem(org.drools.runtime.process.WorkItem, org.drools.runtime.process.WorkItemManager)
     */
    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        // Do nothing here
    }

}
