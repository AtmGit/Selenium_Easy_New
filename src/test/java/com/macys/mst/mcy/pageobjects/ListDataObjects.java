package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;
import com.tibco.security.impl.ibm.B;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ListDataObjects {

	WebDriver driver;

	By datalistfilter= By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Data List Filter')]");
	
	By datasearch = By.cssSelector("#input-search");
	
	By blockcount = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/div[2]/div/div[1]");
	
	By namecount = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/div[2]/div[]/div[1]/h4[1]");
	
	By phoneemailcount = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/div[2]/div/div[1]/span");
	
	By titlecount = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/div[2]/div/div[1]/p[1]");



	public ListDataObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement DataListMenu() {
		return driver.findElement(datalistfilter);
	}

	public WebElement DataSearch() {
		return driver.findElement(datasearch);
	}


	public List<WebElement> BlockCount() {
		return driver.findElements(blockcount);
	}


	public List<WebElement> NameCount() {
		return driver.findElements(namecount);
	}


	public List<WebElement> PhoneCount() {
		return driver.findElements(phoneemailcount);
	}

	public List<WebElement> TitleCount() {
		return driver.findElements(titlecount);
	}
}
