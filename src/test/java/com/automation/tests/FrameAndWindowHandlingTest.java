package com.automation.tests;

import com.automation.pages.IframePage;
import com.automation.pages.WindowHandlingPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameAndWindowHandlingTest extends BaseTest {

    Logger logger= LogManager.getLogger(FrameAndWindowHandlingTest.class);

    IframePage framesPage;
    WindowHandlingPage windowPage;

    @Test(priority = 4,enabled = false)
    public void verifySwitchToFrame()
    {
        driver.get("https://demoqa.com/frames");
        framesPage=new IframePage(driver);
        framesPage.switchToFrame();
        String text= framesPage.getFrameText();
        Assert.assertEquals(text, "This is a sample page");
        framesPage.switchToParentPage();
    }

    @Test(priority = 1,enabled = false)
    public void verifySwitchToTab()
    {
        driver.get("https://demoqa.com/browser-windows");
        String mainWindow= driver.getWindowHandle();
        windowPage=new WindowHandlingPage(driver);
        windowPage.clickNewTabButton();

        windowPage.switchToNewTab(mainWindow);

        String text=windowPage.getNewWindowHeadingText();
        Assert.assertEquals(text,"This is a sample page");

        windowPage.closeCurrentWindowAndSwitchBack(mainWindow);
    }

    @Test(priority = 2,enabled = false)
    public void verifySwitchToWindow()
    {
        driver.get("https://demoqa.com/browser-windows");
        String mainWindow= driver.getWindowHandle();
        windowPage=new WindowHandlingPage(driver);
        windowPage.clickNewWindowButton();

        windowPage.switchToNewTab(mainWindow);

        String text=windowPage.getNewWindowHeadingText();
        Assert.assertEquals(text,"This is a sample page");

        windowPage.closeCurrentWindowAndSwitchBack(mainWindow);
    }

    @Test(enabled = false, priority = 3)
    public void verifySwitchToWindowWithMessage()
    {
        driver.get("https://demoqa.com/browser-windows");
        String mainWindow= driver.getWindowHandle();
        windowPage=new WindowHandlingPage(driver);
        windowPage.clickNewWindowMessageButton();

        windowPage.switchToNewTab(mainWindow);

        String text=windowPage.getWindowMessage();
        Assert.assertTrue(text.contains("Knowledge"));

        windowPage.closeCurrentWindowAndSwitchBack(mainWindow);
    }


}
