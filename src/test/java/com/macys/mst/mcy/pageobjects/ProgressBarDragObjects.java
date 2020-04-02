package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ProgressBarDragObjects  {

	WebDriver driver;

		
By draganddrop = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Drag & Drop Sliders')]");

By slider = By.xpath("//div[@class='range']//input[@name='range']");

By rangetext = By.cssSelector("#range");

By defaultvaluetext = By.xpath("//h4[contains(text(),'Default value 10')]");


	public ProgressBarDragObjects(WebDriver driver) {

		this.driver=driver;

	}
	
	public WebElement DragandDropLink() {
		return driver.findElement(draganddrop);
	}
	public WebElement Slider() {
		return driver.findElement(slider);
	}

	public WebElement RangeText() {
		return driver.findElement(rangetext);
	}
	public WebElement DefaultValueText() {
		return driver.findElement(defaultvaluetext);
	}



}


