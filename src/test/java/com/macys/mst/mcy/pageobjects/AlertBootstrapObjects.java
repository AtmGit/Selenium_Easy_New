package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class AlertBootstrapObjects {

	WebDriver driver;

	By alertmodalsmenu = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Alerts & Modals')]");

	By bootstrapalert = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Bootstrap Alerts')]");

	By autoclosesuccess = By.cssSelector("#autoclosable-btn-success");

	By successmessage = By.xpath("//div[@class='alert alert-success alert-autocloseable-success']");

	By normalsuccess = By.cssSelector("#normal-btn-success");

	By normalsuccessmessage = By.xpath("//div[@class='alert alert-success alert-normal-success']");

	By normalmessageclose = By.xpath(
			"//div[@class='alert alert-success alert-normal-success']//button[@class='close'][contains(text(),'×')]");

	By normalwarning = By.cssSelector("#normal-btn-warning");

	By normalwarningmessage = By.xpath("//div[@class='alert alert-warning alert-normal-warning']");

	By normalwarningclose = By.xpath(
			"//div[@class='alert alert-warning alert-normal-warning']//button[@class='close'][contains(text(),'×')]");

	public AlertBootstrapObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement AlertModalsMenu() {
		return driver.findElement(alertmodalsmenu);
	}

	public WebElement BootStrapAlertLink() {
		return driver.findElement(bootstrapalert);
	}

	public WebElement AutocloseSuccess() {
		return driver.findElement(autoclosesuccess);
	}

	public WebElement SuccessMessage() {
		return driver.findElement(successmessage);
	}

	public WebElement NormalSuccess() {
		return driver.findElement(normalsuccess);
	}

	public WebElement NormalSuccessText() {
		return driver.findElement(normalsuccessmessage);
	}

	public WebElement NormalSuccessClose() {
		return driver.findElement(normalmessageclose);
	}

	public WebElement NormalWarning() {
		return driver.findElement(normalwarning);
	}

	public WebElement NormalWarningText() {
		return driver.findElement(normalwarningmessage);
	}

	public WebElement NormalWarningClose() {
		return driver.findElement(normalwarningclose);
	}

}
