package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class FileDownloadObjects {

	WebDriver driver;

	By filedownloadlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'File Download')]");
	
	By textbox = By.cssSelector("#textbox");
	
	By generatefile = By.cssSelector("#create");
	
	By download = By.linkText("Download");

	public FileDownloadObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement FileDownloadLink() {
		return driver.findElement(filedownloadlink);
	}

	public WebElement TextBox() {
		return driver.findElement(textbox);
	}
	

	public WebElement GenerateFile() {
		return driver.findElement(generatefile);
	}

	public WebElement Dowbnload() {
		return driver.findElement(download);
	}
}
