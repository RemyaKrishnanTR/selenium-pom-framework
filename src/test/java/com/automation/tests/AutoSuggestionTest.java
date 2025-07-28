package com.automation.tests;

import com.automation.pages.AutoSuggestionPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AutoSuggestionTest extends BaseTest {
    ExcelUtil util;
    String filepath = "src/test/resources/testData/searchData.xlsx";
    String sheet = "AutoSuggestTests";

    @BeforeClass
    public void setUpExcel() throws IOException {
        util = new ExcelUtil(filepath, sheet);
    }

    @AfterClass
    public void tearDownExcel() throws IOException {
        if (util != null) {
            util.close();
        }
    }


    @Test(enabled = false)
    public void autoSuggestionStatic()
    {
        driver.get(ConfigReader.get("base.url.amazon"));
        AutoSuggestionPage suggestionPage=new AutoSuggestionPage(driver);
        suggestionPage.enterSearchText("iphone");
        suggestionPage.selectSuggestion("iphone 16e");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("iphone"));
    }


    @Test
    public void autoSuggestionNthTest()
    {
        driver.get(ConfigReader.get("base.url.amazon"));
        AutoSuggestionPage suggestionPage=new AutoSuggestionPage(driver);
        suggestionPage.enterSearchText("iphone");

        Assert.assertTrue(suggestionPage.isSuggestionListDisplayed(),"suggestion list not displayed");

        List<String> suggestionlist=suggestionPage.getSuggestionList();
        Assert.assertTrue(suggestionlist.size()>2,"Expected more than 2 suggestions");

        System.out.println("Suggestions:");
        for(String s:suggestionlist)
        {
            System.out.println(s+",");
            Assert.assertTrue(s.toLowerCase().contains("iphone"),"Irrelevant suggestion "+s);
        }

        String selected=suggestionPage.selectNthSuggestion(suggestionlist.size()-2);
        System.out.println("Clicked on the suggestion "+selected);

        Assert.assertTrue(driver.getTitle().toLowerCase().contains("iphone"),"Title doesn't reflect searched text "+driver.getTitle());

    }





    @DataProvider(name="getTestData")
    public Object[][] getsearchData() throws IOException {
        List<Map<String,String>> data= util.readFromExcel(filepath,sheet);
        Object[][] result=new Object[data.size()][1];
        for(int i=0;i<data.size();i++)
        {
            result[i][0]=data.get(i);
        }
        return result;
    }

    @Test(enabled = false, dataProvider="getTestData")
    public void autosuggestion(Map<String,String> row) throws IOException, InterruptedException {
        String testcaseId=row.get("TestCaseID");
        String searchterm=row.get("SearchTerm");
        String expectedSuggestion=row.get("ExpectedSuggestion");

        driver.get(ConfigReader.get("base.url.amazon"));
        Thread.sleep(2000);
        AutoSuggestionPage suggestionPage=new AutoSuggestionPage(driver);
        suggestionPage.enterSearchText(searchterm);
        Thread.sleep(2000);
        suggestionPage.selectSuggestion(expectedSuggestion);
        try
        {
            Assert.assertTrue(driver.getTitle().toLowerCase().contains(expectedSuggestion.toLowerCase()),"expected suggestion not in list");
            util.writeResultToExcel(testcaseId,"PASS");
        } catch (Exception e) {
            util.writeResultToExcel(testcaseId,"FAIL");
            throw new RuntimeException(e);
        }
    }

    //A sample data provider practice test
    @DataProvider(name="getCredentials")
    public Object[][] getCredentials()
    {
        List<List<String>> data=util.readCredentials(filepath,sheet);
        Object[][] result=new Object[data.size()][2];
        for(int i=0;i< data.size();i++)
        {
            result[i][0]=data.get(i).get(0);
            result[i][1]=data.get(i).get(1);
        }
        return result;

    }

    //A sample data provider practice test
    @Test(enabled = false, dataProvider ="getCredentials")
    public void enterCredentials(String username,String password)
    {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }


}
