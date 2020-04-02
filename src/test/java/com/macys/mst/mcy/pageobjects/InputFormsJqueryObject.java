package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.FindBy;

public class InputFormsJqueryObject {

	WebDriver driver;

	By inputformjquery = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'JQuery Select dropdown')]");

	By selectcountry = By.xpath(
			"//body/div[@class='container-fluid text-center']/div[@class='row']/div[@class='col-md-6 text-left']/div[1]/div[1]/div[2]/span[1]/span[1]/span[1]");

	By jquerySearch = By
			.xpath("//span[@class='select2-search select2-search--dropdown']//input[@class='select2-search__field']");

	By jquerymultisearch = By.xpath("//input[@placeholder='Select state(s)']");

	//By jquerydisabledrop = By.xpath("//span[@class='select2-selection__rendered']");
	
	By jqueryselectfile = By.xpath("//select[@id='files']");
	
	

	public InputFormsJqueryObject(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement inputFormJqueryMenu() {

		return driver.findElement(inputformjquery);

	}

	public WebElement JquerySelectCountry() {
		return driver.findElement(selectcountry);
	}

	public WebElement JquerySearchCountry() {
		return driver.findElement(jquerySearch);
	}

	
	public WebElement JquerySelectStates() {
		return driver.findElement(jquerymultisearch);
	}

	/*public WebElement JqueryDisableDrodown() {
		return driver.findElement(jquerydisabledrop);
	}
*/

	public WebElement JquerySelectAFile(String Jvalue) {
		return driver.findElement(jqueryselectfile);
	}




}