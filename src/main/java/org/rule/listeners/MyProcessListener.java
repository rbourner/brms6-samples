
package org.rule.listeners;

import org.apache.log4j.Logger;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
 

public class MyProcessListener implements ProcessEventListener {
	private static Logger logger = Logger.getLogger(MyProcessListener.class);

	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent evt) {
		logger.debug("Leaving node '"+evt.getNodeInstance().getNodeName()+"'");
	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent evt) {
		logger.debug("Entering node '"+evt.getNodeInstance().getNodeName()+"'");
	}

	@Override
	public void beforeProcessStarted(ProcessStartedEvent evt) {
		logger.debug("Entering process '"+evt.getProcessInstance().getProcessName()+"'");
	}

	@Override
	public void afterNodeLeft(ProcessNodeLeftEvent arg0) {
	}

	@Override
	public void afterProcessCompleted(ProcessCompletedEvent arg0) {
	}

	@Override
	public void afterProcessStarted(ProcessStartedEvent arg0) {
	}

	@Override
	public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
	}

	@Override
	public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {
	}

	@Override
	public void beforeProcessCompleted(ProcessCompletedEvent arg0) {
	}

	@Override
	public void beforeVariableChanged(ProcessVariableChangedEvent arg0) {
	}
} 