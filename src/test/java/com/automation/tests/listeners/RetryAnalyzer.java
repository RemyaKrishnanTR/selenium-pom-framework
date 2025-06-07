package com.automation.tests.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*TestNG internally Calls retry(result) if a test fails, then check
If true → it schedules the test to run again
If false → marks the test as finally FAILED
After retries, final result is set
Only then, onTestFailure() from your ITestListener is called for final failure*/

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 3;  // retry max 3 times

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;  // retry this test
        }
        return false;     // no more retries
    }

    public int getRetryCount() {
        return retryCount;
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }
}