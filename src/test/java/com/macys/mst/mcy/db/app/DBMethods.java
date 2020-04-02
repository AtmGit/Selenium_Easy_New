package com.macys.mst.mcy.db.app;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.mcy.db.app.AppDBMethods;
import com.macys.mst.mcy.db.app.DBMethods;
import com.macys.mst.mcy.sqlconstants.Constants;

public class DBMethods {

	/** The logger. */
	private static Logger logger = Logger.getLogger(DBMethods.class.getName());

	public static String getShipviaOrders(String packageId) throws Exception {

		String sql = "SELECT LPN.DISTRIBUTION_LEV_SVCE_LEVEL_ID,LPN.LPN_ID,ORDERS.PICKUP_START_DTTM,LPN.TC_LPN_ID,LPN.RATED_WEIGHT,LPN.WEIGHT,LPN.TRACKING_NBR,LPN.ALT_TRACKING_NBR,LPN.SHIP_VIA,LPN.ACTUAL_CHARGE,LPN.BASE_CHARGE,LPN.ESTIMATED_WEIGHT"
				+ ",LPN.ADDNL_OPTION_CHARGE,LPN.ESTIMATED_VOLUME,LPN.LENGTH,LPN.WIDTH,LPN.HEIGHT FROM ORDERS ORDERS INNER JOIN LPN LPN on ORDERS.TC_ORDER_ID=LPN.TC_ORDER_ID WHERE LPN.LPN_ID='#LPN_ID'";
		sql = sql.replace("#LPN_ID", packageId);
		ResultSet rs = AppDBMethods.mstDashBoardResultSet(sql);
		String shipVia = null;
		try {
			if (rs != null) {
				while (rs.next()) {

					shipVia = rs.getString("SHIP_VIA");
				}
			}

		} catch (Exception e) {
			logger.error("Exception in getPackageShipDtlByLpnAndOrders:", e);
			;
		}
		return shipVia;
	}

	public static ResultSet getresultset(Connection con, String SQL)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);
			return rs;
		} catch (SQLException e) {
			logger.info("exeception in creating a results set using SQL :"
					+ SQL + "" + e);
		} finally {
			/* if (stmt != null) { stmt.close(); } */
		}
		return rs;
	}

	public static List<HashMap<String, Object>> convertResultSetToList(
			ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		while (rs.next()) {
			HashMap<String, Object> row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(row);
		}
		return list;
	}

	// ___________________________________________________________________________________________________________
	/*
	 * Get a Single String Value
	 */
	public static String getDBValueInString(String query) {
		String dbValue = null;
		ResultSet result_Set = null;
		try {
			result_Set = AppDBMethods.dashBoardResultSet(query);
			while (result_Set.next()) {
				dbValue = result_Set.getString(1);
				// logger.info("dbValue inside While "+dbValue);
				break;
			}

		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}
		return dbValue;
	}

	/*
	 * Get a Sum of all the values in integer
	 */
	public static double getAvailableQtyForItem(String query) {
		double availableQty = 0;
		double onHand = 0;
		double toBeFilled = 0;
		double wmAllocQty = 0;
		ResultSet result_Set = null;
		double dbValue;
		try {
			result_Set = AppDBMethods.dashBoardResultSet(query);
			while (result_Set.next()) {
				double dbvalue1 = result_Set.getDouble(1);
				double dbvalue2 = result_Set.getDouble(2);
				double dbValue3 = result_Set.getDouble(3);
				onHand = onHand + dbvalue1;
				toBeFilled = toBeFilled + dbvalue2;
				wmAllocQty = wmAllocQty + dbValue3;

			}

			availableQty = onHand + toBeFilled - wmAllocQty;

		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}
		return availableQty;
	}

	/*
	 * Get a Sum of all the values in integer
	 */
	public static int getSumOfValues(String query) {
		int totalQty = 0;
		ResultSet result_Set = null;
		try {
			result_Set = AppDBMethods.dashBoardResultSet(query);
			if (result_Set != null) {
				while (result_Set.next()) {
					int dbvalue = result_Set.getInt(1);
					totalQty = totalQty + dbvalue;
				}
			}

		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}
		return totalQty;
	}

	/*
	 * Get a Single Date Value
	 */
	public static Timestamp getDBValueInDate(String query) {
		Timestamp dbValue = null;
		ResultSet result_Set = null;
		try {
			result_Set = AppDBMethods.dashBoardResultSet(query);
			while (result_Set.next()) {
				dbValue = result_Set.getTimestamp(1);
				// logger.info("dbValue inside While "+dbValue);
			}

		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}
		return dbValue;
	}

	/*
	 * Get a Single Integer Value
	 */
	public static Integer getDBValueInteger(String query) {
		// logger.info("Query Is : "+query);
		Integer dbValue = null;
		try {
			ResultSet result_Set = AppDBMethods.dashBoardResultSet(query);
			if (result_Set != null) {
				while (result_Set.next()) {
					dbValue = result_Set.getInt(1);
					// logger.info("the first column is "+dbValue);

				}
			}
		} catch (Exception exception) {
			logger.info("the Db values exception is " + exception.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}
		return dbValue;
	}

	public static List<String> getDBValuesInList(String query) {
		logger.info("Query Is : " + query);
		List<String> listValues = new ArrayList<String>();

		try {
			ResultSet result_Set = AppDBMethods.dashBoardResultSet(query);
			while (result_Set.next()) {
				String value = result_Set.getString(1);

				// logger.info(value);
				listValues.add(value);

			}
		} catch (Exception exception) {
			System.out.println("the Db values exception is "
					+ exception.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}
		return listValues;

	}

	public static List<String> getMultiColumnDBValuesInList(String query) {
		// logger.info("Query Is : "+query);
		List<String> listValues = new ArrayList<String>();
		try {
			ResultSet result_Set = AppDBMethods.dashBoardResultSet(query);
			ResultSetMetaData rsmd = result_Set.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (result_Set.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					String value = result_Set.getString(i);
					// logger.info(value);
					listValues.add(value);
				}

			}
		} catch (Exception exception) {
			System.out.println("the Db values exception is "
					+ exception.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}
		return listValues;

	}

	/* Get list values in SQL query format */
	public static String getValuesForSQl(List<String> list) {
		List<String> sl = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			sl.add("'" + list.get(i) + "'");
		}
		String deptsSqlOrder = sl.toString().substring(1,
				sl.toString().length() - 1);
		return deptsSqlOrder;

	}

	/**
	 * 
	 * @param query
	 * @return a Map
	 */
	public static Map getDBValuesInMap(String query) {
		logger.info("Query Is : " + query);
		Map<String, String> sub_Map = new LinkedHashMap<String, String>();

		try {
			ResultSet result_Set = AppDBMethods.dashBoardResultSet(query);
			while (result_Set.next()) {

				// logger.info("the first column is "+result_Set.getString(1));
				// logger.info("the sec column is "+result_Set.getString(2));
				sub_Map.put(result_Set.getString(1), result_Set.getString(2));

			}
			// AppDBMethods.dataDBConnection().close();
		} catch (Exception exception) {
			System.out.println("the Db values exception is "
					+ exception.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}
		// logger.info("TEST : "+sub_Map.entrySet());
		return sub_Map;
	}

	/*
	 * Insert a Wave Rule into WM
	 */
	public static void insertWaveRule(String waveRule, String waveType,
			String orderPrefix) {
		try {
			/**
			 * Get max of rule ID, RULE_HDR_ID maxRuleSelDtlId maxWaveRuleparmId
			 */
			String maxRuleIdQuery = Constants.Select.MAX_RULE_ID;
			Integer maxRuleId = Integer.valueOf(DBMethods
					.getDBValueInString(maxRuleIdQuery));
			String maxRuleSelDtlIdQuery = Constants.Select.MAX_RULE_SEL_DTL_ID;
			Integer maxRuleSelDtlId = Integer
					.valueOf(getDBValueInString(maxRuleSelDtlIdQuery));
			String maxRuleSortDtlIdQuery = Constants.Select.MAX_RULE_SORT_DTL_ID;
			Integer maxRuleSortDtlId = Integer
					.valueOf(getDBValueInString(maxRuleSortDtlIdQuery));
			String parmIdWaveTypeQuery = Constants.Select.WAVE_PARM_ID_WAVETYPE
					.replace("${waveDesc}", "'" + waveType + "'");
			Integer parmIdWaveType = Integer
					.valueOf(getDBValueInString(parmIdWaveTypeQuery));
			String maxWaveRuleparmIdQuery = Constants.Select.MAX_WAVE_RULE_PARM_ID;
			Integer maxWaveRuleparmId = Integer
					.valueOf(getDBValueInString(maxWaveRuleparmIdQuery));

			/** Write Insert Actions here */

			/** Insert 1 INSERT_RULE_RULEHDR */
			String insertRuleHdrQry = Constants.Insert.INSERT_RULE_RULEHDR
					.replace("${maxRuleId}", String.valueOf(maxRuleId + 1));
			insertRuleHdrQry = insertRuleHdrQry.replace("${ruleName}", "'"
					+ waveRule + "'");
			insertRuleHdrQry = insertRuleHdrQry.replace("${ruleDesc}", "'"
					+ waveRule + "'");
			logger.info("TEST " + insertRuleHdrQry);
			AppDBMethods.deleteOrUpdateWMSDataBase(insertRuleHdrQry);

			/** Insert 2 INSERT_RULE_SEL_DTL */

			String insertRuleSelDtlQry = Constants.Insert.INSERT_RULE_SEL_DTL
					.replace("${maxRuleId}", String.valueOf(maxRuleId + 1));
			insertRuleSelDtlQry = insertRuleSelDtlQry.replace("${orderPrefix}",
					"'" + orderPrefix + "%'");
			insertRuleSelDtlQry = insertRuleSelDtlQry.replace(
					"${maxRuleSelDtlId}", String.valueOf(maxRuleSelDtlId + 1));
			logger.info("TEST " + insertRuleSelDtlQry);
			AppDBMethods.deleteOrUpdateWMSDataBase(insertRuleSelDtlQry);

			/** Insert 3 INSERT_RULE_SEL_DTL 2 */
			String insertRuleSelDtlQry2 = Constants.Insert.INSERT_RULE_SEL_DTL2
					.replace("${maxRuleId}", String.valueOf(maxRuleId + 1));
			insertRuleSelDtlQry2 = insertRuleSelDtlQry2.replace(
					"${maxRuleSelDtlId}", String.valueOf(maxRuleSelDtlId + 2));
			logger.info("TEST " + insertRuleSelDtlQry2);
			AppDBMethods.deleteOrUpdateWMSDataBase(insertRuleSelDtlQry2);

			/*
			 * Insert 3-2 INSERT_RULE_SEL_DTL 3 : Consider orders later to
			 * current date time String insertRuleSelDtlQry3 =
			 * Constants.Insert.INSERT_RULE_SEL_DTL3.replace("${maxRuleId}",
			 * String.valueOf(maxRuleId+1)); insertRuleSelDtlQry3 =
			 * insertRuleSelDtlQry3.replace("${orderPrefix}",
			 * "'"+orderPrefix+"'"); insertRuleSelDtlQry3 =
			 * insertRuleSelDtlQry3.replace("${maxRuleSelDtlId}",
			 * String.valueOf(maxRuleSelDtlId+4));
			 * logger.info("TEST "+insertRuleSelDtlQry3);
			 * AppDBMethods.deleteOrUpdateWMSDataBase(insertRuleSelDtlQry3);
			 */

			/** Insert 4 INSERT_RULE_WAVE_RULE_PARM */
			String insertRuleWaveRuleParmQry = Constants.Insert.INSERT_RULE_WAVE_RULE_PARM
					.replace("${parmIdWaveType}",
							String.valueOf(parmIdWaveType));
			insertRuleWaveRuleParmQry = insertRuleWaveRuleParmQry.replace(
					"${maxRuleId}", String.valueOf(maxRuleId + 1));
			insertRuleWaveRuleParmQry = insertRuleWaveRuleParmQry.replace(
					"${ruleName}", "'" + waveRule + "'");
			insertRuleWaveRuleParmQry = insertRuleWaveRuleParmQry.replace(
					"${maxWaveRuleparmId}",
					String.valueOf(maxWaveRuleparmId + 1));
			logger.info("TEST " + insertRuleWaveRuleParmQry);
			AppDBMethods.deleteOrUpdateWMSDataBase(insertRuleWaveRuleParmQry);

			if (waveRule.equalsIgnoreCase("AutoRWF RpWave")) {
				/** Insert 3-3 INSERT_RULE_SEL_DTL_CSR */
				String insertRuleSelDtlQry4 = Constants.Insert.INSERT_RULE_SEL_DTL_CSR
						.replace("${maxRuleId}", String.valueOf(maxRuleId + 1));
				insertRuleSelDtlQry4 = insertRuleSelDtlQry4.replace(
						"${maxRuleSelDtlId}",
						String.valueOf(maxRuleSelDtlId + 3));
				logger.info("TEST " + insertRuleSelDtlQry4);
				AppDBMethods.deleteOrUpdateWMSDataBase(insertRuleSelDtlQry4);

				/** Insert 3-4 INSERT_RULE_SEL_DTL_BTR */
				String insertRuleSelDtlQry5 = Constants.Insert.INSERT_RULE_SEL_DTL_BTR
						.replace("${maxRuleId}", String.valueOf(maxRuleId + 1));
				insertRuleSelDtlQry5 = insertRuleSelDtlQry5.replace(
						"${maxRuleSelDtlId}",
						String.valueOf(maxRuleSelDtlId + 4));
				logger.info("TEST " + insertRuleSelDtlQry5);
				AppDBMethods.deleteOrUpdateWMSDataBase(insertRuleSelDtlQry5);

				/**
				 * Insert 5 : INSERT_RULE_rule_sort_dtl String
				 * insertRuleSortDtlQry =
				 * Constants.Insert.INSERT_RULE_SORT_DTL1.
				 * replace("${maxRuleId}",String.valueOf(maxRuleId+1));
				 * insertRuleSortDtlQry
				 * =insertRuleSortDtlQry.replace("${maxRuleSortDtlId}",
				 * String.valueOf(maxRuleSortDtlId+1));
				 * logger.info("TEST insertRuleSortDtlQry:  "
				 * +insertRuleSortDtlQry);
				 * AppDBMethods.deleteOrUpdateWMSDataBase(insertRuleSortDtlQry);
				 * 
				 * Insert 6 : INSERT_RULE_rule_sort_dtl 2 String
				 * insertRuleSortDtlQry2 =
				 * Constants.Insert.INSERT_RULE_SORT_DTL2
				 * .replace("${maxRuleId}",String.valueOf(maxRuleId+1));
				 * insertRuleSortDtlQry2 =
				 * insertRuleSortDtlQry2.replace("${maxRuleSortDtlId}",
				 * String.valueOf(maxRuleSortDtlId+2));
				 * logger.info("TEST insertRuleSortDtlQry2: "
				 * +insertRuleSortDtlQry2);
				 * AppDBMethods.deleteOrUpdateWMSDataBase
				 * (insertRuleSortDtlQry2);
				 */

			} else if (waveRule.equalsIgnoreCase("AutoBuyerCode")) {
				/** Insert 3-3 INSERT_RULE_SEL_DTL_BUYERCODE */
				String insertRuleSelDtlQryBuyerCode = Constants.Insert.INSERT_RULE_SEL_DTL_BUYERCODE
						.replace("${maxRuleId}", String.valueOf(maxRuleId + 1));
				insertRuleSelDtlQryBuyerCode = insertRuleSelDtlQryBuyerCode
						.replace("${maxRuleSelDtlId}",
								String.valueOf(maxRuleSelDtlId + 3));
				logger.info("TEST " + insertRuleSelDtlQryBuyerCode);
				AppDBMethods
						.deleteOrUpdateWMSDataBase(insertRuleSelDtlQryBuyerCode);

			}
			logger.info(maxRuleId);
			logger.info(maxRuleSelDtlId);
			logger.info(parmIdWaveType);
			logger.info(maxWaveRuleparmId);

		} catch (Exception e) {
			logger.info("Exception while inserting rule " + e.getMessage());
		} finally {
			// try { AppDBMethods.dataDBConnection().close(); } catch (Exception
			// ignore) { }
		}

	}

	/**
	 * This method gives the current date and time as string.
	 * 
	 * @return String
	 */
	public static String getCurrentDataTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy HH.mm.ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * This method gives the current Time only as string.
	 * 
	 * @return String
	 */
	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH.mm");
		// get current date time with Date() yyy_MM_dd_HH_mm_ss; dd-MMM-yy
		// HH.mm.ss
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void fileCreator(String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static int getColumnCount(String dashboard, String columnname) {
		int colCount = 0;
		try {

			ResourceBundle bundle = ResourceBundle.getBundle("Columns");
			String column = bundle.getString(dashboard + "_" + columnname);
			colCount = Integer.parseInt(column);
			logger.info(colCount);

		} catch (Exception e) {
			logger.info(e);
		}
		return colCount;
	}

	public static List<Map<?, ?>> getValuesFromDBAsList(String sql,
			String schema) throws Exception {
		ResultSet rs = dbResultSet(sql, schema);
		return resultSetToList(rs);
	}


	public static  ResultSet dbResultSet(String query, String schema) throws SQLException,ClassNotFoundException
	{
		ResultSet resultSet=null;
		Connection connection=null;
		try{
			
			System.out.println("Schema :" + schema);
			 connection=DBConnections.getinstance("db",schema).dbConnection();
			logger.info("Query: "+query);
			resultSet = DBUtils.getresultset(connection, query);
		}catch(SQLException exception)
		{
			logger.info("Exception in result set :"+exception.getMessage());
		}
		return resultSet;	
	}

	public static List<Map<?,?>> resultSetToList(ResultSet rs) throws SQLException, IOException
	{
		if(rs!=null)
		{
		List<Map<?,?>> result =new ArrayList<Map<?,?>>();
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		
		int index=0;
		while(rs.next())
		{
			Map resultMap =new LinkedHashMap();
			for (int i = 1; i <= columns; ++i) {
				if(rs.getObject(i)!=null)
				{
					//resultMap.put(md.getColumnName(i), rs.getObject(i));



					if (md.getColumnClassName(i).contains("CLOB")) {
						String clobToString = readClob((Clob) rs.getObject(i));
						resultMap.put(md.getColumnName(i),clobToString);
					} else {
						resultMap.put(md.getColumnName(i),rs.getObject(i));
					}
				
				}
				else
				{
					resultMap.put(md.getColumnName(i), null);
				}
			}
			result.add(index, resultMap);
			index=index+1;
		}
		
		
		
		logger.info("the list is "+result.toString());
		return result;
		}
		else
		{
			return null;
		}
	}

	public static String readClob(Clob clob) throws SQLException, IOException {
		
		StringBuilder sb = new StringBuilder((int) clob.length());
		Reader r = clob.getCharacterStream();
		char[] cbuf = new char[2048];
		int n;
		while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
			sb.append(cbuf, 0, n);
		}
		return sb.toString();
	}
}
