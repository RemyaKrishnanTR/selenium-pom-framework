package com.automation.tests;

import com.automation.pages.AlertPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AlertTest extends BaseTest {
    Logger logger= LogManager.getLogger(AlertTest.class);
    AlertPage alertPage;

    @BeforeMethod()
    public void setup() throws InterruptedException {
        super.setup();
        alertPage=new AlertPage(driver);
        driver.get("https://demoqa.com/alerts");
    }

    @Test(enabled = false)
    public void verifyAlert()
    {
        alertPage.getAlert();
       String alertText=alertPage.handleAlert();
        Assert.assertEquals(alertText,"You clicked a button");
    }

    @Test(enabled = false)
    public void verifyTimerAlert()
    {
        alertPage.getTimeAlert();
        String alertText=alertPage.handleAlert();
        Assert.assertEquals(alertText,"This alert appeared after 5 seconds");
    }

    @Test(enabled = false)
    public void verifyConfirmAlert()
    {
        alertPage.getConfirmAlert();
        String alertText=alertPage.handleConfirmAlert(true);
        Assert.assertEquals(alertText,"Do you confirm action?");
        Assert.assertTrue(alertPage.getConfirmResult().contains("Ok"));
    }

    @Test(enabled = false)
    public void verifyPromptAlert()
    {
        alertPage.getPromptAlert();
        String alertText=alertPage.handlePromptAlert("hey this is Remya");
        Assert.assertEquals(alertText,"Please enter your name");
        Assert.assertTrue(alertPage.getPromptResult().contains("Remya"));
    }


}
