package com.macys.mst.mcy.stepdefinitions;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
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

import ch.qos.logback.core.joran.action.Action;

import com.macys.mst.mcy.pageobjects.AlertBootstrapObjects;
import com.macys.mst.mcy.pageobjects.FileDownloadObjects;
import com.macys.mst.mcy.pageobjects.InputFormsAjaxObjects;
import com.macys.mst.mcy.pageobjects.InputFormsCheckBoxObjects;
import com.macys.mst.mcy.pageobjects.InputFormsDropdownListObjects;
import com.macys.mst.mcy.pageobjects.InputFormsJqueryObject;
import com.macys.mst.mcy.pageobjects.InputFormsObjects;
import com.macys.mst.mcy.pageobjects.InputFormsRadioButtonObjects;
//import com.macys.mst.mcy.pageobjects.McePageObjects;
import com.macys.mst.mcy.pageobjects.InputFormsSimpleDemoObjects;
import com.macys.mst.mcy.pageobjects.JavaAlertObjects;
import com.macys.mst.mcy.pageobjects.ListBootObjects;
import com.macys.mst.mcy.pageobjects.ListDataObjects;
import com.macys.mst.mcy.pageobjects.ListJqueryObjects;
import com.macys.mst.mcy.pageobjects.ModalBootstrapObjects;
import com.macys.mst.mcy.pageobjects.ProgressBarDragObjects;
import com.macys.mst.mcy.pageobjects.ProgressBarModalObjects;
import com.macys.mst.mcy.pageobjects.ProgressBarsBootStrapObjects;
import com.macys.mst.mcy.pageobjects.ProgressBarsObjects;
import com.macys.mst.mcy.pageobjects.WindowPopupObjects;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.apache.tools.ant.filters.TokenFilter.ContainsString;
import org.fluentlenium.core.Alert;
import org.jbehave.core.annotations.*;

public class ListBoxPage {

	public static WebDriver driver = null;
	SeleniumConfigFileInfoClass configInfo = new SeleniumConfigFileInfoClass();
	static Logger logger = Logger.getLogger(ListBoxPage.class.getName());
	ListBootObjects listobject;
	ListJqueryObjects jqueryobject;
	ListDataObjects listdata;

	@BeforeStory
	public void setupnew() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		// configInfo.Browser(); Call the browser class to select a browser
		driver.get(SeleniumConfigFileInfoClass.mceURL);
		logger.info("Selenium home page is opened");

	}

	@Given("Select Bootstrap List Box from List Box Menu")
	public void ListBoxBootstrapLink() {

		listobject = new ListBootObjects(driver);
		listobject.ListBoxMenu().click();
		logger.info("List Box Menu Link is clicked");

		listobject.ListBootLink().click();
		logger.info("Bootstrap List Box link is clicked");

	}

	@Then("User validates bootstrap list box functionality")
	public void ListBootstrap() throws InterruptedException {

		// Fist step is to count the number of values appears in left and right side

		// Find the left side column size
		int leftcolsize = listobject.LeftColumnValues().size();
		System.out.println("Total Number of values on the left side is " + leftcolsize);

		// Find the right side column size
		int rightcolsize = listobject.RightColumValues().size();
		System.out.println("Total Number of values on the right side is " + rightcolsize);

		String leftname = null;

		// Get the names of the Left Column
		for (int i = 1; i <= leftcolsize; i++) {
			leftname = driver
					.findElement(
							By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[" + i + "]"))
					.getText();

			System.out.println(leftname);

		}

		System.out.println("--------------------------------------");

		// Get the names of the right side columns
		String rightname = null;

		for (int j = 1; j <= rightcolsize; j++) {
			rightname = driver
					.findElement(
							By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li[" + j + "]"))
					.getText();
			System.out.println(rightname);

		}
		// ======================================================================

		System.out.println("Select the value from the left box and move it to the right box");

		driver.findElement(By.xpath("//li[contains(text(),'bootstrap-duallist')]")).click();

		// Move the value Bootstrap to the right side
		listobject.RightSideMoveArrow().click();

		Thread.sleep(1000);
		int rightsize = listobject.RightColumValues().size();

		// The size of the right size should be 6
		System.out.println("Right column size is " + rightsize);

		// Check if the value appears on right side
		for (int j = 1; j <= rightsize; j++) {
			rightname = driver
					.findElement(
							By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li[" + j + "]"))
					.getText();

			if (rightname.contains("bootstrap")) {
				System.out.println("Found the value on the right side ----------------" + rightname);

			}

		}

		Thread.sleep(2000); // Move the value back to the left side
		listobject.LeftSideMoveArrow().click();

		// =============================================================================

		System.out.println("Seach the value and move it to the right and get the count");

		// Search for boot from the left side
		listobject.LeftSeachbox().sendKeys("boot");

		// Click on checkall button to move Boot to the right side
		listobject.RightSideCheckAll().click();

		// Click on Right arrow to move the search value to the right side
		listobject.RightSideMoveArrow().click();

		listobject.LeftSeachbox().clear();

		Thread.sleep(2000);
		int newrightcolsize = listobject.RightColumValues().size();
		System.out.println("Number of values on Right side column after moving is " + newrightcolsize);

		System.out.println("--------------------------------------");

		// Select all values from right side and move it to left side
		listobject.LeftSideCheckAll().click();

		// Move all the values to the left side
		listobject.LeftSideMoveArrow().click();

		Thread.sleep(2000);
		int newleftcolsize = listobject.LeftColumnValues().size();
		System.out.println("New Number from the Left side column after moving value is " + newleftcolsize);

		// =============================================================================

	}

	@Given("Select Jquery List Box from List Box Menu")
	public void JqueryListMenu() {
		jqueryobject = new ListJqueryObjects(driver);

		listobject.ListBoxMenu().click();
		logger.info("List Box Menu Link is clicked");

		jqueryobject.ListJqueryLink().click();
		logger.info("JQuery List Box link is clicked");

	}

	@Then("User validates Jquery list box functionality")
	public void ValidateJqueryList() {

		// First select all the values from Left Box to the Right box and move them back
		// to the Left Box
		int leftsize = jqueryobject.LeftSideSize().size();

		System.out.println("Left side column number of values " + leftsize);

		int rightsize = jqueryobject.RightSideSize().size();
		System.out.println("Right side column number of values " + rightsize);

		String leftside = null;

		for (int k = 1; k <= leftsize; k++) {

			leftside = driver.findElement(By
					.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/select[1]/option["
							+ k + "]"))
					.getText();

			System.out.println("All the values from left are:: " + leftside);

		}

		// Move all the values to the Right hand side and then print them from Right Box
		// and get the count
		jqueryobject.AddAllbutton().click();

		String rightside = null;

		for (int l = 1; l <= 15; l++) {

			rightside = driver.findElement(By
					.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/select[1]/option["
							+ l + "]"))
					.getText();

			System.out.println("All the values from Right are:: " + rightside);

		}

		int newrightsize = jqueryobject.RightSideSize().size();
		System.out.println("Right Box size before moving the value is:: " + rightsize
				+ " and new size of Right Box after moving is:: " + newrightsize);

		// Move all the values to Left side by clicking Remove All button
		jqueryobject.RemoveAllButton().click();

		// Now select 3 names and move it to Right box
		Select names = new Select(
				driver.findElement(By.xpath("//select[@class='form-control pickListSelect pickData']")));

		// Select the names to move from right to left
		names.selectByIndex(1);
		names.selectByIndex(3);
		names.selectByIndex(5);
		names.selectByIndex(7);

		// Click on Add button to add 4 names
		jqueryobject.AddButton().click();

		String newrightside = null;

		for (int l = 1; l < 5; l++) {

			newrightside = driver.findElement(By
					.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/select[1]/option["
							+ l + "]"))
					.getText();

			System.out.println("All the values from Right are:: " + newrightside);
		}
		// Select the class object from the Right box
		Select rightnames = new Select(
				driver.findElement(By.xpath("//select[@class='form-control pickListSelect pickListResult']")));

		// Select two values from the right box
		rightnames.selectByIndex(0);
		rightnames.selectByIndex(1);

		// Move them back to the Left box by clicking on Remove button
		jqueryobject.RemoveButton().click();

		int newleftsize = jqueryobject.LeftSideSize().size();

		System.out.println("Left Box size before moving the value is:: " + leftsize
				+ " and new size of Left Box after moving is:: " + newleftsize);
	}

	@Given("Select Data List from List Box Menu")
	public void DataListMenuLink() {

		listdata = new ListDataObjects(driver);
		listobject.ListBoxMenu().click();
		logger.info("List Box Menu Link is clicked");

		listdata.DataListMenu().click();
		logger.info("Click on data list menu link");

	}

	@Then("User validates Data list filter functionality")
	public void DataListFilterValidation() {

		// Filter the data with name and find that name's ph, email and title and print them
		
		// Search for the data with name
		listdata.DataSearch().sendKeys("Glenn");
		logger.info("Filter the data using name");

		// Count the number of blocks
		int noofblocks = listdata.BlockCount().size();
		System.out.println("Number of blocks on the page are: " + noofblocks);

		String name = null;
		String ph = null;
		String email = null;
		String title = null;

		for (int i = 1; i <= noofblocks; i++) {

			// Loop through all the filtered records and get their text
			name = driver.findElement(By.xpath(
					"/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/div[2]/div[" + i + "]/div[1]/h4[1]"))
					.getText();

			ph = driver.findElement(By.xpath(
					"/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/div[2]/div[" + i + "]/div[1]/span[1]"))
					.getText();

			email = driver.findElement(By.xpath(
					"/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/div[2]/div[" + i + "]/div[1]/span[2]"))
					.getText();

			title = driver.findElement(By
					.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/div[2]/div[" + i + "]/div[1]/p[1]"))
					.getText();

			if (name.contains("Glenn")) {

				System.out.println("Name is: " + name);
				System.out.println("Ph number is " + ph + "Email address is: " + email + "Title is: " + title);

			}

		}

	}

	@AfterStory
	public void AfterStory() {

		driver.quit();
		driver.close();
	}

}
