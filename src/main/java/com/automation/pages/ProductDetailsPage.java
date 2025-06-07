package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductDetailsPage extends BasePage {
    private static final Logger logger= LogManager.getLogger(ProductDetailsPage.class);
    private WebDriver driver;
    private By productTitle = By.id("productTitle");
    private By productPrice=By.xpath("//span[contains(@class, 'a-price aok-align-center ')]//span[@class='a-price-whole']");
    private By addToCart =By.id("add-to-cart-button");
    private By cartIcon=By.id("nav-cart-count-container");
    private By cartItemTitle =By.xpath("//span[@class='a-truncate-cut']");
    private By cartConfirmation=By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[contains(text(),'Added to cart')]");
    private By cartCount=By.xpath("//span[@data-a-selector='value']");
    public ProductDetailsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }

    public String getProductTitle(){
        WebElement productTitleElement= wait.waitForPresenceOfElement(productTitle);
        return productTitleElement.getText();
    }

    public String getPrice()
    {

        return driver.findElement(productPrice).getText();
    }

    public void addToCart()
    {
        driver.findElement(addToCart).click();
    }
    public void clickCart()
    {
        WebElement cart=wait.waitForElementToBeClickable(cartIcon);
        cart.click();
    }

    public boolean isAddedToCart(String expectedTitle)
    {
        try {

            WebElement cartTitleElement = wait.waitForPresenceOfElement(cartItemTitle);
            String actualTitle = cartTitleElement.getText().trim();
            logger.info("Expected Title: {}", expectedTitle);
            logger.info("Cart Title: {}", actualTitle);
            return expectedTitle.toLowerCase().contains(actualTitle.toLowerCase().replace("…", "").trim());
        }
        catch (Exception e) {
            logger.error("Exception in isAddedRoCart{}", e.getMessage());
            return false;
        }
    }
    public String getCartCount()
    {
        return driver.findElement(cartCount).getText();

    }
}
