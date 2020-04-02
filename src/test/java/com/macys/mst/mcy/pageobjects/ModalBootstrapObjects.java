package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ModalBootstrapObjects {

	WebDriver driver;

	By bootstrapmodal = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Bootstrap Modals')]");
	
	By singlemodal = By.xpath("//body/div[@class='container-fluid text-center']/div[@class='row']/div[@class='col-md-6 text-left']/div[1]/div[1]/div[1]/div[2]/a[1]");
	
	By closealert = By.xpath("//div[@id='myModal0']//a[@class='btn'][contains(text(),'Close')]");
	
	By popuptext =  By.className("modal-body");
	
	By closebutton = By.xpath("//div[@id='myModal0']//button[@class='close'][contains(text(),'×')]");
	
	By modaltitle = By.className("modal-title");
	
	By multiplemodelclick = By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/a[1]");
	
	By multiplemodeltitle = By.xpath("//h4[contains(text(),'First Modal')]");
	
	By multiplemodelbody = By.xpath("//div[@id='myModal']//div[@class='modal-body'][contains(text(),'This is the place where the content for the modal')]");
	
	By launchmodel = By.xpath("//div[@class='modal-body']//a[@class='btn btn-primary'][contains(text(),'Launch modal')]");
	
	By launchmodeltitle = By.xpath("//h4[contains(text(),'Modal 2')]");
	
	By launchmodaltext = By.xpath("//div[@id='myModal2']//div[@class='modal-body'][contains(text(),'This is the place where the content for the modal')]");
	
	By launchclose = By.xpath("//div[@id='myModal2']//button[@class='close'][contains(text(),'×')]");
	
	By savechanges = By.xpath("//div[@id='myModal']//a[@class='btn btn-primary'][contains(text(),'Save changes')]");
	
	

	public ModalBootstrapObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement BootStrapModal() {
		return driver.findElement(bootstrapmodal);
	}

public WebElement SingleModal() {
	return driver.findElement(singlemodal);
}


public WebElement CloseAlert() {
	return driver.findElement(closealert);
}

public WebElement PopupText() {
	return driver.findElement(popuptext);
}

public WebElement CancelButton() {
	return driver.findElement(closebutton);
}


public WebElement ModalTitle() {
	return driver.findElement(modaltitle);
}


public WebElement MultipleModalButton() {
	return driver.findElement(multiplemodelclick);
}

public WebElement MultipleModalTitle() {
	return driver.findElement(multiplemodeltitle);
}


public WebElement MultipleModalBodyText() {
	return driver.findElement(multiplemodelbody);
}

public WebElement LaunchModelFromInside() {
	return driver.findElement(launchmodel);
}

public WebElement LaunchModalTitle() {
	return driver.findElement(launchmodeltitle);
}

public WebElement LaunchModalText() {
	return driver.findElement(launchmodaltext);
}

public WebElement CloseLaunchWindow() {
	return driver.findElement(launchclose);
}


public WebElement FirstModalSaveChanges() {
	return driver.findElement(savechanges);
}


}
