package com.automation.tests;

import com.automation.pages.CalendarPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalendarTest extends BaseTest {
    Logger logger= LogManager.getLogger(CalendarTest.class);
    CalendarPage calendarPage;

    @Test(enabled = false)
    public void verifyDatePicker()
    {
        driver.get("https://demoqa.com/date-picker");
        calendarPage=new CalendarPage(driver);
        calendarPage.selectDate("August","6","2020");
        String selectedDate=driver.findElement(By.id("datePickerMonthYearInput")).getAttribute("value");
        Assert.assertEquals(selectedDate,"08/06/2020");
    }
}
