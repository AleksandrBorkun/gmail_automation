package com.epam.gmail.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.gmail.report.Reporter;

public class ExplicitWait {

	private final WebDriver driverWait;
	private final Wait<WebDriver> wait;

	public ExplicitWait(WebDriver driver) {
		this.driverWait = driver;
		wait = new WebDriverWait(this.driverWait, 15)
				.ignoring(StaleElementReferenceException.class, WebDriverException.class)
				.withMessage("Element was not found by locator ");
	}

	public ExplicitWait(WebDriver driver, int time) {
		this.driverWait = driver;
		wait = new WebDriverWait(driver, time).ignoring(StaleElementReferenceException.class, WebDriverException.class)
				.withMessage("Element was not found by locator ");
	}

	public boolean waitForElementDisappearing(By element) {
		try{
			return new WebDriverWait(driverWait, 3).until(ExpectedConditions.and(ExpectedConditions.invisibilityOfElementLocated(element)));
		}
		catch(RuntimeException e){
			return isElementPresent(element);
		}
	}

	public void waitForElementIsClickable(WebElement element) {
		try{
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch(RuntimeException e){
			Reporter.info("Element Wasn't found");
		}
	}

	public void waitForElementAppearing(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementsAppearing(List<WebElement> elementList) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
	}
	
	public boolean isElementPresent(By locator){
		try{
		return new WebDriverWait(driverWait, 3).until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(locator)));
		}
		catch(RuntimeException e){
			return false;
		}
	}

}
