package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class DatepickerObjects {

	WebDriver driver;

	By datepickermenu = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Date pickers')]");

	By bootstrapdatelink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Bootstrap Date Picker')]");

	By selectdate = By.xpath("//input[@placeholder='dd/mm/yyyy']");

	By selectdate2 = By.xpath("//div[@class='input-group date']//span[@class='input-group-addon']");

	By selecttoday = By.xpath("//div[@class='datepicker-days']//th[@class='today'][contains(text(),'Today')]");

	By cleardate = By.xpath("//div[@class='datepicker-days']//th[@class='clear'][contains(text(),'Clear')]");

	By startdate = By.xpath("//input[@placeholder='Start date']");

	By enddate = By.xpath("//input[@placeholder='End date']");

	By forwardbutton = By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'»')]");

	By backwordbutton = By.xpath("//div[@class='datepicker-days']//th[@class='prev'][contains(text(),'«')]");

	By pickaday = By.cssSelector(".day");

	By pickamonth = By.xpath("/html[1]/body[1]/div[3]/div[1]/table[1]/thead[1]/tr[2]/th[2]");

	public DatepickerObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement SelectFromMenu() {

		return driver.findElement(datepickermenu);

	}

	public WebElement BootStrapLink() {
		return driver.findElement(bootstrapdatelink);
	}

	public WebElement SelectADate() {
		return driver.findElement(selectdate);

	}

	public WebElement SelectToday() {
		return driver.findElement(selecttoday);

	}

	public WebElement SelectToday2() {
		return driver.findElement(selectdate2);

	}

	public WebElement ClearDate() {

		return driver.findElement(cleardate);

	}

	public WebElement startDate() {
		return driver.findElement(startdate);
	}

	public WebElement endtDate() {
		return driver.findElement(enddate);
	}

	public List<WebElement> PickADay() {
		return driver.findElements(pickaday);
	}

	public WebElement PickAMonth() {
		return driver.findElement(pickamonth);
	}


   public WebElement ForwardAMonth()
   {
		return driver.findElement(forwardbutton);
   }

   public WebElement BackwardAMonth()
   {
		return driver.findElement(backwordbutton);
   }
   
}
