package com.automation.tests;

import com.automation.pages.ToolTipPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ToolTipTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ToolTipTest.class);
    ToolTipPage toolTipPage;
    @BeforeMethod()
    public void setup() throws InterruptedException {
        super.setup();
        driver.get("https://demoqa.com/tool-tips");
        toolTipPage = new ToolTipPage(driver);

    }

    @Test(enabled = false)
    public void verifyButtonToolTip()
    {
        String text=toolTipPage.getButtonToolTip();
        logger.info("Verifying tooltip for button");
        Assert.assertEquals(text, "You hovered over the Button", "Tooltip text mismatch for button.");

    }

    @Test(enabled = false)
    public void verifyTextBoxToolTip()
    {
        String text=toolTipPage.getTextBoxToolTip();
        Assert.assertEquals(text, "You hovered over the text field","Tooltip text mismatch for textbox.");

    }


}

