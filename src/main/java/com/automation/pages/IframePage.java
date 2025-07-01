package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IframePage {

    WebDriver driver;
    WebDriverWait wait;

    By frame=By.id("frame1");
    By frameText=By.id("sampleHeading");

    public IframePage(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void switchToFrame()
    {
        driver.switchTo().frame("frame1");
    }

    public String getFrameText()
    {
        return driver.findElement(frameText).getText();
    }

    public void switchToParentPage()
    {
        driver.switchTo().defaultContent();
    }
}
