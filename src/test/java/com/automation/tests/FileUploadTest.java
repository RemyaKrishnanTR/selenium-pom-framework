package com.automation.tests;

import com.automation.pages.FileUploadDownloadPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.FilePathUtils;
import com.automation.utils.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FileUploadTest extends BaseTest{
    FileUploadDownloadPage upload;

    @Test(enabled=false)
    public void fileUpload()
    {
        driver.get(ConfigReader.get("base.url.upload"));
        upload=new FileUploadDownloadPage(driver);
        String filePath= FilePathUtils.getTestDataFile("testData/samplefile.txt");
        upload.fileUpload(filePath);
        String confirmationText= upload.getFileUploadConfirmation();
        Assert.assertEquals(confirmationText,"File Uploaded!");
    }

    @Test(enabled = false)
    public void dynamicFileUpload() throws IOException {
        driver.get(ConfigReader.get("base.url.upload"));
        upload=new FileUploadDownloadPage(driver);
        FileUtils fileUtil=new FileUtils();

        String createfilepath = fileUtil.createTempFile("sample.txt","This is a sample file to upload");
        upload.fileUpload(createfilepath);

        String confirmationText= upload.getFileUploadConfirmation();
        Assert.assertEquals(confirmationText,"File Uploaded!");

        String uploadedFileName= upload.getUploadedFileName();
        Assert.assertEquals(uploadedFileName,"sample.txt");

        fileUtil.deleteFile();
    }
}
