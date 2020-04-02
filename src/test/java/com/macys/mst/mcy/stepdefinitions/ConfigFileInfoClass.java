package com.macys.mst.mcy.stepdefinitions;

import com.macys.mst.artemis.config.FileConfig;
//import com.macys.mst.artemis.config.GetPasswordCyberArk;

	public class ConfigFileInfoClass {
		
		
		public static String EnvVal=FileConfig.getInstance().getStringConfigValue("Environment");	

		// MMG OMS URL	
		public static String launchUrl = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".mmgoms.launchpageurl");	
		public static String loginPageUrl = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".mmgoms.loginpageurl");
		
		
		// USERNAME
		public static String mmgomsUser = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".mmgoms.username");
		public static String mmgomsPass = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".mmgoms.password");
		
		//Cyberark
		public static String cyberarksafe = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".cyberark.safe");
		public static String cyberarkappid =  FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".cyberark.appid");
		public static String pwdobjectid = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".cyberark.pwdobjectid");
		//public static String cyberarkPwd = GetPasswordCyberArk.getpassword(cyberarksafe, cyberarkappid, pwdobjectid);	
		
		
		// DB
		public static String dbDriver = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".db.driver");
		public static String database = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".db.database");	
		public static String dbUser = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".db.username");
		public static String dbPass = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".db.password");
		public static String connecturi = FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".db.connecturi");
		
		// ODS Database Values
//		public static String odsdriverlocation="env." + EnvVal + ".ODS.classname";
		public static String odsdriver=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".ODS.classname");
		public static String odsuri=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".ODS.connecturi");
		public static String odsusername=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".ODS.name");
		public static String odspassword=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".ODS.encryptedpassword");

		// DB2 Database Values
		public static String db2driver=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".DB2.classname");
		public static String db2uri=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".DB2.connecturi");
		public static String db2database=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".DB2.database");
		public static String db2username=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".DB2.name");
		public static String db2password=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".DB2.encryptedpassword");
		
		// CBE
		public static String cbeDriver=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CBE.classname");
		public static String cbeUri=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CBE.connecturi");
		public static String cbeDatabase=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CBE.database");
		public static String cbeUsername=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CBE.name");
		public static String cbePassword=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CBE.encryptedpassword");
		
		// CLE
		public static String cleDriver=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CLE.classname");
		public static String cleUri=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CLE.connecturi");
		public static String cleDatabase=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CLE.database");
		public static String cleUsername=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CLE.name");
		public static String clePassword=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".CLE.encryptedpassword"); 
		
		// MMR
		public static String mmrDriver=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".MMR.classname");
		public static String mmrUri=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".MMR.connecturi");
		public static String mmrDatabase=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".MMR.database");
		public static String mmrUsername=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".MMR.name");
		public static String mmrPassword=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".MMR.encryptedpassword");
		

		
		// Wait Times and retry counts
		public static String waitTime=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".capt.waitTime");
		public static String retryCount=FileConfig.getInstance().getStringConfigValue("env." + EnvVal + ".capt.retryCount");	
}
