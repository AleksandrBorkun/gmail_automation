package com.epam.gmail.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.epam.gmail.factory.DriverFactory;
import com.epam.gmail.listener.CustomListener;
import com.epam.gmail.page.LoginPage;

@Listeners(CustomListener.class)
public abstract class BaseTest {
	protected WebDriver driver;
	protected LoginPage loginPage;
	
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser){
		driver = DriverFactory.getDriver(browser);
		driver.get("http://www.gmail.com");
		loginPage = new LoginPage(driver);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		driver.close();
	}	
}
