package com.macys.mst.mcy.execdrivers;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;



//import com.macys.mst.projectname.stepdefinitions.ServiceSteps;
import com.macys.mst.artemis.serenityJbehaveJira.SerenityJiraTestRunner;


//import com.macys.mst.mce.stepdefinitions.Dataconsumptiondemo;
public class SerenityJiraRunConfig extends SerenityJiraTestRunner {

	@Override
	public InjectableStepsFactory stepsFactory(){
		return new InstanceStepsFactory(configuration() 
		);
	}

}
