package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WindowHandlingPage {
    WebDriver driver;
    WebDriverWait wait;

    private By tabButton=By.id("tabButton");
    private By windowButton=By.id("windowButton");
    private By windowMessageButton=By.id("messageWindowButton");
    private By newTabMessage=By.xpath("//h1[@id='sampleHeading']");
    private By newWindowMessage=By.xpath("//body");

    public WindowHandlingPage(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickNewTabButton()
    {
        WebElement btn=driver.findElement(tabButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",btn);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",btn);

    }
    public void clickNewWindowButton()
    {
        WebElement btn=driver.findElement(windowButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",btn);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",btn);
    }
    public void clickNewWindowMessageButton()
    {
        WebElement btn=driver.findElement(windowMessageButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",btn);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",btn);
    }

    public void switchToNewTab(String actualHandle)
    {

        Set<String> handles=driver.getWindowHandles();
        for(String handle:handles)
        {
            if(!handle.equals(actualHandle))
            {
                driver.switchTo().window(handle);
                break;
            }

        }
    }

    public String getNewWindowHeadingText()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(newTabMessage));
        return driver.findElement(newTabMessage).getText();
    }

    public String getWindowMessage()
    {

        WebElement body=wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        return body.getText();
    }

    public void closeCurrentWindowAndSwitchBack(String actualHandle)
    {
        driver.close();
        driver.switchTo().window(actualHandle);
    }
}
