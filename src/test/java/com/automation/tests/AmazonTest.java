package com.automation.tests;

import com.automation.pages.ProductDetailsPage;
import com.automation.pages.SearchResultsPage;
import com.automation.tests.listeners.RetryAnalyzer;
import com.automation.tests.listeners.RetryListener;
import com.automation.utils.DataProviders;
import com.automation.utils.WindowHandler;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automation.pages.AmazonHomePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Listeners({com.automation.tests.listeners.TestListener.class, RetryListener.class})
public class AmazonTest extends BaseTest {
    private static final Logger logger= LogManager.getLogger(AmazonTest.class);
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyTitle()
    {
        String expectedTitle="Amazon.in";
        String actualTitle=driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle),"Title does not match");
    }
    @Test(dataProvider = "searchItems",dataProviderClass = DataProviders.class)
    public void searchItemTest(String item){
        AmazonHomePage hm= new AmazonHomePage(driver);
        hm.searchItem(item);

        SearchResultsPage resultsPage=new SearchResultsPage(driver);
        resultsPage.searchProduct();

        WindowHandler.switchToNewWindow(driver);

        ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
        String title=detailsPage.getProductTitle();
        String price=detailsPage.getPrice();
        logger.info("product price is{}", price);
        logger.info("product title is {}", title);

        detailsPage.addToCart();
        detailsPage.clickCart();
        String cartCount=detailsPage.getCartCount();
        logger.info("clicked on cart and the car count is {}",cartCount);
        Assert.assertTrue(detailsPage.isAddedToCart(title),"Product not added to cart");
        Assert.assertTrue(title.toLowerCase().contains(item.toLowerCase()),"product title does not contain searched item"+item);
        Assert.assertEquals(cartCount,"1","product not added to cart");
    }

}
