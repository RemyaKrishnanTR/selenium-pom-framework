package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NotificationPage {
    WebDriver driver;
    WebDriverWait wait;
    private By toastTriggerButton = By.xpath("//button[@id='liveToastBtn']");
    private By toastBody = By.xpath("//*[@id='liveToast']//div[@class='toast-body']");

    public NotificationPage(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void clickToastButton()
    {
        WebElement btn=driver.findElement(toastTriggerButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",btn);
    }

    public String getToastMessage()
    {
        WebElement toast=wait.until(ExpectedConditions.visibilityOfElementLocated(toastBody));
        return toast.getText();
    }

    public boolean isToastDisappeared()
    {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(toastBody));
    }


}
