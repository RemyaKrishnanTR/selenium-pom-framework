package com.automation.tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:/Drivers/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(10000);
    }

    public WebDriver getDriver() {
        return driver;
    }


    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

}
