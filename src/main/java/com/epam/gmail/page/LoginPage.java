package com.epam.gmail.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.gmail.report.Reporter;

public class LoginPage extends BasePage{
	private static final String TITLE = "Gmail";
	@FindBy(xpath = "//input[@type = 'email']")
	WebElement emailFld;
	@FindBy(id = "identifierNext")
	public WebElement emailNextButton;
	@FindBy(xpath = "//input[@type = 'password']")
	WebElement passFld;
	@FindBy(id = "passwordNext")
	public WebElement passNextButton;
	@FindBy(xpath = "//div[@aria-label = 'Сменить аккаунт' or @aria-label = 'Switch account']")
	private WebElement swithAccBtn;
	@FindBy(xpath = "//div/p[contains(text(), 'Use another account') or contains(text(), 'Сменить аккаунт')]")
	private WebElement anotherAccBtn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		if(!TITLE.equals(driver.getTitle())){
			Reporter.pageView("Login Page View");
			throw new IllegalStateException("This is not Login Page");
		}
	}
	
	public LoginPage fillEmailField(String email){
		waitForLoaderIndicatorDisapearing();
		wait.waitForElementIsClickable(emailFld);
		emailFld.click();
		emailFld.sendKeys(email);
		return this;
	}
	
	public LoginPage fillPasswordField(String pass){
		waitForLoaderIndicatorDisapearing();
		wait.waitForElementIsClickable(passFld);
		passFld.click();
		passFld.sendKeys(pass);
		return this;
	}
	
	public LoginPage swithAccount(){
		wait.waitForElementIsClickable(swithAccBtn);
		swithAccBtn.click();
		wait.waitForElementIsClickable(anotherAccBtn);
		anotherAccBtn.click();
		return this;
	}
	
	
	

}
