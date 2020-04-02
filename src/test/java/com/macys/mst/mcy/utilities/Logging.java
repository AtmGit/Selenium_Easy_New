package com.macys.mst.mcy.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class Logging {
	static Logger logger = Logger.getLogger(Logging.class.getName());
	static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");				

	public static void Log(String message) {
		Date currentDt = new Date();
		sdf.format(currentDt);

		System.out.println(sdf.format(currentDt) + " " + message);
		logger.debug(message);
	}

	public static void LogDebug(String message) {
		Date currentDt = new Date();
		sdf.format(currentDt);

		System.out.println(sdf.format(currentDt) + " " + message);
		logger.debug(message);
	}

	public static void LogInfo(String message) {
		Date currentDt = new Date();
		sdf.format(currentDt);

		System.out.println("Info: " + sdf.format(currentDt) + " " + message);
		logger.info(message);
	}

	public static void LogWarn(String message) {
		System.out.println("Warning: " + message);
		logger.warn(message);
	}

	public static void LogError(String message) {
		System.out.println("Error: " + message);
		logger.error(message);
	}
	
	public static void Log(String ClassName, String message) {
		System.out.println(ClassName + ": " + message);
		logger.debug(ClassName + ": " + message);
	}

	public static void LogDebug(String ClassName, String message) {
		System.out.println(ClassName + ": " + message);
		logger.debug(ClassName + ": " + message);
	}

	public static void LogInfo(String ClassName, String message) {
		System.out.println(ClassName + ": " + message);
		logger.info(ClassName + ": " + message);
	}

	public static void LogWarn(String ClassName, String message) {
		System.out.println(ClassName + ": " + message);
		logger.warn(ClassName + ": " + message);
	}

	public static void LogError(String ClassName, String message) {
		System.out.println(ClassName + ": " + message);
		logger.error(ClassName + ": " + message);
	}


}
