package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class DynamicDataObjects {

	WebDriver driver;

		
By dynamicdatalink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Dynamic Data Loading')]");

By getnetuserlink = By.id("save");

By image = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/img[1]");

By name = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]");

	
	public DynamicDataObjects(WebDriver driver) {

		this.driver = driver;

	}

	
	public WebElement DynamicDataLink() {
		return driver.findElement(dynamicdatalink);
	}

	public WebElement NewUserButton() {
		return driver.findElement(getnetuserlink);
	}

	public WebElement Image() {
		return driver.findElement(image);
	}

	public WebElement Name() {
		return driver.findElement(name);
	}

/*
	public List<WebElement> SourceDragSize() {
		return driver.findElements(dragsize);
	}
*/
	


	
}
