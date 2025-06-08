package com.automation.pages;

import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    protected WebDriver driver;
    protected WaitUtils wait;

    public BasePage(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WaitUtils(driver,10);
    }

    protected WebElement getElement(By locator)
    {
        return driver.findElement(locator);
    }

    protected void click(WebElement element)
    {
        WebElement ele=wait.waitForElementToBeClickable(element);
        ele.click();
    }
    protected void enterText(WebElement element,String text)
    {
        WebElement ele=wait.waitForElementToBeVisible(element);
        ele.clear();
        ele.sendKeys(text);
    }
    protected String getPageTitle()
    {
        return driver.getTitle();
    }

}
