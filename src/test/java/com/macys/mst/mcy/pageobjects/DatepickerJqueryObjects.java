package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class DatepickerJqueryObjects {

	WebDriver driver;

		
	By jquerydate = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'JQuery Date Picker')]");

	
	By pickamonth = By.xpath("//select[@class='ui-datepicker-month']");
	
	By fromdropdown=By.id("from");
	
	By todropdown=By.id("to");
	
	By nextclick = By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']");
	
	By prevclick = By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']");
	
	By pickanewday = By.cssSelector(".ui-state-default");

	

	public DatepickerJqueryObjects(WebDriver driver) {

		this.driver = driver;

	}

	
	public WebElement JqueryLink() {
		return driver.findElement(jquerydate);
	}

	public WebElement SelectFromMonth() {
		return driver.findElement(pickamonth);
	}

	public WebElement clikFromBox(){
		
		
	      return driver.findElement(fromdropdown);
	   }

	public WebElement clikToBox(){
		
		
	      return driver.findElement(todropdown);
	   }
	
	public WebElement ClickNext(){
		
		
	      return driver.findElement(nextclick);
	   }

	
	public WebElement clikPrevs(){
		
		
	      return driver.findElement(prevclick);
	   }

	public List<WebElement> PickFromDay() {
		return driver.findElements(pickanewday);
	}
	
	
}
