package com.epam.gmail.utils;

import org.testng.Assert;

import com.epam.gmail.report.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Assertion {
	static ExtentTest reporter = ExtentTestManager.getTest();

	static public void verifyTrue(boolean condition, String message) {
		String assertMsg = String.format("Verify True: <b>[%s]</b>", condition);
		try {
			Assert.assertTrue(condition, message);
			passedLog(assertMsg, message);
		} catch (AssertionError e) {
			failedLog(assertMsg, message);
			throw new AssertionError(message);
		}
	}

	static public void verifyTrue(boolean condition) {
		verifyTrue(condition, "");
	}

	static public void verifyFalse(boolean condition, String message) {
		String assertMsg = String.format("Verify False: <b>[%s]</b>", condition);
		try {
			Assert.assertFalse(condition, message);
			passedLog(assertMsg, message);
		} catch (AssertionError e) {
			failedLog(assertMsg, message);
			throw new AssertionError(message);
		}
	}

	static public void verifyEquals(Object actual, Object expected, String message) {
		String msg = String.format("Verify Equals : \nActual : <b>[%s]</b>\nExpected : <b>[%s]</b>", actual, expected);
		try {
			Assert.assertEquals(actual, expected, message);
			passedLog(msg, message);
		} catch (AssertionError e) {
			failedLog(msg, message);
			throw new AssertionError(message);

		}
	}

	static public void verifyNotEquals(Object actual1, Object actual2, String message) {
		String assertMsg = String.format("Verify Not Equals : \nActual : <b>[%s]</b>\nExpected : <b>[%s]</b>", actual1,
				actual2);
		try {
			Assert.assertNotEquals(actual2, actual2, message);
			passedLog(assertMsg, message);
		} catch (AssertionError e) {
			failedLog(assertMsg, message);
			throw new AssertionError(message);

		}
	}

	private static void passedLog(String assertMsg, String logMsg) {
		reporter.log(LogStatus.PASS, logMsg);
		reporter.log(LogStatus.INFO, assertMsg);
	}

	private static void failedLog(String assertMsg, String logMsg) {
		reporter.log(LogStatus.FAIL, logMsg);
		reporter.log(LogStatus.INFO, assertMsg);
	}
}
