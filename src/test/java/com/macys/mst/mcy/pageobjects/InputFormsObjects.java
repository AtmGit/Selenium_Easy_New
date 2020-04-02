package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.FindBy;

public class InputFormsObjects {

	WebDriver driver;
   
	By inputformmenu = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Input Form Submit')]");
	
	By inputfname = By.xpath("//input[@placeholder='First Name']"); 
	
	By inputlname = By.xpath("//input[@placeholder='Last Name']");
	
	By inputemail = By.xpath("//input[@placeholder='E-Mail Address']");
	
	By inputphone = By.name("phone");
	
	By inputaddress = By.xpath("//input[@placeholder='Address']");
	
	By inputcity = By.xpath("//input[@placeholder='city']");
	
	By inputstate = By.xpath("//select[@name='state']");
	
	By inputzip = By.xpath("//input[@placeholder='Zip Code']");
	
	By inputweb = By.name("website");
	
	By inputradio = By.xpath("//input[@type='radio']");
	
	By inputdescription = By.xpath("//textarea[@placeholder='Project Description']");
	
	By inputsend = By.xpath("//button[@class='btn btn-default']");
	
	
	public InputFormsObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement inputFormMenu() {

		return driver.findElement(inputformmenu);

	}
	
	public WebElement InputFName(String ifname) {

		return driver.findElement(inputfname);

	}
	
	public WebElement InputLName(String ilname) {

		return driver.findElement(inputlname);

	}
	
	public WebElement InputEmail(String iemail) {

		return driver.findElement(inputemail);

	}

	public WebElement InputPhone(String iphone) {

		return driver.findElement(inputphone);

	}
	
	public WebElement InputAddress(String iaddress) {

		return driver.findElement(inputaddress);

	}
	
		
	public WebElement InputCity(String icity) {

		return driver.findElement(inputcity);

	}
	
	public WebElement InputState(String istate) {

		return driver.findElement(inputstate);

	}

	public WebElement InputZip(String izip) {

		return driver.findElement(inputzip);

	}

	public WebElement InputWebsite(String isite) {
		return driver.findElement(inputweb);
	}
	
	public WebElement InputDomain(String idomain) {

		return driver.findElement(inputweb);

	}
	
public List<WebElement> InputHostingSelect() {
	return driver.findElements(inputradio);
}


	
public WebElement InputDescription(String idesc) {

	return driver.findElement(inputdescription);

}

public WebElement InputSend() {

	return driver.findElement(inputsend);

}



}