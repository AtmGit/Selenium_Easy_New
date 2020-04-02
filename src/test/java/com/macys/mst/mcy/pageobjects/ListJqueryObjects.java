package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;
import com.tibco.security.impl.ibm.B;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ListJqueryObjects {

	WebDriver driver;

	By jquerylink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'JQuery List Box')]");
	
	By leftsidesize = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/select[1]/option");
	
	By rightsidesize = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[3]/select[1]/option");
	
	By addall = By.xpath("//button[@class='pAddAll btn btn-primary btn-sm']");
	
	By removeall = By.xpath("//button[@class='pRemoveAll btn btn-primary btn-sm']");
	
	By addbutton = By.xpath("//button[@class='pAdd btn btn-primary btn-sm']");
	
	By removebutton = By.xpath("//button[@class='pRemove btn btn-primary btn-sm']");



	public ListJqueryObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement ListJqueryLink() {
		return driver.findElement(jquerylink);
	}

	public List<WebElement>LeftSideSize() {
		return driver.findElements(leftsidesize);
	}

	public List<WebElement>RightSideSize() {
		return driver.findElements(rightsidesize);
	}
	
	public WebElement AddAllbutton() {
		return driver.findElement(addall);
	}
	
	public WebElement RemoveAllButton() {
		return driver.findElement(removeall);
	}


	public WebElement AddButton() {
		return driver.findElement(addbutton);
	}

	public WebElement RemoveButton() {
		return driver.findElement(removebutton);
	}

	

}
