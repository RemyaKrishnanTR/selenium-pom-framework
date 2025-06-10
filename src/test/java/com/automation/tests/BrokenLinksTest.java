package com.automation.tests;

import com.automation.constants.Constants;
import com.automation.utils.ConfigReader;
import com.automation.utils.LinkValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrokenLinksTest extends BaseTest {

    @Test(enabled=false)
    public void testBrokenLinks()
    {
        driver.get(ConfigReader.get("base.url.amazon"));
        LinkValidator.validateAllLinks(driver);
    }
}
