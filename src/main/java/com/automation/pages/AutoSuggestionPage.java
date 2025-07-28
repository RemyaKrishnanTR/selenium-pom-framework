package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AutoSuggestionPage {
    WebDriver driver;

    @FindBy(xpath = "//div[@class='s-suggestion-container']")
    private List<WebElement> suggestionList;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchText;


    public AutoSuggestionPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void enterSearchText(String searchInput)
    {
        searchText.clear();
        searchText.sendKeys(searchInput);
    }

    public void selectSuggestion(String suggestedText)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(suggestionList));
        for (WebElement suggestion:suggestionList)
        {
            String text=suggestion.getText();
            if(text.equalsIgnoreCase(suggestedText))
            {
                suggestion.click();
                break;
            }
        }
    }

    // Fetch all suggestion texts
    public List<String> getSuggestionList()
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(suggestionList));
        return suggestionList.stream().map(WebElement::getText).toList();
    }

    // Select nth suggestion (like 2nd last, 3rd, etc.)
    public String selectNthSuggestion(int index)
    {

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(suggestionList));
        if(index>=suggestionList.size())
        {
            throw new RuntimeException("invalid index");
        }
        String text=suggestionList.get(index).getText();
        suggestionList.get(index).click();
        return text;
    }

    public boolean isSuggestionListDisplayed()
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(suggestionList));
        return suggestionList.size()>0 && suggestionList.get(0).isDisplayed();
    }
}
