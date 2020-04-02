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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.macys.mst.mcy.stepdefinitions.SeleniumConfigFileInfoClass;
import com.macys.mst.mcy.utilities.Logging;
import com.opera.core.systems.internal.input.KeyEvent;
import com.sun.tools.javac.util.List;

import ch.qos.logback.core.joran.action.Action;

import com.macys.mst.mcy.pageobjects.AlertBootstrapObjects;
import com.macys.mst.mcy.pageobjects.DragDropObjects;
import com.macys.mst.mcy.pageobjects.DynamicDataObjects;
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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.apache.tools.ant.filters.TokenFilter.ContainsString;
import org.fluentlenium.core.Alert;
import org.jbehave.core.annotations.*;

public class OthersPage {

	public static WebDriver driver = null;
	SeleniumConfigFileInfoClass configInfo = new SeleniumConfigFileInfoClass();
	static Logger logger = Logger.getLogger(OthersPage.class.getName());
	DragDropObjects dragdrop;
	DynamicDataObjects dynamicdata;

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

	@Given("Select Drag and Drop from Others Menu")
	public void OthersMenu() {
		
		
		dragdrop= new DragDropObjects(driver);

		dragdrop.OthersMenu().click();
		logger.info("Others menu link is clicked");

	    dragdrop.DragDropLink().click();
	    logger.info("Drag And Drop link is clicked");
	    

	}

	@Then("User validates Drag and Drop functionality")
	public void ValidateDragAndDrop() throws InterruptedException, AWTException{

	int size = dragdrop.SourceDragSize().size();
	
	System.out.println("Number of droppable objects are: "+size);
	//WebElement move = null;
		
		Thread.sleep(3000);
	
	WebElement from = driver.findElement(By.xpath("//span[contains(text(),'Draggable 4')]"));
	
	WebElement to = driver.findElement(By.id("mydropzone"));
	
	Actions builder = new Actions(driver);
	
	builder.dragAndDrop(from, to).build().perform();
	
		
/*	for (int i = 1; i <= size; i++) {
		
		WebElement to = driver.findElement(By.id("mydropzone"));
		
		Actions builder = new Actions(driver);
		WebElement from = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/span[" + i + "]"));
		
		builder.clickAndHold(from).build().perform();
		
		//Thread.sleep(5000);
		
		builder.click(to).build().perform();
		builder.release(to).build().perform();
		
		builder.clickAndHold(from).release(to).build().perform();
		builder.dragAndDrop(from, to).build().perform();
		
			
		
	}*/
		
		
	}
	
	@Given("Select Dynamic Data Load from Others Menu")
	public void DynamicDataLink() {
		
		
	dynamicdata = new DynamicDataObjects(driver);

		dragdrop.OthersMenu().click();
		logger.info("Others menu link is clicked");

	    dynamicdata.DynamicDataLink().click();
	    logger.info("Dynamic Data link is clicked");
	   

	}

	@Then("User validates Dynamic Data Load functionality")
	public void ValidateDynamicData() throws InterruptedException {
		
	//Click Get New User Link for the first time
		dynamicdata.NewUserButton().click();
		
		Thread.sleep(2000);
			
		Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", dynamicdata.Image());
        if (!ImagePresent)
        {
             System.out.println("Image not displayed.");
        }
        else
        {
            System.out.println("Image displayed.");
            String text = dynamicdata.Name().getText();
            System.out.println(text);
        }
	
        dynamicdata.NewUserButton().click();
        Thread.sleep(2000);
        
    	Boolean ImagePresent2 = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", dynamicdata.Image());
        if (!ImagePresent2)
        {
             System.out.println("Image not displayed.");
        }
        else
        {
            System.out.println("Image displayed.");
            String text = dynamicdata.Name().getText();
            System.out.println(text);
        }
	
	}
	
	
	@AfterStory
	public void AfterStory() {

		driver.quit();
		driver.close();
	}

}
