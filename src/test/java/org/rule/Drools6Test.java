package org.rule;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.drools.core.RuleBaseConfiguration;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.KnowledgeBaseFactory;
import org.rule.listeners.MyAgendaListener;
import org.rule.listeners.MyWMListener;
import org.rule.model.SimpleFact;

public class Drools6Test {

	private static Logger logger = Logger.getLogger(Drools6Test.class);

    @Test
	public void startTest1()  {
    	logger.info("");
    	logger.info("############ TEST 1 #############");
    	KieServices ks = KieServices.Factory.get();
    	KieRepository kr = ks.getRepository();
    	KieFileSystem kfs = ks.newKieFileSystem();
        Resource res = ks.getResources().newClassPathResource("defaultKBase/TestRules.drl");
    	kfs.write(res);
    	
    	KieBuilder kb = ks.newKieBuilder(kfs);
    	kb.buildAll();
    	
    	if (kb.getResults().hasMessages()) {
    		logger.error("!!! Problem: "+kb.getResults().toString());
    	}    	
    	
    	KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
    	
    	RuleBaseConfiguration kBaseConf = new RuleBaseConfiguration();
    	EventProcessingOption evtProcOpt = EventProcessingOption.STREAM;
    	boolean phreakEnabled = true;
    	logger.info("Using event processing mode: "+evtProcOpt);
    	kBaseConf.setEventProcessingMode(evtProcOpt);
    	if (phreakEnabled) {
    		logger.info("Using algorith: PHREAK");
    	} else {
    		logger.info("Using algorith: RETE");    		
    	}
    	kBaseConf.setPhreakEnabled(phreakEnabled); 

        KieBase kbase = kContainer.newKieBase(kBaseConf);

    	KieSessionConfiguration ksessionConfig = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
    	final KieSession ksession = kbase.newKieSession(ksessionConfig, null);
    	
    	MyAgendaListener agendaListener = new MyAgendaListener();
    	ksession.addEventListener(agendaListener);
    	MyWMListener wml = new MyWMListener();
    	ksession.addEventListener(wml);
    	
    	SimpleFact fact1 = new SimpleFact("id1", "NOK");
    	SimpleFact fact2 = new SimpleFact("id2", "NOK");
        ksession.insert(fact1);
        ksession.insert(fact2);
        ksession.fireAllRules();            	
    	assertEquals(ksession.getFactCount(), 2); 
        assertEquals(fact1.getStatus(), "OK");
        assertEquals(fact2.getStatus(), "OK");
        
        ksession.dispose();
	}
    
    @Test
	public void startTest2()  {
    	logger.info("");
    	logger.info("############ TEST 2 #############");
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("session0");

    	SimpleFact fact1 = new SimpleFact("id3", "NOK");
    	SimpleFact fact2 = new SimpleFact("id4", "NOK");
    	kieSession.insert(fact1);
    	kieSession.insert(fact2); 
    	kieSession.fireAllRules();            	
    	assertEquals(kieSession.getFactCount(), 2); 
        assertEquals(fact1.getStatus(), "OK");
        assertEquals(fact2.getStatus(), "OK");
        
    	kieSession.dispose();
    }
    
    @Test
	public void startTest3()  { 
    	logger.info("");
    	logger.info("############ TEST 3 #############");
    	
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession("session1");

    	SimpleFact fact1 = new SimpleFact("idA", "NOK");
    	SimpleFact fact2 = new SimpleFact("idB", "NOK");
    	kieSession.insert(fact1);
    	kieSession.insert(fact2); 

		kieSession.startProcess("KieSample");
    	kieSession.fireAllRules();           
    	
    	assertEquals(kieSession.getFactCount(), 2); 
        assertEquals(fact1.getStatus(), "OK");
        assertEquals(fact2.getStatus(), "OK");
        
    	kieSession.dispose(); 
    }
}
