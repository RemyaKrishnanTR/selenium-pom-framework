package com.automation.pages;

import com.automation.utils.WaitUtils;
import org.openqa.selenium.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class ProductDetailsPage extends BasePage {
    private static final Logger logger= LogManager.getLogger(ProductDetailsPage.class);
    private WebDriver driver;

    @FindBy(id = "productTitle")
    private WebElement productTitle;

    @FindBy(xpath= "//span[contains(@class, 'a-price aok-align-center ')]//span[@class='a-price-whole']")
    private WebElement productPrice;

    @FindBy(id="add-to-cart-button")
    private WebElement addToCart;

    @FindBy(id="nav-cart-count-container")
    private WebElement cartIcon;

    @FindBy(xpath = "//span[@class='a-truncate-cut']")
    private WebElement cartItemTitle;

    @FindBy(xpath="//div[@id='attachDisplayAddBaseAlert']//h4[contains(text(),'Added to cart')]")
    private WebElement cartConfirmation;

    @FindBy(xpath="//span[@data-a-selector='value']")
    private WebElement cartCount;

    @FindBy(xpath="//select[(@name='quantity')]")
    private WebElement quantitydd;


    public ProductDetailsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String getProductTitle(){
        WebElement productTitleElement= wait.waitForElementToBeVisible(productTitle);
        return productTitleElement.getText();
    }

    public String getPrice()
    {

        return productPrice.getText();
    }

    public void addToCart()
    {
       try {
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
           Thread.sleep(2000);
           ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);
           logger.info("Clicked on Add to Cart button using JS executor.");
       } catch (Exception e) {
           logger.error("failed to add to cart",e.getMessage());
       }
    }

    public void clickCart()
    {
        WebElement cart=wait.waitForElementToBeClickable(cartIcon);
        cart.click();
    }

    public boolean isAddedToCart(String expectedTitle)
    {
        try {

            WebElement cartTitleElement = wait.waitForElementToBeVisible(cartItemTitle);
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
        return cartCount.getText();

    }

    public void selectQuantity(String quantity)
    {
        try {
            logger.info("waiting for quantity to get selected");
            wait.waitForElementToBeVisible(quantitydd);
            if (quantitydd.isDisplayed() && quantitydd.isEnabled()) {
                Select select = new Select(quantitydd);
                select.selectByVisibleText(quantity);
                quantitydd.sendKeys(Keys.ESCAPE);
                logger.info("quantity selected");
            } else {
                logger.warn("Quantity dropdown not interactable");
            }
        } catch (Exception e) {
            logger.error("Error selecting quantity: " + e.getMessage());
        }
    }

    public void selectColor(String color)
    {
        String colorXpath="//span[contains(@class,'swatch-button-with-slots') and .//img[@alt='"+color+"']]";
        WebElement colorElement=driver.findElement(By.xpath(colorXpath));
        colorElement.click();
        logger.info(color+" is selected.");
    }
}
