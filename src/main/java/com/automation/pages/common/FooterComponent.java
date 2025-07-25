package com.automation.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterComponent {

    private WebDriver driver;

    private By conditionsLink = By.linkText("Conditions of Use & Sale");

    public FooterComponent(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isConditionsLinkDisplayed() {
        return driver.findElement(conditionsLink).isDisplayed();
    }
}
