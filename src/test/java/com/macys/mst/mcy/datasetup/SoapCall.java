package com.macys.mst.mcy.datasetup;

import org.apache.log4j.Logger;

import com.manh.scope.RunWaveWebServiceClient;

public class SoapCall {
	static Logger logger = Logger.getLogger(SoapCall.class.getName());

	public static String runWave(String hostName, String waveType) {
		String waveNumber=null;
		logger.info("hostName "+hostName);
		logger.info("waveType "+waveType);
		waveNumber = RunWaveWebServiceClient.runWave(hostName, waveType);
		return waveNumber;
	}

}
