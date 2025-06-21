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

import java.util.List;
import java.util.Map;


@Listeners({com.automation.tests.listeners.TestListener.class, RetryListener.class})
public class AmazonTest extends BaseTest {

    private static final Logger logger= LogManager.getLogger(AmazonTest.class);
    private AmazonHomePage amazonHomePage;

    @BeforeMethod
    public void launchAmazonHomePage() {
        driver.get(ConfigReader.get("base.url.amazon"));
        amazonHomePage = new AmazonHomePage(driver);
    }

    @Test(enabled=false)
    public void verifyTitle()
    {
        String expectedTitle="Amazon.in";
        String actualTitle=driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle),"Title does not match");
    }

    @Test(enabled = false, dataProvider = "searchItems",dataProviderClass = DataProviders.class)
    public void searchItemTest(String item,String color)
    {
        amazonHomePage.enterSearchText(item);

        SearchResultsPage resultsPage=new SearchResultsPage(driver);
        resultsPage.clickFirstProduct();

        WindowHandler.switchToNewWindow(driver);

        ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
        String title=detailsPage.getProductTitle();
        String price=detailsPage.getPrice();
        logger.info("Product price is {}", price);
        logger.info("product title is {}", title);
        detailsPage.selectColor(color);
        detailsPage.selectQuantity("2");
        detailsPage.addToCart();
        detailsPage.clickCart();
        String cartCount=detailsPage.getCartCount();
        logger.info("Clicked on cart. Cart count is {}", cartCount);
        Assert.assertTrue(detailsPage.isAddedToCart(title),"Product not added to cart");
        Assert.assertTrue(title.toLowerCase().contains(item.toLowerCase()),
                "Expected product title to contain searched item: " + item + ", but got: " + title);
        Assert.assertEquals(cartCount,"2","product not added to cart");
    }

    /*Test Case:
    Amazon user hovers on “Account & Lists”, clicks “Your Orders”, and validates that the user is taken to the Your Orders page only if signed in, otherwise validates redirection to sign-in page.*/

    @Test
    public void verifyHoverAndClick()
    {

        amazonHomePage.hoverAndClick("Your Orders");

        String currentUrl=driver.getCurrentUrl().toLowerCase();
        String title=driver.getTitle().toLowerCase();

        logger.info("current url after navigation "+currentUrl);
        if (currentUrl.contains("signin") || title.contains("sign in"))
        {
            logger.info("user is not signed in. Redirected to sign in page");
            Assert.assertTrue(title.contains("sign in"));
        }
        else
        {
            logger.info("user is signed in.Verifying if 'Your Orders' page is displayed...");
            Assert.assertTrue(title.contains("your orders")||currentUrl.contains("your orders"),"Navigation to your orders failed");
        }
    }

    @Test
    public void verifyAllDropDownOptions()
    {
        List<String> options = amazonHomePage.getAllDropDownOptions();
        logger.info("Total dropdown options found"+options.size());

        for(String option:options)
        {
         logger.info("Option-> "+option);
        }
    }

    @Test
    public void verifyHoverMenuOptions()
    {
        Assert.assertTrue(amazonHomePage.isMenuOptionPresent("Your Orders"),"Your Orders option not found");
    }

    @Test
    public void verifyHoverMenuLinksHref()
    {
        Map<String,String> hoverLinks=amazonHomePage.getHoverMenuTextLinks();
        Assert.assertFalse(hoverLinks.isEmpty());// assertion becomes true when the condition becomes false
        hoverLinks.forEach((text,href)->
        {
            Assert.assertNotNull(href,"No href for the text");
            logger.info("Menu: "+ text +"->"+href);
        });
    }

    @Test
    public void verifyAutoCompleteKeyboardSelection()
    {
        amazonHomePage.enterSearchText("iphone");
        amazonHomePage.selectSearchSuggestionWithKeyboard("iphone",2);
        String currentUrl=driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(currentUrl.contains("iphone"),"Auto complete selection failed. Current url is:"+currentUrl);
    }
}
