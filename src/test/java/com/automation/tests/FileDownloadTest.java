package com.automation.tests;

import com.automation.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileDownloadTest{
    private WebDriver driver;
    private String downloadPath;

    /*To tell Chrome to:
Download files without asking where to save them ->download.prompt_for_download = false
Always save to the folder specified->download.default_directory
Block popups that interfere with downloads -> profile.default_content_settings.popus
Use hashmap to set chrome preferences for download and pass it in setExperimentalOption()*/

    @BeforeMethod
    public void setup()
    {
        downloadPath=System.getProperty("user.dir")+ File.separator+"downloads";
        File folder = new File(downloadPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        Map<String,Object> preference=new HashMap<>();
        preference.put("download.default_directory",downloadPath);
        preference.put("download.prompt_for_download",false);
        preference.put("profile.default_content_settings.popus",0);

        ChromeOptions options=new ChromeOptions();
        options.setExperimentalOption("prefs",preference);
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(enabled = false)
    public void verifyDownload() throws InterruptedException {
        driver.get(ConfigReader.get("base.url.download"));
        driver.findElement(By.linkText("Test.txt")).click();

        Thread.sleep(3000);

        File downloadedFile=new File(downloadPath+File.separator+"Test.txt");
        Assert.assertTrue(downloadedFile.exists(),"file not downloaed in the specified path");
    }

    @AfterMethod
    public void teardown()
    {
        if(driver!=null)
        {
            driver.quit();
        }
    }
}
