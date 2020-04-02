package com.macys.mst.mcy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.macys.mst.artemis.selenium.PageObject;

import net.serenitybdd.core.annotations.findby.FindBy;

public class ProgressBarModalObjects {

	WebDriver driver;

	By progressbarlink = By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Progress Bar Modal')]");

	By showdialog1 = By.xpath("//button[@class='btn btn-primary']");
	By dialog1progress = By.xpath("//h3[contains(text(),'Loading')]");

	By showdialog2 = By.xpath("//button[@class='btn btn-success']");
	By dialog2progress = By.xpath("//div[@class='modal-header']");

	By showdialog3 = By.xpath("//button[@class='btn btn-warning']");
	By dialog3progress = By.xpath("//h3[contains(text(),'Hello Mr. Alert !')]");

	public ProgressBarModalObjects(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement ProgressBarModalLink() {
		return driver.findElement(progressbarlink);
	}

	public WebElement ShowDialog1() {
		return driver.findElement(showdialog1);
	}

	public WebElement ShowDialog2() {
		return driver.findElement(showdialog2);
	}

	public WebElement ShowDialog3() {
		return driver.findElement(showdialog3);
	}

	public WebElement ShowDialog1Progress() {
		return driver.findElement(dialog1progress);
	}

	public WebElement ShowDialog2Progress() {
		return driver.findElement(dialog2progress);
	}

	public WebElement ShowDialog3Progress() {
		return driver.findElement(dialog3progress);
	}

}
