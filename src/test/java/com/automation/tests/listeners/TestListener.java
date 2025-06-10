package com.automation.tests.listeners;

import com.automation.reports.ExtentManager;
import com.automation.reports.ExtentTestManager;
import com.automation.tests.AmazonTest;
import com.automation.tests.BaseTest;
import com.automation.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends com.automation.tests.BaseTest implements ITestListener {
    private static final Logger logger= LogManager.getLogger(TestListener.class);

    private ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.setTest(extent.createTest(result.getMethod().getMethodName()).assignAuthor("QA").assignCategory("Regression").assignDevice("chrome"));
    }
    /*In TestNG, the ITestResult object holds the outcome of a test method.
    result.getThrowable() returns the exception (Throwable) that caused the test to fail.
    If the test passed, there will be no exception, so getThrowable() will return null*/
    @Override
    public void onTestSuccess(ITestResult result) {
        if (result.getThrowable() == null) {
            ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Check if RetryAnalyzer is present and retries are not exhausted
        Object retryAnalyzerObj = result.getMethod().getRetryAnalyzer(result);
        if (retryAnalyzerObj instanceof RetryAnalyzer) {
            RetryAnalyzer retryAnalyzer = (RetryAnalyzer) retryAnalyzerObj;

            // If retries are still available, mark this failure as SKIP to avoid cluttering report
            if (retryAnalyzer.getRetryCount() < retryAnalyzer.getMaxRetryCount()) {
                result.setStatus(ITestResult.SKIP);
                logger.info("Retry attempt " + retryAnalyzer.getRetryCount() + " failed, marked as SKIP for test: " + result.getMethod().getMethodName());
                return; // Skip further failure processing for retry attempts
            } else {
                // Last retry failed, mark as FAILURE
                result.setStatus(ITestResult.FAILURE);
            }
        }

        // Your existing failure handling code:
        BaseTest testInstance = (BaseTest) result.getInstance();
        driver = testInstance.getDriver();
        String testName = result.getMethod().getMethodName();
        String path = ScreenshotUtils.takeScreenshot(driver, testName);
        logger.error("Test Failed: {} | screenshot saved at {}", testName, path);

        ExtentTestManager.getTest().fail(result.getThrowable());
        try {
            ExtentTestManager.getTest().addScreenCaptureFromPath(path, "Screenshot of failure: " + testName)
                    .log(Status.FAIL, "Test failed: " + testName);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
        }
    }



    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }





}
