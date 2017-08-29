package com.epam.gmail.tests;

import static org.testng.Assert.assertTrue;

import java.util.UUID;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.epam.gmail.page.LoginPage;
import com.epam.gmail.page.MainPage;
import com.epam.gmail.property.PropertyProvider;
import com.epam.gmail.report.Reporter;
import com.epam.gmail.utils.Assertion;
import com.epam.gmail.utils.MailAPI;

public class LoginPageTest extends BaseTest {
	
	private static final String PASS = PropertyProvider.getProperty("password");
	private static final String USER_FIRST = PropertyProvider.getProperty("user_first");
	private static final String USER_SECOND = PropertyProvider.getProperty("user_second");
	private static final String SENT_FOLDER = "[Gmail]/Sent Mail";
	private static final String INBOX_FOLDER = "inbox";	
	private static final String RANDOM_TXT = UUID.randomUUID().toString();
	
	
	@Test(description = "Verify That When Send The Message It Came To In Box folder")
	public void sendMessageAndCheckedInBoxTest() throws InterruptedException{
		
		Reporter.logStep("Step 1 : Login to Gmail");
		loginPage.fillEmailField(USER_FIRST);
		loginPage.emailNextButton.click();
		loginPage.fillPasswordField(PASS);
		loginPage.passNextButton.click();
		MainPage mainPage = new MainPage(driver);
		
		Reporter.logStep("Step 2 : Send the MSG");
		mainPage.wait.waitForElementIsClickable(mainPage.composeBtn);
		Reporter.pageView("Main Page View");
		mainPage.composeBtn.click();
		Reporter.pageView("Message Window View");
		assertTrue(mainPage.msgTbl.isEnabled(), "Verify That Message Window Is Opened");
		mainPage.fillRecipientsField(USER_SECOND);
		mainPage.fillSubjectField(RANDOM_TXT);
		mainPage.fillMsgField(RANDOM_TXT);
		mainPage.wait.waitForElementIsClickable(mainPage.sendBtn);
		mainPage.sendBtn.click();
		Reporter.pageView("Sent Msg View");
		Assertion.verifyTrue(mainPage.isMsgFrameClosed(), "Verify That Message Window Is Closed");
		Assertion.verifyTrue(mainPage.isMsgSent(), "Verify that the msg is sent");
		
		Reporter.logStep("Step 3 : LogOut");
		mainPage.wait.waitForElementIsClickable(mainPage.userAccountBtn);
		mainPage.userAccountBtn.click();
		Reporter.pageView("User Icon View");
		mainPage.wait.waitForElementIsClickable(mainPage.logoutBtn);
		mainPage.logoutBtn.click();
		loginPage = new LoginPage(driver);
		
		Reporter.logStep("Step 4 : Swith Account");
		Reporter.pageView("Login Page View");
		loginPage.swithAccount();
		loginPage = new LoginPage(driver);
		loginPage.fillEmailField(USER_SECOND)
				.emailNextButton.click();
		loginPage.fillPasswordField(PASS)
				.passNextButton.click();
		mainPage = new MainPage(driver);
		
		Reporter.logStep("Step 5 : Verify That The Msg is present");
		assertTrue(mainPage.checkIncomingMsg(USER_FIRST, RANDOM_TXT, RANDOM_TXT));
	}
	
	@Override
	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		super.tearDown();
		Reporter.info("Clean Test Data");
		MailAPI.deleteAllMailIn(USER_FIRST, PASS, SENT_FOLDER);
		MailAPI.deleteAllMailIn(USER_SECOND, PASS, INBOX_FOLDER);
	}

}
