package com.automation.tests;

import com.automation.pages.WebTablePage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.StringJoiner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebTableTest extends BaseTest {

    WebTablePage webTablePage;
    private static final Logger logger= LogManager.getLogger(WebTableTest.class);

    @Test(enabled = false)
    public void readWebTableData()
    {
        driver.get(ConfigReader.get("base.url.webtables"));
        webTablePage=new WebTablePage(driver);
        List<WebElement> rows=webTablePage.getAllRows();
        for(WebElement row:rows)
        {
            List<WebElement> cols=webTablePage.getColumnsInRow(row);
            StringJoiner joiner=new StringJoiner(" | ");
            for(WebElement col:cols)
            {
                joiner.add(col.getText().trim());
            }
            logger.info(joiner.toString());
            System.out.println();
        }
    }

    @Test(enabled = true)
    public void validateHeaders()
    {
        driver.get(ConfigReader.get("base.url.webtables"));
        webTablePage=new WebTablePage(driver);

        List<String> actualheaders=webTablePage.getHeaders();
        List<String> expectedheaders=List.of( "Company",
                "Group",
                "Prev Close (Rs)",
                "Current Price (Rs)",
                "% Change");
        Assert.assertEquals(actualheaders,expectedheaders,"Table headers did not match expected columns");



    }
}
