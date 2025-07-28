package com.automation.tests;

import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod   //use beforeMethod instead of beforeClass, this makes sure each test starts fresh, which is often safer in test automation
    public void setupTest()
    {
        loginPage=new LoginPage(driver);
        driver.get("https://practicetestautomation.com/practice-test-login");
    }


    @Test(enabled = false)
    public void loginWithValidCredentials()
    {

        loginPage.login("student", "Password123");
        String msg=loginPage.getSuccessMessage().trim();
        Assert.assertEquals(msg,"Logged In Successfully","Login failed with given credentials");

    }

    @Test(enabled = false)
    public void loginWithInValidCredentials()
    {
        loginPage.login("studentben", "Password123jdh");
        String errorMsg=loginPage.getErrorMessage().trim();
        Assert.assertEquals(errorMsg,"Your username is invalid!");

    }
}
