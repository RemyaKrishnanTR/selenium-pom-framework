package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToolTipPage {
    WebDriver driver;
     private By buttonToolTip= By.id("toolTipButton");
    private By textBoxToolTip = By.id("toolTipTextField");
    private By tooltipText = By.cssSelector(".tooltip-inner");

    public ToolTipPage(WebDriver driver)
    {
        this.driver=driver;
    }

    public String hoverAndGetToolTip(By locator)
    {
        WebElement element=driver.findElement(locator);
        new Actions(driver).moveToElement(element).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(tooltipText));

        return driver.findElement(tooltipText).getText();
    }

    public String getButtonToolTip()
    {
        return hoverAndGetToolTip(buttonToolTip);
    }

    public String getTextBoxToolTip()
    {
        return hoverAndGetToolTip(textBoxToolTip);
    }
}
