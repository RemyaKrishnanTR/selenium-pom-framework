package com.automation.Reports;

import com.aventstack.extentreports.ExtentTest;
/*ExtentTestManager
It uses a ThreadLocal<ExtentTest> to keep track of the current test being run by each thread (parallel-safe).
ThreadLocal gives each thread its own copy of the ExtentTest object
You use:
setTest() → to start logging for a test
getTest() → to log steps/status for that test
What is ExtentTest?
ExtentTest is a class from the ExtentReports library.
It represents a single test case’s report instance.
You use ExtentTest to log steps, results, and attach screenshots related to one test.
*/

public class ExtentTestManager {
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }
}
