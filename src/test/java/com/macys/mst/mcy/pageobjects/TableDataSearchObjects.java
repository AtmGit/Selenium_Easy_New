package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class TableDataSearchObjects {

	WebDriver driver;

		
By tablemenu = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Table')]");

By tabledatasearchlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Table Data Search')]");

By searchbox = By.cssSelector("#task-table-filter");

By numberofcolumns = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td");

By numberofrows = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr");

By firstcolheader = By.xpath("//input[@placeholder='#']");

By usernamecolumn = By.xpath("//input[@placeholder='Username']");

By firstnamecolumn = By.xpath("//input[@placeholder='First Name']");

By lastnamecolumn = By.xpath("//input[@placeholder='Last Name']");

By filterbutton = By.xpath("//button[@class='btn btn-default btn-xs btn-filter']");

By totalcolmns =  By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td");

By totalrows = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr");

By tablename = By.xpath("//table[@class='table']");

By allvalues = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr/td");

By listedusers = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr/td");

	
	public TableDataSearchObjects(WebDriver driver) {

		this.driver = driver;

	}

	
	public WebElement TableMenu() {
		return driver.findElement(tablemenu);
	}

	public WebElement TableDataSearchLink() {
		return driver.findElement(tabledatasearchlink);
	}
	
	public WebElement DataSearch() {
		return driver.findElement(searchbox);
	}

	public List<WebElement> TotalColumns() {
		return driver.findElements(numberofcolumns);
	}

	public List<WebElement> TotalRows() {
		return driver.findElements(numberofrows);
	}
	
	public WebElement FirstColumnSearch() {
		return driver.findElement(firstcolheader);
	}
	public WebElement UsernameSearch() {
		return driver.findElement(usernamecolumn);
	}
	
	public WebElement FirstNameSearch() {
		return driver.findElement(firstnamecolumn);
	}
	
	public WebElement LastNameSearch() {
		return driver.findElement(lastnamecolumn);
	}

	public WebElement SelectFilter() {
		return driver.findElement(filterbutton);
	}

	public List<WebElement> NumberofColumns() {
		return driver.findElements(totalcolmns);
	}

	public List<WebElement> NumberofRows() {
		return driver.findElements(totalrows);
	}
	
	public WebElement TableName() {
		return driver.findElement(tablename);
	}

	public List<WebElement> AllValuesFromTable() {
		return driver.findElements(allvalues);
	}
	

	public List<WebElement> ListedUsers() {
		return driver.findElements(listedusers);
	}
	
}
