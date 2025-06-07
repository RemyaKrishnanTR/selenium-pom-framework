package com.automation.utils;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name="searchItems")
    public Object[][] getSearchItems()
    {
        return new Object[][]{{"Noise Buds X Prime", "Silver Grey"}};
    }

}
