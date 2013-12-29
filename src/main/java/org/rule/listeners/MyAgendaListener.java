package org.rule.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.runtime.rule.FactHandle;
 

public class MyAgendaListener implements AgendaEventListener {
	
	private static Logger logger = Logger.getLogger(MyAgendaListener.class);
	
	private List<String> rulesFired = new ArrayList<String>();
	
	public List<String> getRulesFired() {
		return this.rulesFired;
	}

	public void afterMatchFired(AfterMatchFiredEvent ev) {
		String ruleName = ev.getMatch().getRule().getName();
		StringBuffer buf = new StringBuffer();
		buf.append("***** Rule fired: '"+ruleName+"'");
		rulesFired.add(ruleName);
		Iterator<? extends FactHandle> iter = ev.getMatch().getFactHandles().iterator();
		while (iter.hasNext()) {
			buf.append(", fact: "+iter.next().toString());
		}
		logger.info(buf.toString());	
	}

	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0) {
		// TODO Auto-generated method stub		
	}

	public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0) {
		// TODO Auto-generated method stub		
	}

	public void agendaGroupPopped(AgendaGroupPoppedEvent arg0) {
		// TODO Auto-generated method stub		
	}

	public void agendaGroupPushed(AgendaGroupPushedEvent arg0) {
		// TODO Auto-generated method stub		
	}

	public void beforeMatchFired(BeforeMatchFiredEvent arg0) {
		// TODO Auto-generated method stub		
	}

	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0) {
		// TODO Auto-generated method stub		
	}

	public void beforeRuleFlowGroupDeactivated(
			RuleFlowGroupDeactivatedEvent arg0) {
		// TODO Auto-generated method stub		
	}

	public void matchCancelled(MatchCancelledEvent arg0) {
		// TODO Auto-generated method stub		
	}

	public void matchCreated(MatchCreatedEvent arg0) {
		// TODO Auto-generated method stub		
	}
}
