package com.automation.tests;

import com.automation.pages.NotificationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotificationTest extends BaseTest {
    Logger logger= LogManager.getLogger(NotificationTest.class);
    NotificationPage notificationPage;

    @BeforeMethod()
    public void setup() throws InterruptedException {
        super.setup();
        driver.get("https://getbootstrap.com/docs/5.0/components/toasts/#live-example");
        notificationPage=new NotificationPage(driver);
    }

    @Test(enabled = false)
    public void verifyToastMessage()
    {
        notificationPage.clickToastButton();
        String message=notificationPage.getToastMessage();
        Assert.assertEquals(message,"Hello, world! This is a toast message.");
        boolean flag= notificationPage.isToastDisappeared();
        Assert.assertTrue(flag,"Toast message should be disappeared after sometime");
    }


}
