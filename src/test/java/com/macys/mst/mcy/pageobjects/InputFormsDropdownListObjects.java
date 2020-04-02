package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.FindBy;

public class InputFormsDropdownListObjects {

	WebDriver driver;

	By Dropdownlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Select Dropdown List')]");
	
	By SelectDay = By.xpath("//select[@id='select-demo']");
	
	By SelectStates = By.id("multi-select");
	
    By Firstselected = By.cssSelector("#printMe");
    
    By Allselected = By.cssSelector("#printAll");
    
    By Verifyselectedmsg = By.xpath("//p[@class='getall-selected']");
    
    

	public InputFormsDropdownListObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement SelectDropdownList() {

		return driver.findElement(Dropdownlink);

	}

	/*public WebElement SelectFromDropdown() {

		return driver.findElement(SelectDay);

	}*/

	public WebElement SelectFromDropdown(String a) {

		return driver.findElement(SelectDay);
	}
	
	
	public WebElement SelectStateFromDropdown() {

		return driver.findElement(SelectStates);

	}

	public WebElement firstSelected() {

		return driver.findElement(Firstselected);

	}
	public WebElement allSelected() {

		return driver.findElement(Allselected);

	}

	public WebElement verifySelectedMessage() {

		return driver.findElement(Verifyselectedmsg);

	}
	
	
}