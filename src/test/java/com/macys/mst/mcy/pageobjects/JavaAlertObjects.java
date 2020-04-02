package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class JavaAlertObjects {

	WebDriver driver;

	By javaalertlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Javascript Alerts')]");
	
	By alertbox = By.xpath("//button[@class='btn btn-default']");
	
	By alertconfirmbox = By.xpath("//button[@class='btn btn-default btn-lg'][contains(text(),'Click me!')]");
	
	By alertmessage = By.cssSelector("#confirm-demo");
	
	By promptbox = By.xpath("//button[contains(text(),'Click for Prompt Box')]");
	
	By messagename = By.cssSelector("#prompt-demo");

	

	public JavaAlertObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement JavaAlertLink() {
		return driver.findElement(javaalertlink);
	}

	public WebElement AlertBox() {
		return driver.findElement(alertbox);
	}

	public WebElement ConfirmAlertBox() {
		return driver.findElement(alertconfirmbox);
	}
	public WebElement AlertConfirmMessage() {
		return driver.findElement(alertmessage);
	}

	public WebElement PromptBox() {
		return driver.findElement(promptbox);
	}


	public WebElement MessageName() {
		return driver.findElement(messagename);
	}

}
