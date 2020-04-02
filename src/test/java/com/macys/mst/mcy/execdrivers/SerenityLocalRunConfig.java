package com.macys.mst.mcy.execdrivers;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

//import com.macys.mst.projectname.stepdefinitions.ServiceSteps;
import com.macys.mst.artemis.serenityJbehaveLocal.SerenityLocalTestRunner;
import com.macys.mst.mcy.stepdefinitions.AlertsModalsPage;
import com.macys.mst.mcy.stepdefinitions.DatePickersPage;
import com.macys.mst.mcy.stepdefinitions.InputFormsPage;
import com.macys.mst.mcy.stepdefinitions.ListBoxPage;
import com.macys.mst.mcy.stepdefinitions.OthersPage;
import com.macys.mst.mcy.stepdefinitions.ProgressBarsPage;
import com.macys.mst.mcy.stepdefinitions.TablePage;
import com.macys.mst.mcy.stepdefinitions.InputFormsPage;

import com.macys.mst.mcy.utilities.Logging;


public class SerenityLocalRunConfig extends SerenityLocalTestRunner {

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(),
								
	//		new	GMRLaunch(),
	//		new InputFormsPage(),
	//		new DatePickersPage()
	//		new TablePage()
	//        new ProgressBarsPage()
	//        new AlertsModalsPage()
	//			new Test99GuruLogin()
	//			new ListBoxPage()
				new OthersPage()
					
		);
	}
}
