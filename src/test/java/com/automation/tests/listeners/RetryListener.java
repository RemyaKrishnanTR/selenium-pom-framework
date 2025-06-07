package com.automation.tests.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
/*If any retry passes, the test is marked as PASS.
If all retries fail, then the test is finally marked as FAIL.
All intermediate failed attempts show as Skipped/Ignored by default.
When TestNG runs a test method and it fails, it checks:
“Is there a RetryAnalyzer associated with this test?”
If yes, then TestNG calls the retry(ITestResult result) method on your RetryAnalyzer class automatically.
*/

public class RetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }