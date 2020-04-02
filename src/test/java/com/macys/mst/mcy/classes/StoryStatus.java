package com.macys.mst.mcy.classes;

import java.util.ArrayList;

public class StoryStatus {

	public ArrayList<ScenarioStatus> Scenarios;
	public String Name;
	public double getScenarioCount() {
		return Scenarios.size();
	}
	
	public double getScenarioPassCount() {
		double pass = 0;
		for(ScenarioStatus ss: Scenarios) {
			if(ss.Status.equalsIgnoreCase("PASSED")) {
				pass++;
			}
		}
		return pass;
	}

	public StoryStatus() {
		Scenarios = new ArrayList<ScenarioStatus>();
	}
}
