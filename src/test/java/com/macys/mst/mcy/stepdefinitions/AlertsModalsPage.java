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

public class AlertsModalsPage {

	public static WebDriver driver = null;
	SeleniumConfigFileInfoClass configInfo = new SeleniumConfigFileInfoClass();
	static Logger logger = Logger.getLogger(AlertsModalsPage.class.getName());
	AlertBootstrapObjects alertmenu;
	ModalBootstrapObjects modalstrap;
	WindowPopupObjects windowpopup;
	ProgressBarModalObjects progressbar;
	JavaAlertObjects javaalert;
	FileDownloadObjects filedownload;

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

	@Given("Select Bootstrap Alers link from Alerts & Modals Menu")
	public void AlertModalsMenu() {

		alertmenu = new AlertBootstrapObjects(driver);

		alertmenu.AlertModalsMenu().click();
		logger.info("Alert & Modals link is clicked");

		alertmenu.BootStrapAlertLink().click();
		logger.info("BootStrap Alert Link is clicked");

	}

	@Then("User validates bootstrap alert messages")
	public void ValidateAlerts() throws InterruptedException {

		// Click on autoclose success message
		alertmenu.AutocloseSuccess().click();
		logger.info("Clicked on Autoclose Success Message");

		String message = alertmenu.SuccessMessage().getText();
		System.out.println("Displayed message is: " + message);

		Thread.sleep(6000);

		// alertmenu.SuccessMessage().isDisplayed();
		System.out.println("The message has closed automatically: " + alertmenu.SuccessMessage().isDisplayed());
		logger.info("If the message is false then the alert automaticallty disappeared");

		// =======================================================================================

		alertmenu.NormalSuccess().click();
		logger.info("Clicked on Normal Success Message");

		Thread.sleep(2000);

		// Question - Not getting the displayed text from the alert. Its just printing x
		// instead of a message
		String successtext = alertmenu.NormalSuccessText().getText();
		System.out.println("Displayed Normal message is: " + successtext);

		// Close the message alert box
		alertmenu.NormalSuccessClose().click();

		// Verify that box is disappeared
		System.out.println("The message has closed: " + alertmenu.NormalSuccessText().isDisplayed());
		logger.info("If the message is false then the alert window is closed");

		// ================================================================================================

		// Click on Normal Warning Message, Get the text and close it
		alertmenu.NormalWarning().click();
		logger.info("Clicked on Normal Warning Message");

		Thread.sleep(2000);
		String warningtext = alertmenu.NormalWarningText().getText();
		System.out.println("Displayed Normal message is: " + warningtext);

		// Close the message alert box
		alertmenu.NormalWarningClose().click();

		// Verify that box is disappeared
		System.out.println("The message has closed: " + alertmenu.NormalWarningText().isDisplayed());
		logger.info("If the message is false then the alert window is closed");

	}

	@Given("Select Bootstrap Modals link from Alerts & Modals Menu")
	public void BootStrapModalLink() {

		modalstrap = new ModalBootstrapObjects(driver);

		alertmenu.AlertModalsMenu().click();
		logger.info("Alert & Modals link is clicked");

		modalstrap.BootStrapModal().click();
		logger.info("Bootstrap Modals link is clicked");

	}

	@Then("User validates Single Modal bootstrap alert")
	public void ModalBootStrap() throws InterruptedException {
		// Click on Single Modal Button
		modalstrap.SingleModal().click();

		// Make sure to put sleep after clicking on the button
		Thread.sleep(5000);

		String title = modalstrap.ModalTitle().getText();
		System.out.println("The title of the page is " + title);

		String t1 = modalstrap.PopupText().getText();
		logger.info("Get the Text from the Modal Window");
		System.out.println("The text on the page is " + t1);

		// Close the Modal window
		modalstrap.CloseAlert().click();
		logger.info("Close the Modal Window");

		Thread.sleep(3000);

	}

	@Then("User validates Multiple Modal bootstrap alert")
	public void MultipleModalBootStrap() throws InterruptedException {

		// Click on Multiple Modal Button
		modalstrap.MultipleModalButton().click();

		Thread.sleep(5000);

		String mtitle = modalstrap.MultipleModalTitle().getText();
		System.out.println("The title of the page is " + mtitle);

		String t2 = modalstrap.MultipleModalBodyText().getText();
		logger.info("Get the Text from the Modal Window");
		System.out.println("The text on the body is: " + t2);

		// Click on Launch Model from Inside the Modal
		modalstrap.LaunchModelFromInside().click();
		logger.info("Click on Launch Model from Inside the Model window");

		// Get the details of another Sub Launch Window 
		Thread.sleep(5000);

		// Get the title of the launched window 
		String launchmodaltext = modalstrap.LaunchModalTitle().getText();
		System.out.println("The Launch Modal title is: " + launchmodaltext);

		// Get Launch Model text 
		String launchmodeltext =modalstrap.LaunchModalText().getText();
		System.out.println("The launch modal text is: " + launchmodeltext);

		// Close Launch Window 
		modalstrap.CloseLaunchWindow().click();

		Thread.sleep(3000);

		// Click on Save Changes after closing the launch window
		modalstrap.FirstModalSaveChanges().click();
		logger.info("Back to the Main Modal Window");

	}

	@Given("Select Window Popup from Alert Menu")
	public void WindowPopupLink() {

		windowpopup = new WindowPopupObjects(driver);

		alertmenu.AlertModalsMenu().click();
		logger.info("Alert & Modals link is clicked");

		windowpopup.WindowPopup().click();
		logger.info("Click on Window Popup link from Alert Menu");

	}

	@Then("User validates Single Window Popup")
	public void SingleWindowPopup() {
		
		  windowpopup.FollowOnTwitter().click(); 
		  Set<String> ids = driver.getWindowHandles();
		  Iterator<String> it = ids.iterator(); 
		  String twtparentid = it.next(); 
		  String twtChildId = it.next();
		  driver.switchTo().window(twtChildId);
		  System.out.println("The title of the Twitter Window is: " +
		  driver.getTitle()); // After switching the window
		  
		  System.out.println("*******************************************************************");
		  System.out.println("Switch to the Main Window after getting twitter title");
		  System.out.println("-------------------------------------------------------")
		  ;
		  
		  // Close the open twitter popup window 
		  driver.close();
		  
		  // Switch to the main window after getting Twitter title
		  driver.switchTo().window(twtparentid);
		  
		  windowpopup.LikeOnFacebook().click();
		  Set<String> ids1 = driver.getWindowHandles(); 
		  Iterator<String> itfb = ids1.iterator(); 
		  String facparentid = itfb.next();
		  String facChildId = itfb.next();
		  driver.switchTo().window(facChildId);
		  System.out.println("The title of the Facebook Window is: " +
		
				  
		  driver.getTitle()); // After switching the window 
		  System.out.println("*******************************************************************");
		  System.out.println("Switch to the Main Window after getting facebook title");
		  System.out.println("--------------------------------------------------------"
		  ); 
		  // close the open facebook popup window 
		  driver.close();
		  driver.switchTo().window(facparentid);
		  
		  System.out.println("We are now in Main Window of: " + driver.getTitle());
	}

	@Then("User validates Multiple Window popup")
	public void MultiplWindowPopup() {

		// Click on Follow Twitter and Facebook Link
		windowpopup.TwitterAndFB().click();
		Set<String> ids1 = driver.getWindowHandles();
		Iterator<String> itfb = ids1.iterator();
		String facparentid = itfb.next();
		String twitterChildId = itfb.next();
		String fbChildId = itfb.next();

		// First Switch to the first FB Window, Get the title and Close it
		driver.switchTo().window(twitterChildId);
		System.out.println("The title of the Window is: " + driver.getTitle());
		driver.close();

		System.out.println("--------------------------------------------------------");

		// Switch to the second Twitter Window. Get the title and close it
		driver.switchTo().window(fbChildId);
		System.out.println("The title of the Window is: " + driver.getTitle());
		driver.close();

		System.out.println("--------------------------------------------------------");

		// Move to the Main window and get the title of the main Window
		driver.switchTo().window(facparentid);
		System.out.println("We are now in Main Window of: " + driver.getTitle());

	}

	@Given("Select Progress Bar Modal from Alert Menu")
	public void ProgressModalLink() {

		progressbar = new ProgressBarModalObjects(driver);
		alertmenu.AlertModalsMenu().click();
		logger.info("Alert & Modals link is clicked");

		progressbar.ProgressBarModalLink().click();
		logger.info("Click on Progress Bar Modal Link");

	}

	@Then("User validates modal dialog with progress bar")
	public void ProgressModaPage() throws InterruptedException {

		// Click on First Show Dialog
		progressbar.ShowDialog1().click();

		Thread.sleep(1000);
		String firstbar = progressbar.ShowDialog1Progress().getText();
		System.out.println("The message on the first dialog progress bar is: " + firstbar);
		Thread.sleep(2000);

		progressbar.ShowDialog2().click();
		Thread.sleep(1000);
		String secondbar = progressbar.ShowDialog2Progress().getText();
		System.out.println("The message on the second dialog progress bar is: " + secondbar);

		Thread.sleep(3000);

		progressbar.ShowDialog3().click();
	
		Thread.sleep(5000);
		String thirdbar = progressbar.ShowDialog3Progress().getText();
		
		Thread.sleep(2000);
		
		
		System.out.println("The message on the third dialog progress bar is: " + thirdbar);
	}

	
	@Given("Select JavaScript Alert from Alert Menu")
	public void JavaNewAlert()
	{
	    javaalert = new JavaAlertObjects(driver);
		alertmenu.AlertModalsMenu().click();
		logger.info("Alert & Modals link is clicked");
		
		javaalert.JavaAlertLink().click();
		logger.info("Click on the Javascript Alert");
	}
	
	

	@Then("User validates Java Alert popups")
	public void JavaAlertValidation() throws InterruptedException {

		// Click the Alert Box
		javaalert.AlertBox().click();

		// Switching to Alert
		org.openqa.selenium.Alert alert = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage = alert.getText();
		System.out.println("The First Alert Message is " + alertMessage);

		// Click on Ok button or Accepting alert
		alert.accept();

		System.out.println("-----------------------------------------------------------");

		// Click on another alert box
		javaalert.ConfirmAlertBox().click();

		// Switching to Alert
		org.openqa.selenium.Alert alert2 = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage2 = alert2.getText();
		System.out.println("The Second Alert Message is " + alertMessage2);

		// Click on Ok button or Accepting alert
		alert2.dismiss();

		String message = javaalert.AlertConfirmMessage().getText();
		Assert.assertEquals("You pressed Cancel!", message);

		System.out.println("-----------------------------------------------------------");

		javaalert.PromptBox().click();
		org.openqa.selenium.Alert alert3 = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage3 = alert3.getText();
		System.out.println("The Third Alert Message is " + alertMessage3);

		// Enter the name and click ok
		alert3.sendKeys("Sam");
		alert3.accept();

		Thread.sleep(2000);
		// Get the message from box and validated the name entered

		String messagetext = javaalert.MessageName().getText();
		System.out.println("The message is: " + messagetext);

		Assert.assertEquals("You have entered 'Sam' !", messagetext);

	}

	@Given("Select file Download from Alert Menu")
	public void FileDownloadLink() {

		filedownload = new FileDownloadObjects(driver);
		alertmenu.AlertModalsMenu().click();
		logger.info("Alert & Modals link is clicked");

		filedownload.FileDownloadLink().click();
		logger.info("Click on File download link");

	}

	@Then("User validates File download functionality")
	public void FiledownloadValidation() {

		// Enter Multiple lines of text
		String myText = "India have blown hot and cold in the T20 format over the last 12 months, failing to "
				+ "\n win two successive series and managing only three series victories out of the seven"
				+ "\n they have played since November 2018. That they haven't fielded their best eleven"
				+ "\n as yet in this format was not lost on Virat Kohli when asked about India's fifth-placed "
				+ "\n ranking. However, ahead of the three-match T20I series against West Indies which starts on Friday (December 6)";
		myText = myText.replace("\n", Keys.chord(Keys.SHIFT, Keys.ENTER));

		// Enter text into the text box
		filedownload.TextBox().sendKeys(myText);
		logger.info("Enter text into text box");

		// Click on Generate file button
		filedownload.GenerateFile().click();

		// Click on download button to download the text into text file
		filedownload.Dowbnload().click();

	}

	@AfterStory
	public void AfterStory() {

		driver.quit();
		driver.close();
	}

}
