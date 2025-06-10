package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FileUploadDownloadPage {
    WebDriver driver;
    @FindBy(id="file-upload")
    private WebElement fileInput;

    @FindBy(id="file-submit")
    private WebElement uploadButton;

    @FindBy(tagName = "h3")
    private WebElement uploadSuccessMsg;

    public FileUploadDownloadPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void fileUpload(String filePath)
    {
        fileInput.sendKeys(filePath);
        uploadButton.click();
    }
    public String getFileUploadConfirmation()
    {
        return uploadSuccessMsg.getText();
    }
}
