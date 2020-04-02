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
import org.jbehave.core.annotations.*;

public class InputFormsPage {

	public static WebDriver driver = null;
	SeleniumConfigFileInfoClass configInfo = new SeleniumConfigFileInfoClass();
	static Logger logger = Logger.getLogger(InputFormsPage.class.getName());

	InputFormsSimpleDemoObjects inputMain;
	InputFormsCheckBoxObjects checkboxdemo;
	InputFormsRadioButtonObjects radiobuttondemo;
	InputFormsDropdownListObjects dropdownlist;
	InputFormsAjaxObjects ajexform;
	InputFormsObjects inputform;
	InputFormsJqueryObject jqueryform;

	@BeforeStory
	public void BeforeStory()  {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		// configInfo.Browser(); Call the browser class to select a browser
		driver.get(SeleniumConfigFileInfoClass.mceURL);
		logger.info("Selenium home page is opened");

	/*	// Get the title of the Page
		logger.info("The Title of the page is: " + driver.getTitle());

		// Get the total number of links on the page
		logger.info("The Total number of links on the page: " + driver.findElements(By.tagName("a")).size());*/
	}

	@Given("Select Simple Form Demo link from Input Form Menu")
	public void SimpleForm() {

		inputMain = new InputFormsSimpleDemoObjects(driver);

		inputMain.InputForms().click();
		logger.info("Input Form link is clicked from the Menu");

		inputMain.SimpleFormDemo().click();
		logger.info("Simple Form Demo page is opened");

	}

	@When("User enters and verifies the message in Single Input Field")
	public void SingleInput() throws InterruptedException {
		logger.info("User enters the message");
		Thread.sleep(2000);
		inputMain.EnterMessage().sendKeys("Selenium Test");
		inputMain.ShowMessage().click();

		String message = inputMain.verifyMessage().getText();
		Assert.assertEquals("Selenium Test", message);
		logger.info("Displeyed message is correct");
	}

	@Then("User enters and verifies the sum of two numbers:$elemTable")
	public void whenUserSearchesForProduct(ExamplesTable elemTable) throws InterruptedException {

		for (Map<String, String> rows : elemTable.getRows()) {

			inputMain.enterValueA().sendKeys(rows.get("ValueA"));
			inputMain.enterValueB().sendKeys(rows.get("ValueB"));
			logger.info("Got the two numbers from the story");

			// After entering the numbers into the box read those values

			String test1 = inputMain.enterValueA().getAttribute("value");
			String test2 = inputMain.enterValueB().getAttribute("value");

			System.out.println("First Number is " + test1);
			System.out.println("Second Number is " + test2);

			// Convert test1 and test2 from String to integer
			int value1 = Integer.valueOf(test1);
			int value2 = Integer.valueOf(test2);

			// Add two integer numbers
			int totalsum = value1 + value2;

			System.out.println("The total is " + totalsum);

			// Click on the button to get the total
			inputMain.GetTotalNumber().click();

			// Read the text of the total message
			String finaltotal = inputMain.verifyTotal().getText();

			// Convert the text of finaltotal from string to int
			int total = Integer.valueOf(finaltotal);

			// Compare two totals
			Assert.assertEquals(total, totalsum);

			logger.info("The total is verified");

		}

	}

	@When("User selects Checkbox Demo from Input Form Menu")
	public void Checkboxdemo()

	{
		checkboxdemo = new InputFormsCheckBoxObjects(driver);
		inputMain.InputForms().click();
		logger.info("Input Form link is clicked from the Menu");

		checkboxdemo.CheckboxDemo().click();
		logger.info("Check box Demo page is opened");

	}

	@Then("User selects Single Checkbox and verifies the message")
	public void SingleCheck() {
		checkboxdemo.SingleCheckboxDemo().click();
		logger.info("Signle Checkbox is Selected");

		String ExpectedMessage = "Success - Check box is checked";

		String ActualMessage = checkboxdemo.SingleCheckboxMessage().getText();

		Assert.assertEquals(ExpectedMessage, ActualMessage);
		logger.info("Displeyed Message is Verified");

	}

	@Then("User selects Multiple Checkboxes and verifies the functionality")
	public void MultipleCheck() {

		// Button Text before Clicking the Check All box
		String beforeclickingtext = checkboxdemo.CheckAllButton().getAttribute("value");
		logger.info("Button text before clicking: " + beforeclickingtext);

		// Click on Check All button to select all the boxes
		checkboxdemo.CheckAllButton().click();

		//// Button Text before Clicking the Check All box
		String afterclickingtext = checkboxdemo.CheckAllButton().getAttribute("value");
		logger.info("Button text After clicking: " + afterclickingtext);

		Assert.assertEquals("Uncheck All", afterclickingtext);
		logger.info("Button text is verified after clicking Check All");

		// Verify all the boxes are checked
		logger.info("The checkbox1's selection state is - " + checkboxdemo.MultipleBox1().isSelected());
		logger.info("The checkbox2's selection state is - " + checkboxdemo.MultipleBox2().isSelected());
		logger.info("The checkbox3's selection state is - " + checkboxdemo.MultipleBox3().isSelected());
		logger.info("The checkbox4's selection state is - " + checkboxdemo.MultipleBox4().isSelected());

		// Uncheck the all the checkboxes
		// checkboxdemo.MultipleBox1().click();
		// checkboxdemo.MultipleBox2().click();
		checkboxdemo.MultipleBox3().click();
		// checkboxdemo.MultipleBox4().click();

		Assert.assertEquals("Check All", beforeclickingtext);
		logger.info("Button text is verified after Unchecking all the boxes");

	}

	@When("User selects Radio Button Demo from Input Form Menu")
	public void RadioButtonCheck() {

		radiobuttondemo = new InputFormsRadioButtonObjects(driver);

		inputMain.InputForms().click();
		logger.info("Input Form link is clicked from the Menu");

		radiobuttondemo.RadioButtonDemo().click();
		logger.info("Radio Button Demo page is opened");

	}

	@Then("User selects radio and group radio buttons and verifies the functionality")
	public void SingleAndGroupRadioButtonCheck() {

		radiobuttondemo.femaleButton().click();
		logger.info("Female radio button is selected");

		radiobuttondemo.getcheckedValue().click();
		logger.info("Click on Get Checked button");

		String radiomessage = radiobuttondemo.verifyRadioMessage().getText();

		Assert.assertEquals("Radio button 'Female' is checked", radiomessage);
		logger.info("Displeyed radio button message is correct");

		// Select radio button if Male text is found
		int count = radiobuttondemo.selectSex().size();

		for (int i = 0; i < count; i++) {

			String text = radiobuttondemo.selectSex().get(i).getAttribute("value");

			if (text.equals("Male")) {

				radiobuttondemo.selectSex().get(i).click();
				logger.info("Correct Male button is selected");
			}

		}

		int agecount = radiobuttondemo.selectAgeGroup().size();

		for (int i = 0; i < agecount; i++) {

			String text = radiobuttondemo.selectAgeGroup().get(i).getAttribute("value");
			System.out.println(text);

			if (text.equals("5 - 15")) {

				radiobuttondemo.selectAgeGroup().get(i).click();
				logger.info("Correct age button is selected");
			}

		}

		radiobuttondemo.getValue().click();
		logger.info("Get value button is clicked");

		String text123 = radiobuttondemo.radioMessage().getText();
		System.out.println(text123);

		// Verify the message
		assertTrue(text123.contains("Sex : Male") && text123.contains("Age group: 5 - 15"));

		logger.info("Displeyed Group radio button message is correct");

	}

	@When("User selects Dropdown List from Input Form Menu")
	public void DropdownListCheck() {

		dropdownlist = new InputFormsDropdownListObjects(driver);

		inputMain.InputForms().click();
		logger.info("Input Form link is clicked from the Menu");

		dropdownlist.SelectDropdownList().click();
		logger.info("Dropdown page is opened");

	}

	@Then("Select a day from the dropdown")
	public void whenUserSearchesForProduct(@Named("SelectADay") String SelectADay) {

		logger.info("Searching for " + SelectADay);

		WebElement day1 = dropdownlist.SelectFromDropdown(SelectADay);
		Select FromDay = new Select(day1);
		FromDay.selectByValue(SelectADay);
		logger.info("Day is Selected from the dropdown");

	}

	@Then("Select multiple States from the dropdown")
	public void SelectMultipleStates() {

		// Select values from dropdown using Action class
		WebElement state1 = dropdownlist.SelectStateFromDropdown();
		Select FromState = new Select(state1);
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(FromState.getOptions().get(1)).click(FromState.getOptions().get(4))
				.click(FromState.getOptions().get(6)).keyUp(Keys.CONTROL);

		builder.build().perform();
		logger.info("States are Selected from the dropdown");

		// Click on First Selected button
		dropdownlist.firstSelected().click();
		WebElement option1 = FromState.getFirstSelectedOption();
		String selectedOption = option1.getText();
		// System.out.println(selectedOption);

		// Get the text of the message
		String first = dropdownlist.verifySelectedMessage().getText();

		// Compare if expected and actual state is correct.
		Assert.assertEquals("First selected option is : " + selectedOption, first);

		// Click on All Selected Button
		dropdownlist.allSelected().click();

		java.util.List<WebElement> alloptions = FromState.getAllSelectedOptions();

		for (WebElement temp : alloptions) {
			String label_text = temp.getText();
			dropdownlist.allSelected().sendKeys(label_text);
			dropdownlist.allSelected().sendKeys("/n");
			System.out.println("Selected Option " + label_text);
		}

		String all = dropdownlist.verifySelectedMessage().getText();
		System.out.println(all);

		// ????????????Question how to assert multiple string. in above
		// example?????????????????
	}

	@When("User selects Ajex Form from Input Form Menu")
	public void SelectAjecxFormFromMenu() {

		ajexform = new InputFormsAjaxObjects(driver);

		inputMain.InputForms().click();
		logger.info("Input Form link is clicked from the Menu");

		ajexform.ajexMenu().click();
		logger.info("Ajax page is opened");

		String title = ajexform.GetAjexTitle().getText();
		logger.info("Ajax form title is " + title);
	}

	@Then("User validates Ajex functionality")
	public void AjexFormSubmit(@Named("AjaxName") String AjaxName, @Named("AjexComment") String AjexComment)
			throws InterruptedException {

		logger.info("Searching for " + AjaxName + " AND " + AjexComment);
		ajexform.EnterAjexName(AjaxName).sendKeys(AjaxName);
		ajexform.EnterAjexctComment(AjexComment).sendKeys(AjexComment);

		// Click on Submit button
		ajexform.ajexSubmit().click();

		Thread.sleep(2000);

		// Verify the message after clicking on submit
		String ajexmessage = ajexform.AjextSubmitMessage().getText();
		System.out.println(ajexmessage);
		// assertTrue(ajexmessage.contains("Form submitted"));

		Assert.assertEquals("Form submited Successfully!", ajexmessage);
		logger.info("Ajex form submiited and the message is: " + ajexmessage);
	}

	@When("User selects Input Forms from the Input Form Menu")
	public void SelectInputFormFromMenu() {
		inputform = new InputFormsObjects(driver);

		inputMain.InputForms().click();
		logger.info("Input Form link is clicked from the Menu");

		inputform.inputFormMenu().click();
		// inputMain.InputForms().click();
		logger.info("Input Form Page is opened");

	}

	@Then("User validates Input form functionality")
	public void InputFormValidation(@Named("Fname") String Fname, @Named("Lname") String Lname,
			@Named("FEmail") String FEmail, @Named("FPh") String FPh,

			@Named("FAddress") String FAddress, @Named("FCity") String FCity, @Named("FState") String FState,
			@Named("Fzip") String Fzip, @Named("Fwebsite") String Fwebsite, @Named("Fdescrition") String Fdescrition)

	{
		inputform = new InputFormsObjects(driver);
		logger.info("Searching for " + Fname + " AND " + Lname);
		inputform.InputFName(Fname).sendKeys(Fname);
		inputform.InputLName(Lname).sendKeys(Lname);
		inputform.InputEmail(FEmail).sendKeys(FEmail);
		inputform.InputPhone(FPh).sendKeys(FPh);
		inputform.InputAddress(FAddress).sendKeys(FAddress);
		inputform.InputCity(FCity).sendKeys(FCity);

		logger.info("Searching for " + FState);

		WebElement state1 = inputform.InputState(FState);
		Select FromState = new Select(state1);
		FromState.selectByVisibleText(FState);
		logger.info("State is Selected from the dropdown");

		inputform.InputZip(Fzip).sendKeys(Fzip);
		inputform.InputWebsite(Fwebsite).sendKeys(Fwebsite);

		// int count = radiobuttondemo.selectSex().size();

		int count2 = inputform.InputHostingSelect().size();

		for (int i = 0; i < count2; i++) {

			String text = inputform.InputHostingSelect().get(i).getAttribute("value");

			if (text.equals("no")) {

				inputform.InputHostingSelect().get(i).click();
				logger.info("Correct No button is selected");
			}
		}

		inputform.InputDescription(Fdescrition).sendKeys(Fdescrition);
		inputform.InputSend().click();

		logger.info("Input form submitted");

	}

	@When("User selects Jquery Link from the Input Form Menu")
	public void SelectJqueryFromMenu() {
		jqueryform = new InputFormsJqueryObject(driver);

		inputMain.InputForms().click();
		logger.info("Input Form link is clicked from the Menu");

		jqueryform.inputFormJqueryMenu().click();
		// inputMain.InputForms().click();
		logger.info("Jquery Form Page is opened");
	}

	@Then("User validates Jquery form functionality")
	public void JqueryDropdownValidation(@Named("FromFile") String FromFile) {

		jqueryform.JquerySelectCountry().click();
		jqueryform.JquerySearchCountry().clear();
		jqueryform.JquerySearchCountry().click();

		WebElement searchdropdown = jqueryform.JquerySearchCountry();

		searchdropdown.sendKeys("Jap");

		searchdropdown.sendKeys(Keys.ARROW_DOWN);
		searchdropdown.sendKeys(Keys.ENTER);

		jqueryform.JquerySelectStates().click();

		// Select multiple State
		WebElement searchmultistates = jqueryform.JquerySelectStates();

		searchmultistates.sendKeys("Alaba");

		searchmultistates.sendKeys(Keys.ARROW_DOWN);
		searchmultistates.sendKeys(Keys.ENTER);

		searchmultistates.sendKeys("Georgia");

		searchmultistates.sendKeys(Keys.ARROW_DOWN);
		searchmultistates.sendKeys(Keys.ENTER);

		searchmultistates.sendKeys("Alaska");

		searchmultistates.sendKeys(Keys.ARROW_DOWN);
		searchmultistates.sendKeys(Keys.ENTER);

		logger.info("Multiple states selected");

		// jqueryform.JqueryDisableDrodown().click();

		jqueryform.JquerySelectAFile(FromFile).click();

		WebElement file1 = jqueryform.JquerySelectAFile(FromFile);
		Select fromfile = new Select(file1);
		fromfile.selectByVisibleText(FromFile);

		logger.info("Value from the dropdown is selected");

		inputMain.GoToHome().click();
		logger.info("Go To Homepage");

		inputMain.homePageIsOpened();
		logger.info("We are on HomePage now");

	}

	@AfterStory
	public void AfterStory() {

		//driver.quit();
		driver.close();
	}

}
