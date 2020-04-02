package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ProgressBarsBootStrapObjects {

	WebDriver driver;

	By bootstrapprogressbarlink = By
			.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Bootstrap Progress bar')]");

	By downloadbutton = By.id("cricle-btn");

	By progresscircle = By.xpath("//div[@class='prog-circle']");

	public ProgressBarsBootStrapObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement BootStrapProgressBarsLink() {
		return driver.findElement(bootstrapprogressbarlink);
	}

	public WebElement ProgressBar() {
		return driver.findElement(progresscircle);
	}

	public WebElement Downloadbutton() {
		return driver.findElement(downloadbutton);
	}

}
