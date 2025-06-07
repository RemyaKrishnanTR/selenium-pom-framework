package com.automation.pages;

import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    protected void click(By locator)
    {
        getElement(locator).click();
    }
    protected void enterText(By locator,String text)
    {
        getElement(locator).sendKeys(text);
    }
    protected String getPageTitle()
    {
        return driver.getTitle();
    }

}
