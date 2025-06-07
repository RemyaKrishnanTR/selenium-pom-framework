package com.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScreenshotUtils {
    private static final Logger logger= LogManager.getLogger(ScreenshotUtils.class);

    public static String takeScreenshot(WebDriver driver, String testname) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportDir = System.getProperty("user.dir") + "/screenshots/";
        String filePath = reportDir + testname + " " + timeStamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(filePath));
            logger.info("screenshot saved successfully at {}",filePath);
        } catch (IOException e) {
            logger.error("Failed to take scrrenshot for{}", testname, e);
        }

        return filePath;
    }
}