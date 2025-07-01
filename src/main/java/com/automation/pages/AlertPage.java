package com.automation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertPage {
    WebDriver driver;
    WebDriverWait wait;

    private By alertButton=By.id("alertButton");
    private By timeralertButton=By.id("timerAlertButton");
    private By confirmAlertButton=By.id("confirmButton");
    private By promptAlertButton=By.id("promtButton");
    private By confirmResult=By.id("confirmResult");
    private By promptResult=By.id("promptResult");

    public AlertPage(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void getAlert()
    {
        driver.findElement(alertButton).click();
    }

    public void getTimeAlert()
    {
        driver.findElement(timeralertButton).click();
    }

    public void getConfirmAlert()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmAlertButton));
        WebElement btn=driver.findElement(confirmAlertButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",btn);
    }

    public void getPromptAlert()
    {
        WebElement btn=driver.findElement(promptAlertButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",btn);
    }

    public String handleAlert()
    {
      Alert alert  = wait.until(ExpectedConditions.alertIsPresent());
      String message= alert.getText();
      alert.accept();
      return message;
    }

    public String handleConfirmAlert(boolean accept) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return message;
    }

    public String handlePromptAlert(String text)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();
        alert.sendKeys(text);
        alert.accept();
        return message;
    }

    public String getConfirmResult()
    {
       return driver.findElement(confirmResult).getText();
    }

    public String getPromptResult()
    {
        return driver.findElement(promptResult).getText();
    }
}
