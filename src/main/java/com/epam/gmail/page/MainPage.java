package com.epam.gmail.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.gmail.report.Reporter;

public class MainPage extends BasePage{
	
	@FindBy(xpath = "//div[@class = 'z0']/div[@role = 'button']")
	public WebElement composeBtn;
	@FindBy(xpath = "//textarea[@name = 'to']")
	WebElement recipientsFld;
	@FindBy(xpath = "//input[@placeholder = 'Subject']")
	WebElement subjectFld;
	@FindBy(xpath = "//div[@aria-label = 'Message Body']")
	WebElement msgFld;
	@FindBy(xpath = "//div[@role = 'button' and contains(@data-tooltip, 'Send')]")
	public WebElement sendBtn;
	@FindBy(xpath = "//a[contains(@title, 'Google Account')]")
	public WebElement userAccountBtn;
	@FindBy(xpath = "//div[@aria-label = 'Account Information']//a[@id = 'gb_71']")
	public WebElement logoutBtn;
	@FindBy(xpath = "//div[@role = 'dialog']")
	public WebElement msgTbl;
		
	public MainPage(WebDriver driver) {
		super(driver);
		wait.waitForElementIsClickable(logoutBtn);
		if(!driver.getTitle().contains("Inbox")){
			Reporter.pageView("Main Page View");
			throw new IllegalStateException("This is not Main Page");
		}
	}
	
	public void fillRecipientsField(String email){
		wait.waitForElementIsClickable(recipientsFld);
		recipientsFld.click();
		recipientsFld.sendKeys(email);
		msgTbl.click();
	}
	
	public void fillSubjectField(String subject){
		wait.waitForElementIsClickable(subjectFld);
		subjectFld.click();
		subjectFld.sendKeys(subject);
	}
	
	public void fillMsgField(String msg){
		wait.waitForElementIsClickable(msgFld);
		msgFld.click();
		msgFld.sendKeys(msg);
	}
	
	public boolean checkIncomingMsg(String email, String subject, String msg){
		wait.waitForElementIsClickable(composeBtn);
		Reporter.pageView("Incomming MSG View");
		final String xpath = String.format("//div[@role = 'main']//span[@email = '%s']/../../following-sibling::td//span/b[contains(text(), '%s')]/../following-sibling::span[contains(text(), '%s')]", email, subject, msg);
		return wait.isElementPresent(By.xpath(xpath));
	}
	
	public boolean isMsgFrameClosed(){
		return wait.waitForElementDisappearing(By.xpath("//div[@role = 'dialog']"));
	}
	
	public boolean isMsgSent(){
		return wait.isElementPresent(By.xpath("//div[@role = 'alert']//div[@class = 'vh']/span[contains(text(), 'View message')]"));
	}

}
