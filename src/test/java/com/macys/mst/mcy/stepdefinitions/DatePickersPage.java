package com.macys.mst.mcy.stepdefinitions;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.macys.mst.mcy.stepdefinitions.SeleniumConfigFileInfoClass;
import com.macys.mst.mcy.utilities.Logging;
import com.sun.tools.javac.util.List;
import com.macys.mst.mcy.pageobjects.DatepickerJqueryObjects;
import com.macys.mst.mcy.pageobjects.DatepickerObjects;
import com.macys.mst.mcy.pageobjects.InputFormsAjaxObjects;
import com.macys.mst.mcy.pageobjects.InputFormsCheckBoxObjects;
import com.macys.mst.mcy.pageobjects.InputFormsDropdownListObjects;
import com.macys.mst.mcy.pageobjects.InputFormsJqueryObject;
import com.macys.mst.mcy.pageobjects.InputFormsObjects;
import com.macys.mst.mcy.pageobjects.InputFormsRadioButtonObjects;
//import com.macys.mst.mcy.pageobjects.McePageObjects;
import com.macys.mst.mcy.pageobjects.InputFormsSimpleDemoObjects;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.WebDriverListener;
import com.macys.mst.mcy.stepdefinitions.SeleniumConfigFileInfoClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.apache.tools.ant.filters.TokenFilter.ContainsString;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.jbehave.core.annotations.*;

public class DatePickersPage {

	WebDriver driver;
	SeleniumConfigFileInfoClass configInfo = new SeleniumConfigFileInfoClass();
	static Logger logger = Logger.getLogger(DatePickersPage.class.getName());

	DatepickerObjects datepicker;
	DatepickerJqueryObjects jquerydate;

	@BeforeStory
	public void setup() throws IOException {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		// configInfo.Browser(); Call the browser class to select a browser
		driver.get(SeleniumConfigFileInfoClass.mceURL);
		logger.info("Selenium home page is opened");

		// Get the title of the Page
		logger.info("The Title of the page is: " + driver.getTitle());

		// Get the total number of links on the page
		logger.info("The Total number of links on the page: " + driver.findElements(By.tagName("a")).size());
	}

	@Given("Select Bootstrap Date Picker link from Date pickers menu")
	public void BootstrapfromMenu() {

		datepicker = new DatepickerObjects(driver);

		// Click on the Date Picker link and click on Bootstrap Date Picker
		datepicker.SelectFromMenu().click();
		logger.info("Click on Date Pickers link");

		datepicker.BootStrapLink().click();
		logger.info("Click on Bootstraper Date Picker Link");
	}

	@Then("User verifies date pickers functionality")
	public void ValidateDate() {

		// Verify Select date functionality
		datepicker.SelectADate().click();
		datepicker.SelectToday().click();
		datepicker.SelectADate().click();
		datepicker.ClearDate().click();
		// datepicker.SelectADate().clear();
		// datepicker.SelectADate().click();
		datepicker.SelectToday2().click();
		datepicker.SelectToday().click();
		logger.info("Select a date, Today and Clear functionalities are verified");
	}

	@Then("Select dates from start and end date")
	public void StartAndEndDate() {

		// Click on the Start Date box
		datepicker.startDate().click();
		logger.info("Click on Start Date Box");

		// While loop keep on executing until it becomes false
		while (!datepicker.PickAMonth().getText().contains("March 2020"))

		{

			datepicker.ForwardAMonth().click();

		}

		// Get the size of number of days for Nov
		int count = datepicker.PickADay().size();
		System.out.println("Total number of days: " + count);

		// Iterate through all the numbers until you get your expected number which is
		// 28 for this example
		for (int i = 0; i < count; i++) {

			String text = datepicker.PickADay().get(i).getText();

			if (text.equals("28")) {
				datepicker.PickADay().get(i).click();

				break;
			}

		}

		// Click on the end date box
		datepicker.endtDate().click();

		// While loop keep on executing until it becomes false or when September is
		// found
		while (!datepicker.PickAMonth().getText().contains("September 2020"))

		{

			datepicker.ForwardAMonth().click();

		}

		// Iterate through all the numbers until you get your expected number which is 6
		// for this example
		for (int i = 0; i < count; i++) {

			String text = datepicker.PickADay().get(i).getText();

			if (text.equals("6")) {
				datepicker.PickADay().get(i).click();
				break;
			}

		}

		logger.info("Start and End Dates are selected from the Calendar");

	}

	@Given("Select Jquery Date Picker link from Date pickers menu")
	public void JqueryDate() {

		jquerydate = new DatepickerJqueryObjects(driver);

		// Click on the Date Picker link and click on Bootstrap Date Picker
		datepicker.SelectFromMenu().click();
		logger.info("Click on Date Pickers link");

		jquerydate.JqueryLink().click();
		logger.info("Click on Jquery Date Picker Link");

	}

	@Then("User verifies date range picker functionality")
	public void SelectJqueryDates() {

		// Click on From dropdown
		jquerydate.clikFromBox().click();

		WebElement newmonth = jquerydate.SelectFromMonth();
		Select frommonth = new Select(newmonth);
		frommonth.selectByValue("1");

		int jcount = jquerydate.PickFromDay().size();

		System.out.println("Total number of days: " + jcount);

		// Iterate through all the numbers until you get your expected number which is
		// 28 for this example
		for (int i = 0; i < jcount; i++) {

			String text = jquerydate.PickFromDay().get(i).getText();

			if (text.equals("20")) {
				jquerydate.PickFromDay().get(i).click();

				break;
			}

		}

		// Click on To Dropdown

		jquerydate.clikToBox().click();

		WebElement newmonth1 = jquerydate.SelectFromMonth();
		Select frommonth1 = new Select(newmonth1);
		frommonth1.selectByValue("7");

		// question -> How to click next button and select a month from the dropdown.
		// Below while loop is not working
		// String test = jquerydate.SelectFromMonth().getText();
		// System.out.println(test);

		/*
		 * while(!jquerydate.SelectFromMonth().getText().equals("May"))
		 * 
		 * { jquerydate.ClickNext().click(); }
		 */

		int jcount1 = jquerydate.PickFromDay().size();

		System.out.println("Total number of days: " + jcount);

		// Iterate through all the numbers until you get your expected number which is
		// 28 for this example
		for (int i = 0; i < jcount1; i++) {

			String text = jquerydate.PickFromDay().get(i).getText();

			if (text.equals("5")) {
				jquerydate.PickFromDay().get(i).click();

				break;
			}

		}
		logger.info("To and From values selected from Jquery date picker");
	}

	@AfterStory
	public void AfterStory() {

		driver.quit();
		driver.close();

	}

}
