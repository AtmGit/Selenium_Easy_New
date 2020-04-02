package com.macys.mst.mcy.datasetup;

public interface DataOutputSQLConstants {

	interface OutputInstert {
		/**
		 * These SQL's are to insert data into specific data output table
		 * 
		 */
		public static final String Order_Creation_putdataSQL = "INSERT INTO AUTO_DATA_OUTPUT "
				+ " (JIRA_ISSUE_ID,SCENARIO_ID,DT_ITR_NUM,DT_ORDERS,DT_LOCATION_ID,ENV)"
				+ " VALUES ('${jiraid}','${scenarioid}','${rownum}','${ordernumbers}','${locationid}','${execenv}')";

		public static final String Order_Wave_Creation_putdataSQL = "INSERT INTO AUTO_DATA_OUTPUT "
				+ " (JIRA_ISSUE_ID,SCENARIO_ID,DT_ITR_NUM,DT_ORDERS,DT_WAVE_NUMBER,DT_LOCATION_ID,ENV)"
				+ " VALUES ('${jiraid}','${scenarioid}','${rownum}','${ordernumbers}','${wavenumber}','${locationid}','${execenv}')";

		public static final String Order_Wave_Work_Creation_putdataSQL = "INSERT INTO AUTO_DATA_OUTPUT "
				+ " (JIRA_ISSUE_ID,SCENARIO_ID,DT_ITR_NUM,DT_ORDERS,DT_WAVE_NUMBER,DT_WORK_ID,DT_LOCATION_ID,ENV)"
				+ " VALUES ('${jiraid}','${scenarioid}','${rownum}','${ordernumbers}','${wavenumber}','${workid}','${locationid}','${execenv}')";

	}

}
