package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ProgressBarsObjects  {

	WebDriver driver;

		
By progressbarlink = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Progress Bars')]");

By jquerydownloadlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'JQuery Download Progress bars')]");

By selectdownload = By.id("downloadButton");

By canceldownload = By.xpath("//button[contains(text(),'Cancel Download')]");

By currentprogress = By.xpath("//div[@class='progress-label']");

By closebutton = By.xpath("//button[contains(text(),'Close')]");


private WebElement startdownloadbutton;


	public ProgressBarsObjects(WebDriver driver) {

		this.driver=driver;

	}
	
	public WebElement ProgressBarsLink() {
		return driver.findElement(progressbarlink);
	}

	public WebElement JqueryDownloadLink() {
		return driver.findElement(jquerydownloadlink);
	}

	public WebElement StartDownload() {
		return driver.findElement(selectdownload);
	}

   public WebElement CurrentProgress() {
	   return driver.findElement(currentprogress);
   }

   public WebElement CloseButton() {
	   return driver.findElement(closebutton);
   }

   public WebElement CancelButton() {
	   return driver.findElement(canceldownload);
   }
}


