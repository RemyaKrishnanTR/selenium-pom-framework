package com.automation.tests;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.automation.pages.TestCaseGenerator.generateTestcases;

public class TestCaseGeneratorTest {
    @Test(enabled = false)
    public void verifyTestCaseGeneration()
    {
        Map<String,List<String>> config=new HashMap<>();
        config.put("Browser",Arrays.asList("Chrome","Firefox"));
        config.put("OS",Arrays.asList("Windows","mac"));
        config.put("Language",Arrays.asList("English","Spanish"));
        List<Map<String,String>> testCases=generateTestcases(config);
        for(Map<String,String> testcase:testCases)
        {
            System.out.println(testcase);
        }
    }
}
