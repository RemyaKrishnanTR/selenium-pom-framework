package com.automation.tests;

import com.automation.utils.LinkValidator;
import org.testng.annotations.Test;


public class BrokenLinksTest extends BaseTest {
    @Test
    public void testBrokenLinks()
    {
        LinkValidator.validateAllLinks(driver);
    }
}
