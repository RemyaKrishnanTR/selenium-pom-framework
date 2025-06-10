package com.automation.tests;

import com.automation.pages.FileUploadDownloadPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.FilePathUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest{

    @Test(enabled=false)
    public void fileUpload()
    {
        driver.get(ConfigReader.get("base.url.upload"));
        FileUploadDownloadPage upload=new FileUploadDownloadPage(driver);

        String filePath= FilePathUtils.getTestDataFile("testData/samplefile.txt");
        upload.fileUpload(filePath);

        String confirmationText= upload.getFileUploadConfirmation();
        Assert.assertEquals(confirmationText,"File Uploaded!");
    }

}
