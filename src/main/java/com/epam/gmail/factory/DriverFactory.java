package com.epam.gmail.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

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
	private static Map<String, Supplier<WebDriver>> driverMap;

	public static WebDriver getDriver(String browser) {
		if (driver == null) {
			driver = driverMap.get(browser).get();
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(25, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static WebDriver getDriver() {
		return driver != null ? driver : getDriver("chrome");
	}

	private static WebDriver createFireFoxDriver() {
		FirefoxDriverManager.getInstance().setup();
		log.info("Opening FireFox Browser");
		return new FirefoxDriver();
	}

	private static WebDriver createChromeDriver() {
		ChromeDriverManager.getInstance().setup();
		log.info("Opening CHROME Browser");
		return new ChromeDriver();
	}

	private static WebDriver createIEDriver() {
		InternetExplorerDriverManager.getInstance().setup();
		log.info("Opening IE Browser");
		return new InternetExplorerDriver();
	}

	static {
		driverMap = new HashMap<>();
		driverMap.put("chrome", () -> createChromeDriver());
		driverMap.put("firefox", () -> createFireFoxDriver());
		driverMap.put("IE", () -> createIEDriver());
	}

}
