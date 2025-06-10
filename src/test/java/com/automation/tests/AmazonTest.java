package com.automation.tests;

import com.automation.constants.Constants;
import com.automation.pages.ProductDetailsPage;
import com.automation.pages.SearchResultsPage;
import com.automation.tests.listeners.RetryAnalyzer;
import com.automation.tests.listeners.RetryListener;
import com.automation.utils.ConfigReader;
import com.automation.utils.DataProviders;
import com.automation.utils.WindowHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automation.pages.AmazonHomePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;


@Listeners({com.automation.tests.listeners.TestListener.class, RetryListener.class})
public class AmazonTest extends BaseTest {

    private static final Logger logger= LogManager.getLogger(AmazonTest.class);

    @Test(enabled=false)
    public void verifyTitle()
    {
        driver.get(ConfigReader.get("base.url.amazon"));
        String expectedTitle="Amazon.in";
        String actualTitle=driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle),"Title does not match");
    }

    @Test(enabled = false, dataProvider = "searchItems",dataProviderClass = DataProviders.class)
    public void searchItemTest(String item,String color){
        driver.get(ConfigReader.get("base.url.amazon"));
        AmazonHomePage hm= new AmazonHomePage(driver);
        hm.enterSearchText(item);

        SearchResultsPage resultsPage=new SearchResultsPage(driver);
        resultsPage.clickFirstProduct();

        WindowHandler.switchToNewWindow(driver);

        ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
        String title=detailsPage.getProductTitle();
        String price=detailsPage.getPrice();
        logger.info("product price is{}", price);
        logger.info("product title is {}", title);
        detailsPage.selectColor(color);
        detailsPage.selectQuantity("2");
        detailsPage.addToCart();
        detailsPage.clickCart();
        String cartCount=detailsPage.getCartCount();
        logger.info("clicked on cart and the car count is {}",cartCount);
        Assert.assertTrue(detailsPage.isAddedToCart(title),"Product not added to cart");
        Assert.assertTrue(title.toLowerCase().contains(item.toLowerCase()),"product title does not contain searched item"+item);
        Assert.assertEquals(cartCount,"2","product not added to cart");
    }

}
