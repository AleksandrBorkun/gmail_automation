package com.epam.gmail.driverFactory;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

public class DriverFactory {

	private static Logger log = LogManager.getLogger();
	private static WebDriver driver;

	public static WebDriver getDriver(String browser) {
		//final String browser = PropertyProvider.getProperty("browser");
		if (driver == null) {
			switch (browser) {

			case "firefox":
				FirefoxDriverManager.getInstance().setup();
				driver = new FirefoxDriver();
				log.info("Opening FireFox Browser");
				break;

			case "chrome":
				ChromeDriverManager.getInstance().setup();
				driver = new ChromeDriver();
				log.info("Opening CHROME BrowserS");
				break;

			case "IE":
				InternetExplorerDriverManager.getInstance().setup();
				driver = new InternetExplorerDriver();
				log.info("Opening IE Browser");
				break;
				
			default : 
				throw new IllegalStateException("Incorrect Browser Choosen");
			}
	        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
	        driver.manage().timeouts().setScriptTimeout(25, TimeUnit.SECONDS);
	        driver.manage().window().maximize();
		}

		return driver;
	}
	
	public static WebDriver getDriver(){
		return getDriver("chrome");
	}
}
