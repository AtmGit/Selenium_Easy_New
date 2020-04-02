package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class DragDropObjects {

	WebDriver driver;

		
By othersMenu = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Others')]");


By dragdroplink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Drag and Drop')]");

By dragsize = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/span");

By targetdroppable = By.cssSelector("#mydropzone");





	
	public DragDropObjects(WebDriver driver) {

		this.driver = driver;

	}

	
	public WebElement OthersMenu() {
		return driver.findElement(othersMenu);
	}

	public WebElement DragDropLink() {
		return driver.findElement(dragdroplink);
	}

	public List<WebElement> SourceDragSize() {
		return driver.findElements(dragsize);
	}

	
	
	public WebElement TargetDroppable() {
		return driver.findElement(targetdroppable);
	}

	
}
