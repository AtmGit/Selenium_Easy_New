package com.macys.mst.mcy.pageobjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.FindBy;

public class InputFormsSimpleDemoObjects {

	WebDriver driver;
	// WebDriverWait wait = new WebDriverWait(driver, 10);

	By InputFormsLink = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Input Forms')]");

	By SimpleFormDemoLink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Simple Form Demo')]");

	By DemohomeLink = By.linkText("//a[contains(text(),'Demo Home')]");

	By EnterMessageBox = By.cssSelector("#user-message");

	By ShowMessageClick = By.xpath("//button[contains(text(),'Show Message')]");

	By VerifyMessageText = By.cssSelector("#display");

	By enterA = By.id("sum1");

	By enterB = By.id("sum2");

	By getTotal = By.xpath("//button[contains(text(),'Get Total')]");

	By total = By.id("displayvalue");

	By demohome = By.linkText("Demo Home");

	By homepagetext = By.cssSelector("#btn_basic_example");
	/*
	 * @FindBy(id = "sum1") public WebElement enterA;
	 * 
	 * @FindBy(id = "sum2") public WebElement enterB;
	 */

	public InputFormsSimpleDemoObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement GoToHome() {
		return driver.findElement(demohome);
	}

	public boolean homePageIsOpened() {
		return driver.findElement(homepagetext).isDisplayed();
	}

	public WebElement InputForms() {

		// wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Input
		// Forms")));
		return driver.findElement(InputFormsLink);

	}

	public WebElement SimpleFormDemo() {

		return driver.findElement(SimpleFormDemoLink);

	}

	public WebElement DemoHome() {

		return driver.findElement(DemohomeLink);

	}

	public WebElement EnterMessage() {

		return driver.findElement(EnterMessageBox);

	}

	public WebElement ShowMessage() {

		return driver.findElement(ShowMessageClick);

	}

	public WebElement verifyMessage() {

		return driver.findElement(VerifyMessageText);

	}

	public WebElement enterValueA() {

		return driver.findElement(enterA);
	}

	public WebElement enterValueB() {

		return driver.findElement(enterB);
	}

	public WebElement GetTotalNumber() {

		return driver.findElement(getTotal);

	}

	public WebElement verifyTotal() {

		return driver.findElement(total);

	}

}
