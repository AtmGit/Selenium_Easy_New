package com.macys.mst.mcy.pageobjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.FindBy;

public class InputFormsCheckBoxObjects {

	WebDriver driver;

	By CheckBoxDemoLink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Checkbox Demo')]");

	By SingleCheckbox = By.cssSelector("#isAgeSelected");

	By CheckMessage = By.cssSelector("#txtAge");

	By CheckOption1 = By.xpath("//label[text()='Option 1']//input[@class='cb1-element']");

	By CheckOption2 = By.xpath("//label[text()='Option 2']//input[@class='cb1-element']");

	By CheckOption3 = By.xpath("//label[text()='Option 3']//input[@class='cb1-element']");

	By CheckOption4 = By.xpath("//label[text()='Option 4']//input[@class='cb1-element']");

	By CheckAll = By.cssSelector("#check1");

	public InputFormsCheckBoxObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement CheckboxDemo() {

		return driver.findElement(CheckBoxDemoLink);

	}

	public WebElement SingleCheckboxDemo() {

		return driver.findElement(SingleCheckbox);

	}

	public WebElement SingleCheckboxMessage() {

		return driver.findElement(CheckMessage);

	}

	public WebElement MultipleBox1() {

		return driver.findElement(CheckOption1);

	}
	public WebElement MultipleBox2() {

		return driver.findElement(CheckOption2);

	}
	public WebElement MultipleBox3() {

		return driver.findElement(CheckOption3);

	}
	public WebElement MultipleBox4() {

		return driver.findElement(CheckOption4);

	}


	public WebElement CheckAllButton() {

		return driver.findElement(CheckAll);

	}

	
}
