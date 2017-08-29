package com.epam.gmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.gmail.utils.ExplicitWait;

public abstract class BasePage {
	
	@FindBy(xpath = "//div[@class = 'ANuIbb IdAqtf']")
	protected WebElement loadIndicatorLocator;
	
	protected WebDriver driver;
	public ExplicitWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new ExplicitWait(driver);
	}
	
	void waitForLoaderIndicatorDisapearing() {
		try{
			if (loadIndicatorLocator.isDisplayed()) {
				wait.waitForElementDisappearing(By.xpath("//div[@class = 'ANuIbb IdAqtf']"));
			}
		}
		catch(RuntimeException e){
		}
	}
}
