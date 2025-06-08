    package com.automation.pages;

    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
    import org.openqa.selenium.JavascriptExecutor;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.openqa.selenium.support.PageFactory;

    public class SearchResultsPage extends BasePage {
        private static final Logger logger= LogManager.getLogger(SearchResultsPage.class);
        private WebDriver driver;
        @FindBy(xpath="(//div[@data-component-type='s-search-result']//div//h2//span)[1]")
        private WebElement searchProduct;

        public SearchResultsPage(WebDriver driver)
        {
            super(driver);
            this.driver=driver;
            PageFactory.initElements(driver, this);
        }

        public void clickFirstProduct()
        {
            try {
                logger.info("Attempting to click on the first product from search results.");
                WebElement firstProduct = wait.waitForElementToBeClickable(searchProduct);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", firstProduct);
                firstProduct.click();
                logger.info("Successfully clicked on the first product.");
            } catch (Exception e) {
                logger.error("Failed to click on the first product: " + e.getMessage());
                throw e;
            }

        }

        public boolean verifyTitleContains(String keyword)
        {
            return driver.getTitle().toLowerCase().contains(keyword.toLowerCase());
        }
    }
