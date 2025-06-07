package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage extends BasePage {
    private By searchtextBox= By.id("twotabsearchtextbox");
    private By searchButton=By.id("nav-search-submit-button");

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    public void searchItem(String item)
    {
        enterText(searchtextBox,item);
        click(searchButton);
    }

}
