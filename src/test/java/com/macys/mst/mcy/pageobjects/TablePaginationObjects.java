package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class TablePaginationObjects {

	WebDriver driver;

		
By tablemenu = By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Table')]");

By tablepaginationlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Table Pagination')]");

By movepages = By.cssSelector(".col-md-6.text-center #myPager a");

By nameselementsnew = By.cssSelector(".table.table-hover>tbody>tr>td:nth-child(1)");

By page1click = By.cssSelector(".col-md-6.text-center #myPager a.page_link.active");

By columnsize = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/table[1]/thead[1]/tr[1]/th");

By rowsize = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/table[1]/tbody[1]/tr");

By colrowvalue = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/div[1]/table[1]/tbody[1]/tr[4]/td[4]");

	
	public TablePaginationObjects(WebDriver driver) {

		this.driver = driver;

	}

	
	public WebElement TableMenu() {
		return driver.findElement(tablemenu);
	}

	public WebElement TablePaginationLink() {
		return driver.findElement(tablepaginationlink);
	}

	public List<WebElement> PaginationForLoop() {
		return driver.findElements(movepages);
	}
	
	public WebElement Page1Click() {
		return driver.findElement(page1click);
	}
	
	public List<WebElement> FirstColumnNumbers() {
		return driver.findElements(nameselementsnew);
	}

	public List<WebElement> TotalColumns() {
		return driver.findElements(columnsize);
	}

	public List<WebElement> TotalRows() {
		return driver.findElements(rowsize);
	}
	
	public WebElement SpeficRowColumn() {
		return driver.findElement(colrowvalue);
	}


}
