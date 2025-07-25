package com.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class WebTablePage {
    private static final Logger logger = LogManager.getLogger(WebTablePage.class);
    private WebDriver driver;

    @FindBy(className = "dataTable")
    private WebElement tableLocator;


    public WebTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllRows() {

        return tableLocator.findElements(By.tagName("tr"));
    }

    public List<WebElement> getColumnsInRow(WebElement row) {

        return row.findElements(By.tagName("td"));
    }

    public List<String> getHeaders() {
        return driver.findElements(By.xpath("//table[@class='dataTable']//thead//th")).stream().map(WebElement::getText).map(String::trim).collect(Collectors.toList());
    }

    public List<Map<String, String>> getWebTableData() {
        List<Map<String, String>> tableData = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr-group']"));

        for (WebElement row : rows) {
            Map<String, String> rowData = new HashMap<>();
            List<String> headers = Arrays.asList("First Name", "Last Name", "Age", "Email","Salary","Department");
            List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));
            for (int i = 0; i < headers.size(); i++) { //for not including Actions column of the webtable
                String key = headers.get(i).trim();
                String value = cells.get(i).getText().trim();
                rowData.put(key, value);
            }


        }
        return tableData;
    }
}