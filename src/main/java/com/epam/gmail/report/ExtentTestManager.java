package com.epam.gmail.report;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager { 
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ExtentTest test;

    public static ExtentTest getTest() {
        return test;
    }

    public static void endTest() {
        extent.endTest(test);
        extent.flush();
    }

    public static ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static ExtentTest startTest(String testName, String desc) {
		test = extent.startTest(testName, "[Description: " + desc + "]");
        return test;
    }
}
