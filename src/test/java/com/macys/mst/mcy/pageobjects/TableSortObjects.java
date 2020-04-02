package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class TableSortObjects {

	WebDriver driver;

		
By tablesortlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Table Sort & Search')]");

//By namesort = By.xpath("//th[@class='sorting_asc']");

By namesort = By.cssSelector(".sorting_asc");

By poritionsort = By.xpath("//th[contains(text(),'Position')]");

By officesort = By.xpath("//th[contains(text(),'Office')]");

By agesort = By.xpath("//th[contains(text(),'Age')]");

By startdatesprt = By.xpath("//th[contains(text(),'Start date')]");

By salarysort = By.xpath("//th[contains(text(),'Salary')]");

By showselect = By.xpath("//select[@name='example_length']");

By searchbox = By.xpath("//label[contains(text(),'Search:')]//input");

By tablevalues = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr/td");

By columnvalues = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr/td[3]");



	
	public TableSortObjects(WebDriver driver) {

		this.driver = driver;

	}

	
	public WebElement TableSortLink() {
		return driver.findElement(tablesortlink);
	}

	
	public WebElement NameSort() {
		return driver.findElement(namesort);
	}


	public WebElement PositionSort() {
		return driver.findElement(poritionsort);
	}


	public WebElement AgeSort() {
		return driver.findElement(agesort);
	}

	

	public WebElement DateSort() {
		return driver.findElement(startdatesprt);
	}
	

	public WebElement SalarySort() {
		return driver.findElement(salarysort);
	}

	public WebElement SelectShow() {
		return driver.findElement(showselect);
	}

	public WebElement EnterSearch() {
		return driver.findElement(searchbox);
	}

	public List<WebElement> TableVlaues() {
		return driver.findElements(tablevalues);
	}
	public List<WebElement> ColumnValues() {
		return driver.findElements(columnvalues);
	}

   public WebElement OfficeSort()
   {
	   return driver.findElement(officesort);
   }
}
