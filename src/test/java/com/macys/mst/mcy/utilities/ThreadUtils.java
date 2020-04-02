package com.macys.mst.mcy.utilities;

import java.util.concurrent.TimeUnit;

public class ThreadUtils {
	public static void SleepForSeconds(int seconds) {
		Long millis=TimeUnit.SECONDS.toMillis(seconds);
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
