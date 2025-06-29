package com.automation.pages;

import com.automation.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DataTablePage {
    private static final Logger logger= LogManager.getLogger(DataTablePage.class);
    private WebDriver driver;

    private By tableRows=By.xpath("//table[@class='display dataTable']//tbody//tr");
    private  By nextButton=By.xpath("//button[@class='dt-paging-button next']");

    public DataTablePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public boolean searchAcrossPage(String targetName) throws InterruptedException {
        boolean isFound=false;
        while(true)
        {
            List<WebElement> rows=driver.findElements(tableRows);
            for(WebElement row:rows)
            {
                String name=row.findElements(By.tagName("td")).get(0).getText().trim();
                if(name.equalsIgnoreCase(targetName))
                {
                    isFound=true;
                    break;
                }
            }
            if(isFound)
            {
                break;
            }
            Thread.sleep(1000);
            List<WebElement> nextButtons=driver.findElements(nextButton);
            if(nextButtons.isEmpty())
            {
                logger.error("next button is disabled, reached last page");
                break;
            }


            WebElement nextBtn = nextButtons.get(0);
            String nextClass = nextBtn.getAttribute("class");

            if (nextClass!=null && nextClass.contains("disabled")) {
                logger.error("Reached last page but record not found.");
                break;
            }

            WebElement oldFirstRow = rows.get(0);
            nextBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.stalenessOf(oldFirstRow));
        }
        return isFound;
    }
}
