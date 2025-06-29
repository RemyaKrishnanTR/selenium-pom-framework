package com.automation.tests;

import com.automation.pages.DataTablePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PaginationSearchTest extends BaseTest {
    private static final Logger logger= LogManager.getLogger(PaginationSearchTest.class);

    @Test(enabled = false)
    public void verifyRecordExistsInPagination()
    {
        driver.get("https://datatables.net/examples/data_sources/ajax.html");
        DataTablePage dataTablePage=new DataTablePage(driver);
        boolean recordFound= false;
        try {
            recordFound = dataTablePage.searchAcrossPage("Fiona Green");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(recordFound)
        {
           logger.info("Found name Fiona in paginated table");
        }
        else {
            logger.error(" Did not find name Fiona in paginated table");

        }
        Assert.assertTrue(recordFound,"Record not found across all pages");

    }
}
