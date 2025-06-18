package com.automation.tests;

import com.automation.pages.SortTablePage;
import com.automation.utils.ConfigReader;
import com.automation.utils.SortUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SortTableTest extends BaseTest {
    @Test(enabled = false)
    public void ValidateSorting() throws InterruptedException {
        final Logger logger= LogManager.getLogger(AmazonTest.class);
        driver.get(ConfigReader.get("base.url.sortTables"));

        SortTablePage sortTable = new SortTablePage(driver);

        sortTable.clickColumnHeader("Quantity");
        logger.info("clicked on header Quantity");

        List<Integer> ls=sortTable.getColumnValues("Quantity");
        boolean isSortedAsc=SortUtil.sortedAscending(ls);
        boolean isSortedDesc=SortUtil.sortedDescending(ls);
        Assert.assertTrue(isSortedDesc||isSortedAsc,"Not sorted"); // becomes true if isSortedAsc or isSortedDesc is true;
        logger.info("sorting validation passed");
    }

}
