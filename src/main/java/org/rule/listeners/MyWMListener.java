package org.rule.listeners;

import org.apache.log4j.Logger;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.WorkingMemoryEventListener;
 

public class MyWMListener implements WorkingMemoryEventListener {
	
	private static Logger logger = Logger.getLogger(MyWMListener.class);

	@Override
	public void objectDeleted(ObjectDeletedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void objectInserted(ObjectInsertedEvent evt) {
		logger.info("Inserting object into WM: "+evt.getObject());
	}

	@Override
	public void objectUpdated(ObjectUpdatedEvent arg0) {
		// TODO Auto-generated method stub
		
	}
} 
