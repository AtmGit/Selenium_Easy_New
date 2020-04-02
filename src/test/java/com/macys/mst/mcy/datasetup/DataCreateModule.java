package com.macys.mst.mcy.datasetup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.macys.mst.artemis.datasetup.DTSDriver;
import com.macys.mst.artemis.datasetup.GetSetDataConstants;
import com.macys.mst.artemis.datasetup.PutdatainOutputdb;
import com.macys.mst.artemis.datasetup.SelectDataModule;
import com.macys.mst.artemis.db.DBConnectionBean;
import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.mcy.datasetup.DataOutputSQLConstants;

public class DataCreateModule extends SelectDataModule {

	static Logger logger = Logger.getLogger(DTSDriver.class.getName());
	public static Connection Conn = DBConnections.getinstance("db","appuser").dbConnection();
	public static Connection datadbConn = DBConnectionBean.gettestdatadbconnection();

	@Override
	public void datacreation(String modulename, HashMap<String, Object> inputmap)
			throws SQLException {

		switch (modulename) {

		case "Order_Creation":

			GetSetDataConstants.setdatainputmap(inputmap);
			OrderWaveWorkCreation orderdatagen = new OrderWaveWorkCreation();
			orderdatagen.setUpDataAndPostOrder(inputmap);

			String Order_Creation_putdataSQL = DataOutputSQLConstants.OutputInstert.Order_Creation_putdataSQL
					.replace(
							"${jiraid}",
							GetSetDataConstants.getdatainputmap()
									.get("JIRA_ISSUE_ID").toString())
					.replace(
							"${scenarioid}",
							GetSetDataConstants.getdatainputmap()
									.get("SCENARIO_ID").toString())
					.replace(
							"${rownum}",
							GetSetDataConstants.getdatainputmap()
									.get("DT_ITR_NUM").toString())
					.replace("${ordernumbers}",
							OrderWaveWorkCreation.getOrdernumber().toString())
					.replace("${locationid}", AppdataGenerationBean.getLocnID())
					.replace("${execenv}", LocalDriverManager.getInstance().getexecenvflag());

			logger.info(Order_Creation_putdataSQL);
			PutdatainOutputdb.Setdatainoutputdb(datadbConn,
					Order_Creation_putdataSQL);

			break;

		case "Order_Wave_Creation":

			GetSetDataConstants.setdatainputmap(inputmap);
			OrderWaveWorkCreation OrderandWavedatagen = new OrderWaveWorkCreation();
			OrderandWavedatagen.setUpDataAndPostOrder(inputmap);

			OrderandWavedatagen.runWaveInWm(inputmap);

			String Order_Wave_Creation_putdataSQL = DataOutputSQLConstants.OutputInstert.Order_Wave_Creation_putdataSQL
					.replace(
							"${jiraid}",
							GetSetDataConstants.getdatainputmap()
									.get("JIRA_ISSUE_ID").toString())
					.replace(
							"${scenarioid}",
							GetSetDataConstants.getdatainputmap()
									.get("SCENARIO_ID").toString())
					.replace(
							"${rownum}",
							GetSetDataConstants.getdatainputmap()
									.get("DT_ITR_NUM").toString())
					.replace("${ordernumbers}",
							OrderWaveWorkCreation.getOrdernumber().toString())
					.replace(
							"${wavenumber}",
							AppdataGenerationBean.getPickWaveNumber()
									.toString())
					.replace("${locationid}", AppdataGenerationBean.getLocnID())
					.replace("${execenv}", LocalDriverManager.getInstance().getexecenvflag());
			logger.info(Order_Wave_Creation_putdataSQL);
			PutdatainOutputdb.Setdatainoutputdb(datadbConn,
					Order_Wave_Creation_putdataSQL);
			break;

		case "Order_Wave_Work_Creation":

			GetSetDataConstants.setdatainputmap(inputmap);
			OrderWaveWorkCreation OrderWaveWorkCreation = new OrderWaveWorkCreation();
			OrderWaveWorkCreation.setUpDataAndPostOrder(inputmap);
			OrderWaveWorkCreation.runWaveInWm(inputmap);

			OrderWaveWorkCreation.releaseWaveAndCreateWork(Conn,
					inputmap.get("DT_WAVE_TYPE").toString());

			String Order_Wave_Work_Creation_putdataSQL = DataOutputSQLConstants.OutputInstert.Order_Wave_Work_Creation_putdataSQL
					.replace(
							"${jiraid}",
							GetSetDataConstants.getdatainputmap()
									.get("JIRA_ISSUE_ID").toString())
					.replace(
							"${scenarioid}",
							GetSetDataConstants.getdatainputmap()
									.get("SCENARIO_ID").toString())
					.replace(
							"${rownum}",
							GetSetDataConstants.getdatainputmap()
									.get("DT_ITR_NUM").toString())
					.replace("${ordernumbers}",
							OrderWaveWorkCreation.getOrdernumber().toString())
					.replace(
							"${wavenumber}",
							AppdataGenerationBean.getPickWaveNumber()
									.toString())
					.replace("${workid}",
							AppdataGenerationBean.getWorkID().toString())
					.replace("${locationid}", AppdataGenerationBean.getLocnID())
					.replace("${execenv}", LocalDriverManager.getInstance().getexecenvflag());

			logger.info(Order_Wave_Work_Creation_putdataSQL);
			PutdatainOutputdb.Setdatainoutputdb(datadbConn,
					Order_Wave_Work_Creation_putdataSQL);

			break;

		default:
			modulename = "Invalid ";
			break;
		}

	}

}
