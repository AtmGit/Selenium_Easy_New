package com.macys.mst.mcy.execdrivers;

import java.util.HashMap;
import java.util.Map;

import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.WebDriver;

import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.serenityJbehaveJira.Executiondriver;
import com.macys.mst.mcy.datasetup.DataSetupDriver;

public class ExecutionConfig extends Executiondriver {

	public String execenv = LocalDriverManager.getInstance().getexecenvflag();

	@Override
	public void calserenityjiralocaltestrunner() {
		
		SerenityJiraRunConfig jirR = new SerenityJiraRunConfig();

		try {
			jirR.run();
			// new SerenityLocalRunConfig();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public void calserenitylocaltestrunner() {

		SerenityLocalRunConfig SerRun = new SerenityLocalRunConfig();

		try {
			SerRun.run();
		} catch (Throwable e) {

			e.printStackTrace();

		}

	}

	@Override
	public void calDTSdriver(String execenv) {

		DataSetupDriver dtsrun = new DataSetupDriver();

		try {
			dtsrun.setupdata(execenv);
		} catch (Throwable e) {

			e.printStackTrace();

		}

	}

	@Override
	public Map<String, String> schemaNames() {
		Map<String, String> SchemaNames = new HashMap<String, String>();
		SchemaNames.put("dbtype", "db");
		SchemaNames.put("automationdbuser", "autouser");
		return SchemaNames;
	}
	


}
