package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.FindBy;

public class InputFormsRadioButtonObjects {

	WebDriver driver;

	By RadioButtonLink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Radio Buttons Demo')]");
	
	By Malebutton = By.xpath("//body/div/div/div/div/div/label[contains(text(),'Male')]/input[1]");
	
	By Femalebutton = By.xpath("//body/div/div/div/div/div/label[contains(text(),'Female')]/input[1]");
	
	By Getcheckedvalue = By.cssSelector("#buttoncheck");
	
	By Verifytext = By.xpath("//p[@class='radiobutton']");
	
	By Commonname = By.xpath("//input[@name='gender']");
	
	By commonage = By.xpath("//input[@name='ageGroup']");
	
	By getvalue = By.xpath("//button[contains(text(),'Get values')]");
	
	By getradioMessage = By.xpath("//p[@class='groupradiobutton']");
	
		
	public InputFormsRadioButtonObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement RadioButtonDemo() {
	
		return driver.findElement(RadioButtonLink);
	}

	public WebElement maleButton() {
	
		return driver.findElement(Malebutton);
	}

	public WebElement femaleButton() {
	
		return driver.findElement(Femalebutton);
	}

	public WebElement getcheckedValue() {
	
		return driver.findElement(Getcheckedvalue);
	}

	public WebElement verifyRadioMessage() {
		
		return driver.findElement(Verifytext);
	}

  public List<WebElement> selectSex()
  {
	  return driver.findElements(Commonname);
  }

  public List<WebElement> selectAgeGroup()
  {
	  return driver.findElements(commonage);
  }
  
  public WebElement getValue() {
		
		return driver.findElement(getvalue);
	}

  public WebElement radioMessage() {
		
		return driver.findElement(getradioMessage);
	}


}
