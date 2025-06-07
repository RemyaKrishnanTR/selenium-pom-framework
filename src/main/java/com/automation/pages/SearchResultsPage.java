package com.automation.pages;

import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage extends BasePage {
    private WebDriver driver;
    private By searchProduct= By.xpath("(//div[@data-component-type='s-search-result']//div//h2//span)[1]");

    public SearchResultsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }

    public void searchProduct()
    {
        WebElement firstProduct= wait.waitForElementToBeClickable(searchProduct);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", firstProduct);
        firstProduct.click();

    }
}
