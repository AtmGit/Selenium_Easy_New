package com.macys.mst.mcy.stepdefinitions;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import com.macys.mst.mcy.pageobjects.TableDataSearchObjects;
import com.macys.mst.mcy.pageobjects.TableFilterObjects;
import com.macys.mst.mcy.pageobjects.TablePaginationObjects;
import com.macys.mst.mcy.pageobjects.TableSortObjects;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.WebDriverListener;
import com.macys.mst.mcy.stepdefinitions.SeleniumConfigFileInfoClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.apache.tools.ant.filters.TokenFilter.ContainsString;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.jbehave.core.annotations.*;

public class TablePage {

	WebDriver driver;
	SeleniumConfigFileInfoClass configInfo = new SeleniumConfigFileInfoClass();
	static Logger logger = Logger.getLogger(TablePage.class.getName());

	TablePaginationObjects tablepagination;
	TableDataSearchObjects datasearch;
	TableFilterObjects tablefilter;
	TableSortObjects tablesort;

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

	}

	@Given("Select Table pgination link from Table menu")
	public void BootstrapfromMenu() {

		tablepagination = new TablePaginationObjects(driver);

		// Click on the Table Menu and then click on the Table Pagination Link
		tablepagination.TableMenu().click();
		logger.info("Click on Table Menu Link");

		tablepagination.TablePaginationLink().click();
		logger.info("Click on Table Pagination Link");
	}

	@Then("User finds total number of columns their names and rows from first page")
	public void NumberofColsAndRows() throws InterruptedException {

		// Find the total number of columns appear on the table.
		int colsize = tablepagination.TotalColumns().size();
		System.out.println("Total Number of columns are " + colsize);

		// Find the total number of rows appear on the table.
		int rowsize = tablepagination.TotalRows().size();
		System.out.println("Total Number of rows are " + rowsize);

		// Find names of all the columns. Define a String array
		ArrayList<String> colnames = new ArrayList<String>();
		for (int j = 1; j < colsize; j++) {

			String columnNamesSelector = "/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/table[1]/thead[1]/tr[1]/th["
					+ j + "]";

			java.util.List<WebElement> colnamesElements = driver.findElements(By.xpath(columnNamesSelector));

			// Question: How to select only first

			System.out.println("Selected the Column " + j);
			// System.out.println("text is " + text);

			for (WebElement namesElements2 : colnamesElements) {

				String text2 = namesElements2.getText().trim();
				System.out.println("The column heading is " + text2);

				/*
				 * colnames.add(namesElements2.getText()); System.out.println(colnames);
				 */

			}
		}
		logger.info("Value of all the columns are ");
	}

	@Then("Get the value of specific rows and columns")
	public void SpecficRowAndColumn() {
		String row4column3 = tablepagination.SpeficRowColumn().getText();
		System.out.println("The value of row 4 and column 3 is: " + row4column3);

	}

	@Then("User moves to all the pages and get the text of first columns")
	public void AllColumnsfromFirstPage() throws InterruptedException {

		// Get the Size of the page number buttons and previous and next buttons
		int paginationsize = tablepagination.PaginationForLoop().size();

		System.out.println("The number of pages are " + paginationsize);

		ArrayList<String> names = new ArrayList<String>();

		for (int i = 2; i < paginationsize; i++) {

			// Get common xpath/css for the page buttons and loop through them one by one
			String paginationSelector = "/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[" + i + "]/a[1]";
			// driver.findElement(By.xpath(paginationSelector)).click();

			System.out.println("----------------Selected the page---------------- " + i);
			Thread.sleep(1000);

			// Get the names of all the elements for page i
			java.util.List<WebElement> namesElements = tablepagination.FirstColumnNumbers();

			// Getting 13 values from below
			System.out.println(namesElements.size());

			// loops through all the names and add those name to the defined names list
			for (WebElement namesElements1 : namesElements) {

				names.add(namesElements1.getText());
				System.out.println("The value of the first column:  " + names);
				driver.findElement(By.xpath(paginationSelector)).click();
			}

		}

		logger.info("Total number of names on all 3 pages");

	}

	@Given("Select Table Data Search link from Table menu")
	public void DataSearchLink() {

		datasearch = new TableDataSearchObjects(driver);

		// Click on the Table Menu and then click on the Table Pagination Link
		tablepagination.TableMenu().click();
		logger.info("Click on Table Menu Link");

		datasearch.TableDataSearchLink().click();
		logger.info("Click on Table Data Search Link");
	}

	@Then("User verifies search functionality:$elemTable")
	public void SearchesFromTable(ExamplesTable elemTable) {

		int index = 0;
		WebElement baseTable = driver.findElement(By.id("task-table"));
		java.util.List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		String test = tableRows.get(index).getText();
		System.out.println("The Heade name of the columns are: " + test);
		logger.info("Header names of the columns");

		logger.info("Click on Table Data Search Link");

		// Find the total number of columns appear on the table.
		int colsize1 = datasearch.TotalColumns().size();
		System.out.println("Total Number of columns are " + colsize1);

		// Find the total number of rows appear on the table.
		int rowsize1 = datasearch.TotalRows().size();
		System.out.println("Total Number of rows are " + rowsize1);

		String name = null;

		// Print all the values from the cell
		for (int j = 1; j <= rowsize1; j++) {

			{

				for (int k = 1; k <= colsize1; k++)

				{
					name = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[" + j	+ "]/td[" + k + "]"))
							.getText();

					System.out.println("The name of row  " + j + " And column: " + k + " is " + name);
				}

			}

		}

		// Get the filter value from the story
		String test1 = null;
		for (Map<String, String> rows : elemTable.getRows()) {

			datasearch.DataSearch().sendKeys(rows.get("SearchText"));

			test1 = datasearch.DataSearch().getAttribute("value");

			logger.info("Entered the search text");
			System.out.println("The search text is ---------------" + test1);

			// Clear the previous search
			//datasearch.DataSearch().clear();
		}

		// Read all the values from the table
		java.util.List<WebElement> FilterResult = datasearch.AllValuesFromTable();

		// Loop through each values
		for (WebElement status : FilterResult) {

			String findvalue = status.getText().trim(); // trim() removes leading and trailing whitespaces

			// Find the filtered value
			if (findvalue.contains(test1)) {
				System.out.println("Found the expected Filtered value of " + findvalue);

			}

			else {
				System.out.println("Filtered value not found");
			}

		}

		// Clear the previous search
			datasearch.DataSearch().clear();
			datasearch.DataSearch().sendKeys(Keys.ENTER);
	
	}

	@Then("User clicks on filter button and verifies fiter criteria:$elemTable1")

	public void VerifyFilter(ExamplesTable elemTable1) {

		// Find the total number of columns appear on the table.
		int colsize2 = datasearch.NumberofColumns().size();
		System.out.println("Total Number of columns are " + colsize2);

		// Find the total number of rows appear on the table.
		int rowsize2 = datasearch.NumberofRows().size();
		System.out.println("Total Number of rows are " + rowsize2);

		// Print all the values from the cell
		for (int l = 1; l <= rowsize2; l++) {

			{

				for (int m = 1; m <= colsize2; m++)

				{
					String name1 = driver.findElement(
							By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[" + l
									+ "]/td[" + m + "]"))
							.getText();
					System.out.println("The name of row " + l + "And column: " + m + " is " + name1);
				}

			}

		}
		logger.info("Printed all the values from the cell");

		datasearch.SelectFilter().click();

		// Get the filter value from the story
		
		String allvalues = null;
		
		for (Map<String, String> newrows : elemTable1.getRows()) {

			datasearch.UsernameSearch().sendKeys(newrows.get("Username"));

			allvalues  = datasearch.UsernameSearch().getAttribute("value");

			logger.info("Entered the search text");
			System.out.println("The search value is ---------------------------" + allvalues);

			// Enter different filter values and verify the results
		//	datasearch.UsernameSearch().clear();

		/*	datasearch.LastNameSearch().sendKeys(newrows.get("LName"));

			datasearch.LastNameSearch().clear();

			datasearch.FirstColumnSearch().sendKeys(newrows.get("Number"));

			datasearch.FirstColumnSearch().clear();
*/
		}

		//datasearch.SelectFilter().click();
		
		
		// Read all the values from the table
		java.util.List<WebElement> listedFilterResults = datasearch.ListedUsers();

		// Loop through each values
		for (WebElement getvalue : listedFilterResults) {

			String findvalue2 = getvalue.getText().trim(); // trim() removes leading and trailing whitespaces

			// Find the filtered value
			if (findvalue2.contains(allvalues)) {
				System.out.println("Found the expected Filtered value of *********" + findvalue2);

			}

			else {
				System.out.println("Filtered value not found");
			}

		}
        
		// Clear the search
		datasearch.SelectFilter().clear();
		datasearch.SelectFilter().sendKeys(Keys.ENTER);
	
	}


	@Given("Select Table Filter link from Table menu")
	public void TableFilterLink() {

		tablefilter = new TableFilterObjects(driver);

		// Click on the Table Menu and then click on the Table Filter Link
		tablepagination.TableMenu().click();
		logger.info("Click on Table Menu Link");

		tablefilter.TableFilterLink().click();
		logger.info("Click on Table Filter Link");
	}

	@Then("User verifies filter functionality")

	public void FilterFunctionality() {

		// Select any color from below to filter one colour
		// tablefilter.RedFilter().click();

		tablefilter.GreenFilter().click();

		// tablefilter.OrangeFilter().click();

		// tablefilter.AllFilter().click();

		// Get all the colour names in a List using common xpath
		java.util.List<WebElement> statusFilterResult = driver
				.findElements(By.xpath("//tr//td[3]//div[1]//div[1]//h4[1]//span[1]"));

		// Loop through each col
		for (WebElement status : statusFilterResult) {

			String text1 = status.getText().trim(); // trim() removes leading and trailing whitespaces

			// Print all the colours
			System.out.println("Print Colour " + text1);

			// Find the filtered color
			if (text1.equals("(Green)")) {
				System.out.println("Found the expected Filtered color is ****************" + text1);

			}

			else {
				System.out.println("Filtered color not found");
			}

		}

	}

	@Given("Select Table Sort link from Table menu")
	public void SortFunctionality() {
		
		tablesort = new TableSortObjects(driver);
		
		// Click on the Table Menu and then click on the Table Pagination Link
			tablepagination.TableMenu().click();
			logger.info("Click on Table Menu Link");	
    
		// Click on Table Sort Link 
			tablesort.TableSortLink().click();
	}
	
	@Then("User verifies Sort functionality")
	public void VerifySortFunctionality() {
		
		//	Click on Show dropdown and select number of entries
		WebElement selectvalue1 = tablesort.SelectShow();
		Select name2 = new Select(selectvalue1);
		name2.selectByValue("50");
		
		String temp = null;
				
	// Retrieve the List of Items in the Table before Sorting and Store into Array
		java.util.List<WebElement> tdList = tablesort.ColumnValues();
		String strArray[] = new String[tdList.size()];
		for(int i =0;i<tdList.size();i++)
		{
			System.out.println(tdList.get(i).getText());
			strArray[i]=tdList.get(i).getText();
		}
		
		// Sort the Array by Swapping the Elements
		for (int i = 0; i < strArray.length; i++) 
		{
			for (int j = i + 1; j < strArray.length; j++) 
			{
				if (strArray[i].compareTo(strArray[j])>0) 
				{
					temp = strArray[i];
					strArray[i] = strArray[j];
					strArray[j] = temp;
				}
			}
		}
				
	// Printing the Values after sorting / swapping
		System.out.println("##################Sorted values in the Array####################");
		for (int i = 0; i < strArray.length; i++) 
		{
		 
					System.out.println(strArray[i]);
		 }
	
		
	//	Click on the column that needs to be sorted
	//	tablesort.PositionSort().click();
	//	tablesort.NameSort().click();
		tablesort.OfficeSort().click();
	//	tablesort.AgeSort().click();
	//	tablesort.DateSort().click();
	//	tablesort.SalarySort().click();
		
		tdList = tablesort.ColumnValues();
		
		
	// After Sorting printing the values in the List
		
		System.out.println("################# After Sorting Printing the Values from the List ###################");
		for (int i = 0; i < strArray.length; i++) 
		{
		 
					System.out.println(tdList.get(i).getText());
		 }
       
		System.out.println("===========================================================================");
		
	// Comparison between the List and Sorted Array
		
		boolean result = true;
		for (int i=0;i<strArray.length;i++) {
			System.out.println(strArray[i]+" ### "+tdList.get(i).getText());
			
			if(strArray[i].compareTo(tdList.get(i).getText())!=0) {
				result=false;
				System.out.println("Elements in the table are not sorted ");
				break;
			}
		}
		System.out.println("Elements in the dynamic table are sorted::"+ result);
	
		
	}
	
	@Then("User verifies the search functionality")
	public void SortDateFunctionality()
	{
		// Enter the search criteria in the search box
		tablesort.EnterSearch().sendKeys("New York");
		
		// Get all the values from the table
		
		java.util.List<WebElement> alltablevalues = tablesort.TableVlaues();
		
		// Loop through each cell to get the values
				for (WebElement status : alltablevalues) {

					String textvalue = status.getText().trim();  // trim() removes leading and trailing whitespaces

					// Print all the colours
					//System.out.println("The value of the cell is " + textvalue);

		           // Find the filtered color
					if (textvalue.equals("New York")) {
						System.out.println("Found the expected search value of ************************** " + textvalue);

					}

					else {
						System.out.println("Searched value not found");
					}

				}
				// Clear the search text
				tablesort.EnterSearch().clear();
				tablesort.EnterSearch().sendKeys(Keys.ENTER);
				
		
	}
	
	@Then("User verifies sorting")
	public void additionalsorting() {
		java.util.List<WebElement> tdList = driver.findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr/td[2]"));
		
		     
		   String strArray[] = new String[tdList.size()];
			for(int i =0;i<tdList.size();i++)
			{
				System.out.println(tdList.get(i).getText());
				strArray[i]=tdList.get(i).getText();
			}
		
	}
	
	@AfterStory
	public void AfterStory() {

		driver.quit();
		driver.close();

	}

}
