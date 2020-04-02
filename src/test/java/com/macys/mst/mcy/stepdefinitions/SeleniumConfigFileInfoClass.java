package com.macys.mst.mcy.stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.macys.mst.artemis.config.FileConfig;
//import com.macys.mst.artemis.config.GetPasswordCyberArk;

public class SeleniumConfigFileInfoClass {

	public static String mceURL = FileConfig.getInstance().getStringConfigValue("envURL");
	public WebDriver driver = null;

	public void Browser() throws IOException

	{

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\AutoProjects\\Artemis_Macys\\src\\test\\resources\\mceconfig.properties");

		prop.load(fis);

		if (prop.getProperty("browser").equals("firefox"))

		{

			driver = new FirefoxDriver();
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");

		}

		else if (prop.getProperty("browser").equals("chrome"))

		{

			driver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");

		}

		else

		{

			driver = new InternetExplorerDriver();
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\IEDriverServer.exe");

		}

	}
}
