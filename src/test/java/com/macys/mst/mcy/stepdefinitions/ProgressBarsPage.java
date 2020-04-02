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

import com.macys.mst.mcy.pageobjects.InputFormsAjaxObjects;
import com.macys.mst.mcy.pageobjects.InputFormsCheckBoxObjects;
import com.macys.mst.mcy.pageobjects.InputFormsDropdownListObjects;
import com.macys.mst.mcy.pageobjects.InputFormsJqueryObject;
import com.macys.mst.mcy.pageobjects.InputFormsObjects;
import com.macys.mst.mcy.pageobjects.InputFormsRadioButtonObjects;
//import com.macys.mst.mcy.pageobjects.McePageObjects;
import com.macys.mst.mcy.pageobjects.InputFormsSimpleDemoObjects;
import com.macys.mst.mcy.pageobjects.ProgressBarDragObjects;
import com.macys.mst.mcy.pageobjects.ProgressBarsBootStrapObjects;
import com.macys.mst.mcy.pageobjects.ProgressBarsObjects;
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
import org.jbehave.core.annotations.*;

public class ProgressBarsPage {

	public static WebDriver driver = null;
	SeleniumConfigFileInfoClass configInfo = new SeleniumConfigFileInfoClass();
	static Logger logger = Logger.getLogger(ProgressBarsPage.class.getName());

	ProgressBarsObjects progressobject;
	ProgressBarsBootStrapObjects bootstrap;
	ProgressBarDragObjects draganddrop;

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

	@Given("Select Jquery download link from Progress Menu")
	public void ProgressBar() {

		progressobject = new ProgressBarsObjects(driver);

		progressobject.ProgressBarsLink().click();
		logger.info("Progeress Bar link is clicked from the Menu");

		progressobject.JqueryDownloadLink().click();
		logger.info("JQuery download page is opened");

	}

	@Then("User validates Jquery download progress bar")
	public void ValidateDownload() throws InterruptedException {

		// Click on Start Download button
		progressobject.StartDownload().click();
		Thread.sleep(8000);

		// Verify the status of download complete
		String text = progressobject.CurrentProgress().getText();
		System.out.println("The status of the download is " + text);

		// Thread.sleep(3000);

		// Click on Close button
		progressobject.CloseButton().click();

		// Click on Start Download button
		progressobject.StartDownload().click();

		Thread.sleep(2000);
		// Verify the status download
		String text2 = progressobject.CurrentProgress().getText();
		System.out.println("The status of the download is " + text2);

		// Click on Cancel button
		progressobject.CancelButton().click();

	}

	@Given("Select BootStrap Progress Bars link from Progress Menu")
	public void DragandDropLink() {

		bootstrap = new ProgressBarsBootStrapObjects(driver);
		progressobject.ProgressBarsLink().click();
		logger.info("Progeress Bar link is clicked from the Menu");

		bootstrap.BootStrapProgressBarsLink().click();
		logger.info("Progress Bar page is opened");
	}

	@Then("User validates progress bar")
	public void ValidateDragAndDrop() throws InterruptedException {

		// Click on the Download button
		bootstrap.Downloadbutton().click();

		Thread.sleep(1000);

		String firstprogress = bootstrap.ProgressBar().getText();
		System.out.println("The first download progress is " + firstprogress);

		Thread.sleep(20000);
		String secondprogress = bootstrap.ProgressBar().getText();
		System.out.println("The second download progress is " + secondprogress);
		;

	}

	@Given("Select Drag and Drop link from Progress Menu")
	public void SelectBootStrapLink() {

		draganddrop = new ProgressBarDragObjects(driver);

		progressobject.ProgressBarsLink().click();
		logger.info("Progeress Bar link is clicked from the Menu");

		draganddrop.DragandDropLink().click();
		logger.info("Drag and Drop slider page is opened");
	}

	@Then("User validates drag and drop slider")
	public void ValidateProgressBar() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Get the Default Value of the first Slider
		String firstdefaultvalue = draganddrop.DefaultValueText().getText();
		System.out.println("The Default Value of First Slider is " + firstdefaultvalue);

		// Get the initial Range value
		String rangevalue = draganddrop.RangeText().getAttribute("value");
		System.out.println("Initial value before moving slider " + rangevalue);

		wait.until(ExpectedConditions.visibilityOf(draganddrop.Slider()));

		/*
		 * Actions moveSlider = new Actions(driver); Actions action =
		 * moveSlider.dragAndDropBy(slider, 100, 0); action.perform();
		 */

		Actions builder = new Actions(driver);

		WebElement slider = draganddrop.Slider();

		// get the pixel count of Slider X axis and Y axis(267,25)

		Dimension sliderWidth = slider.getSize();
		System.out.println("The Width of the slider is " + sliderWidth);

		int numberOfRangeToDragTheSlider = 200;
		builder.moveToElement(slider).clickAndHold().moveByOffset(numberOfRangeToDragTheSlider, 10).release().perform();

		Thread.sleep(2000);

		// Question - How to get slider value after moving
		System.out.println("Value of Slider after moving " + rangevalue);

	}

	@AfterStory
	public void AfterStory() {

		driver.quit();
		driver.close();
	}

}
