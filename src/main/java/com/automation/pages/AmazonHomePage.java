package com.automation.pages;

import com.automation.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class AmazonHomePage extends BasePage {
    WaitUtils waitutil;
    private static final Logger logger= LogManager.getLogger(AmazonHomePage.class);
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchtextbox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    public AmazonHomePage(WebDriver driver) {
        super(driver);
        this.waitutil=new WaitUtils(driver,10);
        PageFactory.initElements(driver, this);
    }

    public void enterSearchText(String item) {
        enterText(searchtextbox, item);
    }

    public void clickSearchButton()
    {
        click(searchButton);
    }

    public void clickSecondLastSuggestion() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='s-suggestion-container']//span"), 1));
                List<WebElement> searchsuggestions = driver.findElements(By.xpath("//div[@class='s-suggestion-container']//span"));
                if (searchsuggestions==null || searchsuggestions.isEmpty())
                {
                    throw new RuntimeException("No search suggestions found");
                }
                waitutil.waitForElementToBeVisible(searchsuggestions.get(0));
                if (searchsuggestions.size() < 2) {
                    logger.error("Not enough suggestions to click second last.");
                    return;
                }
                WebElement secondLastSuggestion = searchsuggestions.get(searchsuggestions.size() - 2);
                wait.until(ExpectedConditions.elementToBeClickable(secondLastSuggestion)).click();
                logger.info("Clicked second last suggestion successfully.");
             } catch (StaleElementReferenceException e) {
                logger.error("StaleElementReferenceException caught");
                throw e;
            } catch (Exception e) {
                logger.error("unexpected error while clicking second last sugegstion"+e.getMessage());
                throw e;
            }
        }

    }
