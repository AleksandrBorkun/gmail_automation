package com.epam.gmail.report;

import java.time.LocalDateTime;

import com.epam.gmail.property.PropertyProvider;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static final String reportFile = PropertyProvider.getProperty("report_dir") + LocalDateTime.now().toString().replaceAll(":", "-") + ".html";
	private static final ExtentReports Instance = new ExtentReports(reportFile, true);
	
	private ExtentManager(){
	}
	
	public static ExtentReports getInstance(){
		return Instance;
	}
	
	public static String getLocation(){
		return reportFile;
	}
	
}
