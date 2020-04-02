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

public class ListBootObjects {

	WebDriver driver;

	By listBox = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'List Box')]");

	By listbootstraplink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Bootstrap List Box')]");

	By leftsidevalues = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/ul[1]/li");

	By rightsidevalues = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/ul[1]/li");

	By leftsidesearch = By.xpath("//div[@class='well text-right']//input[@placeholder='search']");

	By moverightarrow = By.xpath("//button[@class='btn btn-default btn-sm move-right']");
	
	By moveleftarrow = By.xpath("//button[@class='btn btn-default btn-sm move-left']");
	
	By rightcheckall = By.xpath("//div[@class='well text-right']//a[@class='btn btn-default selector']");
	
	By leftcheckall = By.xpath("//div[@class='well']//a[@class='btn btn-default selector']");

	public ListBootObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement ListBoxMenu() {
		return driver.findElement(listBox);
	}

	public WebElement ListBootLink() {
		return driver.findElement(listbootstraplink);
	}

	public List<WebElement> RightColumValues() {
		return driver.findElements(rightsidevalues);
	}

	public List<WebElement> LeftColumnValues() {
		return driver.findElements(leftsidevalues);
	}

	public WebElement LeftSeachbox() {
		return driver.findElement(leftsidesearch);

	}

	

	public WebElement RightSideCheckAll() {
		return driver.findElement(rightcheckall);

	}

	public WebElement LeftSideCheckAll() {
		return driver.findElement(leftcheckall);

	}
	public WebElement RightSideMoveArrow() {
		return driver.findElement(moverightarrow);

	}
	
	public WebElement LeftSideMoveArrow() {
		return driver.findElement(moveleftarrow);

	}

}
