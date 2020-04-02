package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.FindBy;

public class InputFormsAjaxObjects {

	WebDriver driver;
   
	By Ajexmenu = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Ajax Form Submit')]");
	
	By Ajextitle = By.xpath("//div[contains(text(),'Ajax Form')]");
	
	By Ajexname = By.cssSelector("#title");
	
	By Ajexdescription = By.cssSelector("#description");
	
	By Ajexsubmit = By.cssSelector("#btn-submit");
	
	By Submitmessage = By.id("submit-control");
	

	public InputFormsAjaxObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement ajexMenu() {

		return driver.findElement(Ajexmenu);

	}
	
	public WebElement GetAjexTitle() {

		return driver.findElement(Ajextitle);

	}


	public WebElement EnterAjexName(String name) {

		return driver.findElement(Ajexname);
	}
	
	
	public WebElement  EnterAjexctComment(String comment) {

		return driver.findElement(Ajexdescription);

	}

	public WebElement ajexSubmit() {

		return driver.findElement(Ajexsubmit);

	}
	public WebElement AjextSubmitMessage() {

		return driver.findElement(Submitmessage);

	}

	
	
}