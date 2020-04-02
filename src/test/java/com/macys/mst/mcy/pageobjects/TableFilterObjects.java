package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class TableFilterObjects {

	WebDriver driver;

		
By tablefilterlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Table Filter')]");

By selectgreen = By.xpath("//button[@class='btn btn-success btn-filter']");

By selectorange = By.xpath("//button[@class='btn btn-warning btn-filter']");

By selectred = By.xpath("//button[@class='btn btn-danger btn-filter']");

By selectall = By.xpath("//button[@class='btn btn-default btn-filter']");

	
	public TableFilterObjects(WebDriver driver) {

		this.driver = driver;

	}

	
	public WebElement TableFilterLink() {
		return driver.findElement(tablefilterlink);
	}

	public WebElement GreenFilter() {
		return driver.findElement(selectgreen);
	}

	public WebElement OrangeFilter() {
		return driver.findElement(selectorange);
	}

	public WebElement RedFilter() {
		return driver.findElement(selectred);
	}

	public WebElement AllFilter() {
		return driver.findElement(selectall);
	}




}
