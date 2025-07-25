package com.automation.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent {

    private WebDriver driver;

    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");
    private By cartIcon = By.id("nav-cart");

    public HeaderComponent(WebDriver driver)
    {
        this.driver=driver;
    }

    public void searchProduct(String product) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchButton).click();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();
    }

}
