package com.epam.gmail.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.epam.gmail.factory.DriverFactory;
import com.epam.gmail.property.PropertyProvider;

public class ScreenShotCapture {

	public static String getscreenshot() {
		try {
			String screen = PropertyProvider.getProperty("report_dir") + "screenShot//" + LocalDateTime.now().toString().replaceAll(":", "-") + ".png";
			File scrFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(screen));
			return screen;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}

}
