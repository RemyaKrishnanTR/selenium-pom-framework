package com.automation.tests;

import com.automation.pages.WebTablePage;
import com.automation.utils.ConfigReader;
import com.automation.utils.ExcelUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.automation.constants.Constants.EXCEL_PATH;
import static com.automation.constants.Constants.WEBTABLE_EXCEL_PATH;

public class WebTableTest extends BaseTest {

    WebTablePage webTablePage;
    private static final Logger logger= LogManager.getLogger(WebTableTest.class);
    ExcelUtil excelUtil;

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

    @Test(enabled = false)
    public void exportWebTableToExcel() throws IOException {

        driver.get(ConfigReader.get("base.url.webtables"));
        webTablePage=new WebTablePage(driver);
        List<String> headers=webTablePage.getHeaders();
        List<List<String>> tabledata=new ArrayList<>();

        for(WebElement row: webTablePage.getAllRows())
        {
            List<String> rowdata=new ArrayList<>();
            for(WebElement col: webTablePage.getColumnsInRow(row))
            {
                rowdata.add(col.getText().trim());
            }
            tabledata.add(rowdata);
        }
        excelUtil=new ExcelUtil(EXCEL_PATH,"Webtable");
        excelUtil.writeDataToExcel(EXCEL_PATH,"Webtable",headers,tabledata);
        logger.info("webtable exported to excel"+EXCEL_PATH);
        Assert.assertTrue(new File(EXCEL_PATH).exists(),"Excel file not created");
    }


    @Test(enabled = false)
     public void validateExportedExcel() throws IOException {
        driver.get(ConfigReader.get("base.url.webtables"));
        webTablePage = new WebTablePage(driver);

        List<String> expectedHeaders=webTablePage.getHeaders();
        int expectedCount=webTablePage.getAllRows().size();
        List<Integer> criticalCols=List.of(0,3);
        excelUtil=new ExcelUtil(EXCEL_PATH,"Webtable");
        boolean result=excelUtil.validateExcel(EXCEL_PATH,expectedHeaders,expectedCount,criticalCols);
        Assert.assertTrue(result,"validation failed,Excel contents does not match");
    }


    @Test(enabled = false)
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

    @Test(enabled = false)
    public void webTableValidation() throws IOException {
        driver.get(ConfigReader.get("base.url.demowebtable"));
        excelUtil=new ExcelUtil(WEBTABLE_EXCEL_PATH,"webTable");
        webTablePage=new WebTablePage(driver);
        List<Map<String,String>> excelData=excelUtil.readFromExcel(WEBTABLE_EXCEL_PATH,"webTable");
        List<Map<String,String>> uiData=webTablePage.getWebTableData();
        Assert.assertEquals(uiData.size(),excelData.size(),"data count not match");
        for(int i=0;i<uiData.size();i++) {
            Map<String, String> actualData = uiData.get(i);
            Map<String, String> expectedData = excelData.get(i);
            Assert.assertEquals(actualData, expectedData, "Row mismatch at" + i + 1);
        }
    }

}
