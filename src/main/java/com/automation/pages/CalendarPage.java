package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CalendarPage {
    WebDriver driver;
    WebDriverWait wait;

    private By dateInput=By.id("datePickerMonthYearInput");
    private By monthDropdown = By.cssSelector("select.react-datepicker__month-select");
    private By yearDropdown = By.cssSelector("select.react-datepicker__year-select");
    private By allDates = By.cssSelector(".react-datepicker__day");

    public CalendarPage(WebDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectDate(String month,String day,String year)
    {
        driver.findElement(dateInput).click();

        Select select=new Select(driver.findElement(monthDropdown));
        select.selectByVisibleText(month);

        Select selectyear =new Select(driver.findElement(yearDropdown));
        selectyear.selectByVisibleText(year);

        List<WebElement> days=driver.findElements(allDates);
        for(WebElement d:days)
        {
            String daytoSelect=d.getText();
            if(daytoSelect.equalsIgnoreCase(day))
            {
                d.click();
                break;
            }

        }
    }


}
