package com.automation.pages;

import com.automation.pages.common.FooterComponent;
import com.automation.pages.common.HeaderComponent;
import com.automation.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AmazonHomePage extends BasePage {

    private final Actions actions;
    WaitUtils waitutil;
    public HeaderComponent header;
    public FooterComponent footer;

    private static final Logger logger = LogManager.getLogger(AmazonHomePage.class);

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchtextbox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(xpath = "//span[contains(text(),'Account & Lists')]")
    private WebElement accountAndListsLink;

    @FindBy(xpath = "//span[contains(text(),'Your Orders')]")
    private WebElement yourOrders;

    private static final By SEARCH_SUGGESTIONS = By.xpath("//div[@class='s-suggestion-container']//span");
    private static final By HOVER_MENU_OPTIONS = By.xpath("//div[@id='nav-al-your-account']//span");

    public AmazonHomePage(WebDriver driver) {
        super(driver);
        this.header=new HeaderComponent(driver);
        this.footer=new FooterComponent(driver);
        this.actions = new Actions(driver);
        this.waitutil = new WaitUtils(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void enterSearchText(String item) {
        enterText(searchtextbox, item);
    }

    public void clickSearchButton() {
        click(searchButton);
    }

    public void clickSecondLastSuggestion() {
        try {
            waitutil.waitForNumberOfElementsToBeMoreThan(SEARCH_SUGGESTIONS, 1);

            List<WebElement> searchsuggestions = driver.findElements(SEARCH_SUGGESTIONS);

            if (searchsuggestions.isEmpty()) {
                throw new RuntimeException("No search suggestions found");
            }
            waitutil.waitForElementToBeVisible(searchsuggestions.get(0));
            if (searchsuggestions.size() < 2) {
                logger.error("Not enough suggestions to click second last.");
                return;
            }
            WebElement secondLastSuggestion = searchsuggestions.get(searchsuggestions.size() - 2);
            waitutil.waitForElementToBeClickable(secondLastSuggestion).click();
            logger.info("Clicked second last suggestion successfully.");
        } catch (StaleElementReferenceException e) {
            logger.error("StaleElementReferenceException caught");
            throw e;
        } catch (Exception e) {
            logger.error("unexpected error while clicking second last sugegstion" + e.getMessage());
            throw e;
        }
    }

    public void hoverAndClick(String menuOption) {
        try {

            waitutil.waitForElementToBeVisible(accountAndListsLink);
            logger.info("Hovering over account and list option");
            actions.moveToElement(accountAndListsLink).perform();

            WebElement option = driver.findElement(By.xpath("//span[contains(text(),'" + menuOption + "')]"));
            waitutil.waitForElementToBeClickable(option).click();
            logger.info("Clicked on menu option: {}", menuOption);
        } catch (Exception e) {

            throw new RuntimeException("Hover and click failed" + e.getMessage());
        }
    }

    public List<String> getAllDropDownOptions() {
        List<String> optionList = new ArrayList<>();
        try {
            waitutil.waitForElementToBeVisible(accountAndListsLink);
            logger.info("Hovering over account and list option");
            actions.moveToElement(accountAndListsLink).perform();

            waitutil.waitForAllElementsToBeVisible(HOVER_MENU_OPTIONS);
            List<WebElement> options = driver.findElements(HOVER_MENU_OPTIONS);
            for (WebElement option : options) {
                String text = option.getText().trim();
                if (!text.isEmpty()) {
                    optionList.add(text);
                }
            }
            return optionList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch drop down options" + e.getMessage());
        }
    }

    public boolean isMenuOptionPresent(String menuOption) {
        try {
            waitutil.waitForElementToBeVisible(accountAndListsLink);
            actions.moveToElement(accountAndListsLink).perform();
            waitutil.waitForAllElementsToBeVisible(HOVER_MENU_OPTIONS);
            return driver.findElements(HOVER_MENU_OPTIONS).stream().anyMatch(element -> element.getText().trim().equalsIgnoreCase(menuOption));
        } catch (Exception e) {
            throw new RuntimeException("Error while checking presence of menu option: " + e.getMessage());

        }
    }

    public Map<String, String> getHoverMenuTextLinks() {
        Map<String, String> menuData = new HashMap<>();
        try {
            waitutil.waitForElementToBeVisible(accountAndListsLink);
            actions.moveToElement(accountAndListsLink).perform();
            waitutil.waitForAllElementsToBeVisible(HOVER_MENU_OPTIONS);
            List<WebElement> options = driver.findElements(HOVER_MENU_OPTIONS);
            for (WebElement option : options) {
                String text = option.getText().trim();
                try {
                    String href = option.findElement(By.xpath("./parent::a")).getAttribute("href");
                    if (!text.isEmpty()) {
                        menuData.put(text, href);
                    }
                } catch (RuntimeException e) {
                    logger.info("href not found for item: {}", text);
                }

            }
            return menuData;
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to fetch hover menu options" + e.getMessage());
        }
    }
    public void selectSearchSuggestionWithKeyboard(String query,int arrowPressCount)
    {
        try
        {
            waitutil.waitForElementToBeVisible(searchtextbox);
            enterSearchText(query);

            waitutil.waitForNumberOfElementsToBeMoreThan(SEARCH_SUGGESTIONS,0);

            logger.info("Navigating to autocomplete suggestions using keypress");
            for(int i=0;i<arrowPressCount;i++)
            {
                searchtextbox.sendKeys(Keys.ARROW_DOWN);
                Thread.sleep(300);
            }
            searchtextbox.sendKeys(Keys.ENTER);
            logger.info("Selected suggestion using ENTER after"+arrowPressCount+"arrow down presses");
        } catch (RuntimeException | InterruptedException e)
        {
            throw new RuntimeException("Failed to select suggestion via keyboard"+e.getMessage());
        }
    }

    public void searchProduct(String product)
    {
        searchtextbox.sendKeys(product);
        searchButton.click();
    }
}
