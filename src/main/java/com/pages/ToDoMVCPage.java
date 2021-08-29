package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.drivers.Driver;

public class ToDoMVCPage {

	WebDriver driver;
	
	//xpath locators
	private By todoTxtBx = By.xpath("//header/input");
	private By noOfCBoxs = By.xpath("//li[@class='todo']//input[@type='checkbox']");
	private By noOfXs = By.xpath("//li[@class='todo']//button[@class='destroy']");
	private By totItems = By.xpath("//footer/span/strong");
	private By compItems = By.xpath("//li[@class='todo completed']");
	private By actvItems = By.xpath("//li[@class='todo']");
	private By mkAllCmplt = By.xpath("//label[contains(text(),'Mark all as complete')]");
	private By mgFtr = By.xpath("//span[@class='todo-count']");
	private By hdrPHldr = By.xpath("//header/input[@placeholder='What needs to be done?']");
	
	//LinkText locators
	private By all = By.linkText("All");
	private By active = By.linkText("Active");
	private By completed = By.linkText("Completed");
	
	public ToDoMVCPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public void navToURL(String url) {
		driver.get(url);
	}
	
	public void typeAndEnter(List<String> list) throws InterruptedException {
		for(String l:list) {
			driver.findElement(todoTxtBx).sendKeys(l);
			driver.findElement(todoTxtBx).sendKeys(Keys.RETURN);
		}
	}
	
	public void typeButNoEnter(List<String> list) throws InterruptedException {
		for(String l:list) {
			driver.findElement(todoTxtBx).sendKeys(l);
		}
	}
	
	public int checkToValNoOfToDoItems() {
		List<WebElement> noOfCBoxes = driver.findElements(noOfCBoxs);
		List<WebElement> noOfXss = driver.findElements(noOfXs);
		return noOfCBoxes.size()+noOfXss.size();
	}
	
	public String getTotItems() {
		return driver.findElement(totItems).getText();
	}
	
	public Boolean filtersValAll() {
		return driver.findElement(all).isDisplayed() && driver.findElement(all).isEnabled();
	}
	
	public Boolean filtersValActive() {
		return driver.findElement(active).isDisplayed() && driver.findElement(active).isEnabled();
	}
	
	public Boolean filtersValCompleted() {
		return driver.findElement(completed).isDisplayed() && driver.findElement(completed).isEnabled();
	}
	
	public void markAsCompleted(int row) {
		WebElement cBox = Driver.getDriver().findElement(By.xpath("//li[@class='todo']["+row+"]//input[@type='checkbox']"));
		cBox.click();
	}
	
	public int cntCompItems() {
		List<WebElement> cItems = Driver.getDriver().findElements(compItems);
		return cItems.size();
	}
	
	public int cntActvItems() {
		List<WebElement> aItems = Driver.getDriver().findElements(actvItems);
		return aItems.size();
	}
	
	public void clckMarkAllComplete() {
		driver.findElement(mkAllCmplt).click();
	}
	
	public String getMsgFooter() {
		return driver.findElement(mgFtr).getText();
	}
	
	public Boolean pHldrVal() {
		return driver.findElement(hdrPHldr).isDisplayed() && driver.findElement(hdrPHldr).isEnabled();
	}
}
