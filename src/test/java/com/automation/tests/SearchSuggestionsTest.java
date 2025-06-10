package com.automation.tests;

import com.automation.constants.Constants;
import com.automation.pages.AmazonHomePage;
import com.automation.pages.SearchResultsPage;
import com.automation.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchSuggestionsTest extends BaseTest {

    private static final Logger logger= LogManager.getLogger(SearchSuggestionsTest.class);

    @Test(enabled=false)
    public void searchsuggestionTest()
    {
        driver.get(ConfigReader.get("base.url.amazon"));
     AmazonHomePage homePage=new AmazonHomePage(driver);
     SearchResultsPage resultsPage;
     logger.info("starting test to search and get the second last suggestion");
     homePage.enterSearchText("iphone");
     homePage.clickSecondLastSuggestion();

     resultsPage=new SearchResultsPage(driver);
     Assert.assertTrue(resultsPage.verifyTitleContains("iphone"),"title not matching expected title"+driver.getTitle());
     logger.info("verified title");
     resultsPage.clickFirstProduct();
     logger.info("Test completed: Successfully searched and clicked on product.");

    }

}
