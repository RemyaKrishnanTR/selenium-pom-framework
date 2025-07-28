package com.automation.pages;

import com.automation.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    private WaitUtils waitUtils;


    @FindBy(id="username")
    private WebElement username;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="submit")
    private WebElement loginButton;

    @FindBy(id="error")
    private WebElement errorMessage;

    @FindBy(xpath = "//h1[@class='post-title']")
    private WebElement successMessage;



    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        waitUtils=new WaitUtils(driver,10);
        PageFactory.initElements(driver,this);
    }

    public void enterUsername(String user)
    {
        waitUtils.waitForElementToBeVisible(username);
        username.sendKeys(user);
    }

    public void enterPassword(String pwd)
    {
        password.sendKeys(pwd);
    }

    public void clickLogin()
    {
        loginButton.click();
    }

    public String getErrorMessage()
    {
        return errorMessage.getText();
    }

    public String getSuccessMessage()
    {
        return successMessage.getText();
    }

    public void login(String username,String password)
    {
        enterUsername(username);
        enterPassword(password);
        clickLogin();

    }


}
