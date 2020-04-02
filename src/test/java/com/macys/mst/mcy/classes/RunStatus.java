package com.macys.mst.mcy.classes;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class RunStatus {
	public ArrayList <StoryStatus> Stories;
	
	public void AddStory(StoryStatus story) {
		Stories.add(story);
	}
	
	public String GetStoryResults() {
		StringBuilder sb = new StringBuilder();
		double count = 0;
		double pass = 0;
		DecimalFormat myFormatter = new DecimalFormat("##0.00%");
	      ;
		for(StoryStatus s: Stories) {
			pass += s.getScenarioPassCount() ;
			count += s.getScenarioCount();
		}
		
		
		
		sb.append("\n ***** Test Status Report ***** \n");
		sb.append("Job Total: " + pass + " of " + count + " passed for " + myFormatter.format(pass/count) + "\n\n");
		for(StoryStatus s: Stories) {
			sb.append("Story: " + s.Name + " Pass Rate = " + s.getScenarioPassCount() + " of " + s.getScenarioCount() + " passed (" + myFormatter.format((s.getScenarioPassCount()/s.getScenarioCount())) + ")\n");
			for(ScenarioStatus ss : s.Scenarios) {
				sb.append("\t" + ss.Status + ": " + ss.Name + "\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public RunStatus() {
		Stories = new ArrayList <StoryStatus>();
	}
}
