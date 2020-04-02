//**********************************************************************************************************
// Class  : MmgDBHelper
// Author : Merlin Arulraj
// Purpose: Provides methods for Database communications
//**********************************************************************************************************

package com.macys.mst.mcy.db.app;

import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Base64;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;

import com.ibm.icu.text.DateFormat;
import com.macys.mst.artemis.config.*;
import com.macys.mst.artemis.db.DBConnectionBean;
import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.mcy.stepdefinitions.ConfigFileInfoClass;
import com.macys.mst.mcy.utilities.Logging;
import com.macys.mst.mcy.classes.ClassVariableDetailsHolder;
import com.macys.mst.mcy.sqlconstants.SQLConstants;

public class MmgDBHelper{
	static Logger logger = Logger.getLogger(MmgDBHelper.class.getName());
	public static String driver;
	public static String uri;
	public static String username;
	public static String password;
	public static Connection cbeDbConnection=null;
	public static Connection mmrDbConnection=null;
	
	public static Connection dbConnection;
	/**added by B004944-PC*/
	public static Connection odsConnection=null;	
	public static  Connection blmCbeConnection=null;
	public static  Connection mmrCbeConnection=null;
	public static  Connection db2Connection=null;
	
	
	/**added by B004944-PC*/
	public static Connection dbOdsConnection(String env)
	{
		
		try
		{
			if(odsConnection==null || odsConnection.isClosed())
			{
				 driver = ConfigFileInfoClass.odsdriver;
			        uri = ConfigFileInfoClass.odsuri;
				    username = ConfigFileInfoClass.odsusername;
				    password = GetRedString(ConfigFileInfoClass.odspassword);
				
				Class.forName(driver);
				odsConnection=DriverManager.getConnection(uri,username,password);
				odsConnection.setAutoCommit(false);
				setOdsConnection(odsConnection);
			} else {
				logger.info("ODS Connection is NOT NULL");
				System.out.println("the connection client info"+odsConnection.getClientInfo());
				return odsConnection;
			}
					
		}
		catch(Exception e)
		{
			logger.info(e);
		}
		return odsConnection;
	}
	
	
	/**added by B004944-PC*/
	public static Connection dbBlmCbeConnection(String env)
	{
		
		try
		{
			if(blmCbeConnection==null || blmCbeConnection.isClosed())
			{
				 driver = ConfigFileInfoClass.cbeDriver;
			        uri = ConfigFileInfoClass.cbeUri;
				    username = ConfigFileInfoClass.cbeUsername;
				  //  password = ConfigFileInfoClass.cbePassword;
				    password = GetRedString(ConfigFileInfoClass.cbePassword);
				Class.forName(driver);
				blmCbeConnection=DriverManager.getConnection(uri,username,password);
				blmCbeConnection.setAutoCommit(false);
			} else {
				logger.info("CBE Connection is NOT NULL");
				return blmCbeConnection;
			}
					
		}
		catch(Exception e)
		{
			logger.info(e);
		}
		return blmCbeConnection;
	}
	
	public static Connection dbMmrCbeConnection(String env)
	{
		
		try
		{
			if(mmrCbeConnection==null || mmrCbeConnection.isClosed())
			{
				 driver = ConfigFileInfoClass.mmrDriver;
			        uri = ConfigFileInfoClass.mmrUri;
				    username = ConfigFileInfoClass.mmrUsername;
				  //  password = ConfigFileInfoClass.cbePassword;
				    password = GetRedString(ConfigFileInfoClass.mmrPassword);
				Class.forName(driver);
				mmrCbeConnection=DriverManager.getConnection(uri,username,password);
				mmrCbeConnection.setAutoCommit(false);
			} else {
				logger.info("MMR Connection is NOT NULL");
				return mmrCbeConnection;
			}
					
		}
		catch(Exception e)
		{
			logger.info(e);
			
		}
		return mmrCbeConnection;
	}
	
	
	/**added by B004944-PC*/
	public static Connection db2Connection(String env)
	{
		
		try
		{
			if(db2Connection==null || db2Connection.isClosed())
			{
				 driver = ConfigFileInfoClass.db2driver;
			        uri = ConfigFileInfoClass.db2uri;
				    username = ConfigFileInfoClass.db2username;
				    password = GetRedString(ConfigFileInfoClass.db2password);
				Class.forName(driver);
				db2Connection=DriverManager.getConnection(uri,username,password);
				db2Connection.setAutoCommit(false);
			} else {
				logger.info("Connection is NOT NULL");
				return db2Connection;
			}
					
		}
		catch(Exception e)
		{
			logger.info(e);
		}
		return db2Connection;
		
	}
	
	
	public static void CloseConnection(){
		try {
			if(!dbConnection.isClosed()) dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logging.LogError("Failed to close connection");
			Assert.assertTrue(false, "Failed to close connection");
		}
	}
	
	
	public static void closeFullValidationConnections()
	{
		if(odsConnection!=null)
		{
			try {
				if(!odsConnection.isClosed())
				{
					odsConnection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(blmCbeConnection!=null)
		{
			try {
				if(!blmCbeConnection.isClosed())
				{
					blmCbeConnection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mmrCbeConnection!=null)
		{
			try {
				if(!mmrCbeConnection.isClosed())
				{
					mmrCbeConnection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(db2Connection!=null)
		{
			try {
				if(!db2Connection.isClosed())
				{
					db2Connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void CloseConnections(){
		try {
			if(dbConnection != null) {
				if(!dbConnection.isClosed()) dbConnection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logging.LogError("Failed to close connection");
			Assert.assertTrue(false, "Failed to close connection");
		}
		try {
			if(cbeDbConnection != null) {
				if(!cbeDbConnection.isClosed()) cbeDbConnection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logging.LogError("Failed to close CBE connection");
			Assert.assertTrue(false, "Failed to close CBE connection");
		}
	}
	
	// Returns result set after executing an Oracle DB query
	public static ResultSet executedbquery(String sql, String schema) throws SQLException
	{
		// Run the sql query passed in as parameter and return results		
		Connection connection = null;
		try {
			connection = getdbConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logging.LogError("Failed to get connection.  " + e.getMessage());
			Assert.assertTrue(false, "Failed to get ODS Connection");
		}	

		if(connection==null)
		{
			System.out.println("connection is null");
			Assert.assertTrue(false, "ODS Connection is null");
		}
		PreparedStatement sqlStatement=connection.prepareStatement(sql);
		ResultSet rs =sqlStatement.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String name = rsmd.getColumnName(1);
		int columns = rsmd.getColumnCount();
		return rs;
	}
	
	// Returns result set after executing an Oracle DB query
	public static ResultSet executeCBEquery(String sql) throws SQLException
	{
		// Run the sql query passed in as parameter and return results		
		Connection connection = null;
		
			try {
				connection = getCBEConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.out.println("The connection value is: "+connection);
		//connection = getdbConnection();	
		 logger.info("CBE Query: "+sql);
		 System.out.println("CBE Query: " + sql);
		if(connection==null)
		{
			System.out.println("connection is null");
		}
		ResultSet rs = null;
		PreparedStatement sqlStatement=connection.prepareStatement(sql);
		try {
			rs = sqlStatement.executeQuery();
		} catch (Exception ex) {
			Logging.LogError("ERROR trying to execute: " + sql + "\n Error Message: " + ex.getMessage());
			ex.printStackTrace();
		}
		return rs;
	}
	// Returns result set after executing an Oracle DB query
		public static ResultSet executeMMRquery(String sql) throws SQLException
		{
			// Run the sql query passed in as parameter and return results		
			Connection connection = null;
			
				try {
					connection = getMMRConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			System.out.println("The connection value is: "+connection);
			//connection = getdbConnection();	
			 logger.info("MMR Query: "+sql);
			 System.out.println("MMR Query: " + sql);
			if(connection==null)
			{
				System.out.println("connection is null");
			}
			ResultSet rs = null;
			PreparedStatement sqlStatement=connection.prepareStatement(sql);
			try {
				rs = sqlStatement.executeQuery();
			} catch (Exception ex) {
				Logging.LogError("ERROR trying to execute: " + sql + "\n Error Message: " + ex.getMessage());
				ex.printStackTrace();
			}
			return rs;
		}
	
	// Returns result set after executing a DB2 query
	public static ResultSet executedb2query(String sql, String schema) throws Exception
	{
		// Run the sql query passed in as parameter and return results			
		Connection connection = null;
		ResultSet rs = null;
		connection = getdb2Connection("DB2");
		//System.out.println("Sql = " + sql);
		try {
				rs = DBUtils.getresultset(connection, sql);
			} 
		catch (Exception e) 
		{
			logger.error("Exception in DB2 query execution:", e);	
			e.printStackTrace();
			Assert.assertTrue(rs != null, "Failed to create result set. Result set is null");
		}
		return rs;
	}
	
	// Returns Loyalty QAT Connection string
	public static Connection getdbConnection() throws Exception
	{
		if(dbConnection != null) {
			if (!dbConnection.isClosed()) {
				//Logging.LogInfo("!!! Using ExIsTiNg CoNnEcTiOn !!!");
				return dbConnection;
			}
			dbConnection = null;
		}
		try {
			//Logging.LogInfo("Creating Connection using: " + ConfigFileInfoClass.odsusername + ", ***********\n");
			Class.forName(ConfigFileInfoClass.odsdriver);
			DriverManager.setLoginTimeout(180);
			dbConnection = DriverManager.getConnection(ConfigFileInfoClass.odsuri, ConfigFileInfoClass.odsusername, GetRedString(ConfigFileInfoClass.odspassword));
		} catch (ClassNotFoundException e) {
			Assert.assertNull(e, "Could not create a connection");
			e.printStackTrace();
		}
		catch (Exception ex) {
			Assert.assertNull(ex, "Could not create a connection");
			ex.printStackTrace();
		}
		return dbConnection;
	}
	
	// Returns Loyalty CBE Connection string
	public static Connection getCBEConnection() throws Exception
	{
		if(cbeDbConnection != null) {
			if (!cbeDbConnection.isClosed()) {
				Logging.LogInfo("!!! Using ExIsTiNg CbeCoNnEcTiOn !!!");
				return cbeDbConnection;
			}
			cbeDbConnection = null;
		}
		try {
			//System.out.printf("Connecting using: %s %s\n", ConfigFileInfoClass.odsusername, ConfigFileInfoClass.odspassword);
			Logging.LogInfo("Creating new CBE Connection using: " + ConfigFileInfoClass.cbeUsername + ", ***********\n");
			Class.forName(ConfigFileInfoClass.cbeDriver);
			cbeDbConnection = DriverManager.getConnection(ConfigFileInfoClass.cbeUri, ConfigFileInfoClass.cbeUsername, GetRedString(ConfigFileInfoClass.cbePassword));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not create a connection");
			e.printStackTrace();
		}
		return cbeDbConnection;
	}

	// Returns Loyalty MMR Connection string
		public static Connection getMMRConnection() throws Exception
		{
			if(mmrDbConnection != null) {
				if (!mmrDbConnection.isClosed()) {
					Logging.LogInfo("!!! Using ExIsTiNg CbeCoNnEcTiOn !!!");
					return mmrDbConnection;
				}
				mmrDbConnection = null;
			}
			try {
				//System.out.printf("Connecting using: %s %s\n", ConfigFileInfoClass.odsusername, ConfigFileInfoClass.odspassword);
				Logging.LogInfo("Creating new MMR Connection using: " + ConfigFileInfoClass.mmrUsername + ", ***********\n");
				Class.forName(ConfigFileInfoClass.cbeDriver);
				mmrDbConnection = DriverManager.getConnection(ConfigFileInfoClass.mmrUri, ConfigFileInfoClass.mmrUsername, GetRedString(ConfigFileInfoClass.mmrPassword));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not create a connection");
				e.printStackTrace();
			}
			return mmrDbConnection;
		}
	
	
	// This method is used to connect to a particular schema if there are issues accessing FileConfig 
	// parameters . It uses dbtype and schema as parameters 
	
	public static synchronized Connection dbConnection(String dbtype, String dbSchema) throws Exception
	{
		Connection dbConnection = null;
		if (dbSchema.equalsIgnoreCase("ODS")) {
		    driver = ConfigFileInfoClass.odsdriver;
	        uri = ConfigFileInfoClass.odsuri;
	
		    username = ConfigFileInfoClass.odsusername;
		    password = GetRedString(ConfigFileInfoClass.odspassword);
		} else if (dbSchema.equalsIgnoreCase("DB2")) {
		    driver = ConfigFileInfoClass.db2driver;
	        uri = ConfigFileInfoClass.db2uri;
		    username = ConfigFileInfoClass.db2username;
		    password = GetRedString(ConfigFileInfoClass.db2password);			
		}
        try
	    {
        	Class.forName(driver);
        	dbConnection = DriverManager.getConnection(uri, username, password);
	    } catch (Exception e) {
	        logger.info("Exception while creating DB Connection: " + e);
	        throw new SQLException();
        }
       return dbConnection;
   }
		
	// Returns Loyalty DB2 Connection
	public static Connection getdb2Connection(String dbSchema) throws Exception 
	{
		
		Connection db2Connection = null;
		try {
			db2Connection = dbConnection("db2","DB2");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.assertTrue(db2Connection != null, "Failed to create connection.  db2Connection is null");
		}	

		if (db2Connection == null || db2Connection.isClosed()) 
		{
			Assert.assertTrue(db2Connection != null && !db2Connection.isClosed(), "db2Connection is null or db2Connection is closed.");
		}

		return db2Connection;
	}

		
	// Returns the Loyalty Test Environment value from env.config
	public static String getTestEnv()
	{		
		//String testEnv = FileConfig.getInstance().getStringConfigValue("db.loyaltytestenv");
		String testEnv=ConfigFileInfoClass.EnvVal;
		return testEnv;
	}
	
	// Processing Database results as a Map
	
	public static Map<String, List<Object>> resultSetToMap(ResultSet rs)
			throws SQLException, IOException {
		if (rs != null) {
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			Map map = new HashMap(columns);
			for (int i = 1; i <= columns; ++i) {
				map.put(md.getColumnName(i), new ArrayList());
			}
			while (rs.next()) {
				for (int i = 1; i <= columns; ++i) {
					if (rs.getObject(i) != null) {
						if (md.getColumnClassName(i).contains("CLOB")) {
							String clobToString = DBUtils.readClob((Clob) rs
									.getObject(i));
							((List) map.get(md.getColumnName(i)))
									.add(clobToString);
						} else {
							((List) map.get(md.getColumnName(i))).add(rs
									.getObject(i));
						}

					} else {
						((List) map.get(md.getColumnName(i)))
								.add(JSONObject.NULL);
					}
				}
			}

			logger.info("RESULT SET : " + map);

			return map;
		}

		return null;
	}

	// Processing Database results as a List
	
	public static LinkedList<Map<?, ?>> resultSetToList(ResultSet rs)
			throws SQLException {
		if (rs != null) {
			LinkedList result = new LinkedList();
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			
			int index = 0;
			int size =0;
			
			LinkedHashMap  resultMap = null;
			while (rs.next()) {
				resultMap = new LinkedHashMap();
				size=size+1;
				for (int i = 1; i <= columns; ++i) {
					
					if (rs.getObject(i) != null) {
						
						//System.out.println(" For each record set row the field values are:  "+ rs.getObject(i));
						resultMap.put(md.getColumnName(i), rs.getObject(i));
					} else {
						
						resultMap.put(md.getColumnName(i), null);
					}
				}
				//System.out.println("the index is "+index +"the result is "+resultMap);
				result.add(index,resultMap);
				//System.out.println("the result after adding "+result);
				index += 1;
			}
			if(size==0)
			{	//Prints 0
				resultMap = new LinkedHashMap();
				resultMap.put(md.getColumnName(1), "");
				result.add(0, resultMap);
				//System.out.println("Printing the first col header name:  "+result);
				return result;
			}
			else
			{
				//System.out.println("the result is "+result);
				return result;
			
			}
			
		}
			

		return null;
	}
	
	// To print database results as a Map
	
	public static Map<String, List<Object>> GetValuesFromDBAsMap(String sql,String env,
			String schema) throws Exception {
		ResultSet rs = executedbquery(sql,schema);
		return DBUtils.resultSetToMap(rs);
	}
	
	// Prints database results as a List
	public static LinkedList<Map<?, ?>> GetValuesFromDBAsList(String sql, String schema) throws Exception {
		LinkedList<Map<?, ?>> retval = null;
		ResultSet rs=null;
		ResultSet rs1=null;
		try {
			if(schema.equalsIgnoreCase("DB2")) {
				rs= executedb2query(sql,schema);
				Logging.LogInfo("Current Row = " + rs.getRow());
				retval = resultSetToList(rs);
				//rs.close();
				
			} else {
				Logging.LogInfo("Schema = " + schema);
				rs1 = executedbquery(sql,schema);
				retval = resultSetToList(rs1);
				//rs1.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		   if(rs != null) rs.close();
		   if(rs1 != null) rs1.close();			
		}
		return retval;
	}	
	
	public static int GetScalarInt (String sql, String schema) {
		int retval = 0;
		try {
			if(schema.equalsIgnoreCase("DB2")) {
				ResultSet rs;
				rs = executedb2query(sql,schema);
				while(rs.next()) {
					retval = rs.getInt(1);
				}
				rs.close();
			} else {
				ResultSet rs = executedbquery(sql,schema);
				while(rs.next()) {
					retval = rs.getInt(1);
				}
				rs.close();
			}
		} catch (Exception e) {
			Assert.assertNotNull(e, "Failed to get data from " + schema + " database");
			e.printStackTrace();
		}
		return retval;
	}
	public static String GetRedString(String BlackWord) throws Exception{
			String strData="";
			
			try {
				SecretKeySpec skeyspec=new SecretKeySpec("F00F0FF010010110".getBytes(),"Blowfish");
				Cipher cipher=Cipher.getInstance("Blowfish");
				cipher.init(Cipher.DECRYPT_MODE, skeyspec);
				byte[] decrypted=cipher.doFinal(Base64.getDecoder().decode(BlackWord.getBytes()));
				strData=new String(decrypted);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			}
			return strData;
		}

	
	public static String GetBlackWord(String input) throws Exception {
			String strData="";
			
			try {
				SecretKeySpec skeyspec=new SecretKeySpec("F00F0FF010010110".getBytes(),"Blowfish");
				Cipher cipher=Cipher.getInstance("Blowfish");
				cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
				byte[] encrypted=cipher.doFinal(input.getBytes());
				strData=new String(Base64.getEncoder().encode(encrypted));
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			}
			return strData;
		}
	

	public static Connection getOdsConnection() {
		return odsConnection;
	}


	public static void setOdsConnection(Connection odsConnection) {
		MmgDBHelper.odsConnection = odsConnection;
	}


	public static Connection getBlmCbeConnection() {
		return blmCbeConnection;
	}


	public static void setBlmCbeConnection(Connection blmCbeConnection) {
		MmgDBHelper.blmCbeConnection = blmCbeConnection;
	}


	public static Connection getMmrCbeConnection() {
		return mmrCbeConnection;
	}


	public static void setMmrCbeConnection(Connection mmrCbeConnection) {
		MmgDBHelper.mmrCbeConnection = mmrCbeConnection;
	}


	public static Connection getDb2Connection() {
		return db2Connection;
	}


	public static void setDb2Connection(Connection db2Connection) {
		MmgDBHelper.db2Connection = db2Connection;
	}

	
	}



	
