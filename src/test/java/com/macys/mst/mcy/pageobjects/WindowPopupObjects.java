package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class WindowPopupObjects {

	WebDriver driver;

By windowpopup = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Window Popup Modal')]");

By followtwitter = By.xpath("//a[contains(text(),'Follow On Twitter')]");

By likeonfacebook = By.xpath("//a[contains(text(),'Like us On Facebook')]");

By followtwtandfb = By.linkText("Follow Twitter & Facebook");

By followall = By.cssSelector("#followall");
	

	public WindowPopupObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement FollowOnTwitter() {
		return driver.findElement(followtwitter);
	}

	public WebElement WindowPopup() {
		return driver.findElement(windowpopup);
	}


	public WebElement LikeOnFacebook() {
		return driver.findElement(likeonfacebook);
	}


	public WebElement TwitterAndFB() {
		return driver.findElement(followtwtandfb);
	}


	public WebElement FollowAll() {
		return driver.findElement(followall);
	}





}
