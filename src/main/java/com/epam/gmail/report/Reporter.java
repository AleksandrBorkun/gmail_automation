package com.epam.gmail.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.gmail.utils.ScreenShotCapture;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter {
	private static Logger log = LogManager.getLogger(Reporter.class);

    public static void info(String message) {
        log.info(message);
        ExtentTestManager.getTest().log(LogStatus.INFO, message);
    }
    
    public static void pageView(String message){
    	log.info(message);
    	ExtentTestManager.getTest().log(LogStatus.INFO, message + "\n" + "<img src = '" + ScreenShotCapture.getscreenshot() + "' width=\"200\" height=\"150\" alt = \"Some Image\"/>");
    }
    
    public static void logStep(String message) {
        log.info(message);
        ExtentTestManager.getTest().log(LogStatus.INFO, "<b>"+message+"</b>");
    }
    
    public static void testFailLog(String errorLog) {
        ExtentTestManager.getTest().log(LogStatus.FAIL, "<b>Failed</b>\n<b>Stack Trace :</b>\n"+ errorLog);
    }
    
    public static void testPassLog() {
        ExtentTestManager.getTest().log(LogStatus.PASS, "<b>Success</b>");
    }

	public static void testSkipLog() {
		ExtentTestManager.getTest().log(LogStatus.SKIP, "<b>SKIPED</b>");
		
	}
}
