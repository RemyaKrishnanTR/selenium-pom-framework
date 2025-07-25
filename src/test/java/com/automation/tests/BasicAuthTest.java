package com.automation.tests;

import com.automation.pages.BasicAuthPage;
import com.automation.utils.KeyUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class BasicAuthTest extends BaseTest {
    BasicAuthPage basicAuthPage;

    @Test(enabled = false)
    public void verifyBasicAuthViaURL() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        basicAuthPage = new BasicAuthPage(driver);
        String message = basicAuthPage.getConfirmationMessage();

        Assert.assertTrue(message.contains("Congratulations"), "Basic Auth failed or incorrect message");
    }

    @Test(enabled = false)
    public void handleAuthPopUsinfRobotUtil() throws InterruptedException, AWTException {
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Thread.sleep(2000);
        Robot robot=new Robot();
        KeyUtils.typeText(robot,"admin");
        KeyUtils.pressTab(robot);
        KeyUtils.typeText(robot,"admin");
        KeyUtils.pressEnter(robot);
        Thread.sleep(3000);
    }

}
