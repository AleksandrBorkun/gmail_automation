package com.epam.gmail.listener;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.gmail.report.ExtentManager;
import com.epam.gmail.report.ExtentTestManager;
import com.epam.gmail.report.Reporter;

public class CustomListener implements ITestListener{

	Logger log = LogManager.getLogger();
	
@Override
public void onTestStart(ITestResult result) {
	log.info("Teststarted running:"  + result.getMethod().getMethodName() + " at:" + result.getStartMillis());
	ExtentTestManager.startTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
}

@Override
public void onTestSuccess(ITestResult result) {
	log.info(result.getMethod().getMethodName() + "Success");
	Reporter.testPassLog();
	ExtentTestManager.endTest();
	
}

@Override
public void onTestFailure(ITestResult result) {
	Reporter.testFailLog(result.getThrowable().getMessage() + "\n\n" + Arrays.toString(result.getThrowable().getStackTrace()));
	ExtentTestManager.endTest();
	result.getThrowable().printStackTrace();
}

@Override
public void onTestSkipped(ITestResult result) {
	Reporter.testSkipLog();
	ExtentTestManager.endTest();
	
}

@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	onTestFailure(result);
	}

@Override
public void onStart(ITestContext context) {
	log.info("Start Execute Automation Tests");
}

@Override
public void onFinish(ITestContext context) {
	log.info("Passeds: " + context.getPassedTests().size());
	log.info("Faileds:" + context.getFailedTests().size());
	log.info("Find the Extend Report Here " + ExtentManager.getLocation());
	ExtentManager.getInstance().close();
	
}
}