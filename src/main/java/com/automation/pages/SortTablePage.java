package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SortTablePage {
    WebDriver driver;
    WebDriverWait wait;

    public SortTablePage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void clickColumnHeader(String columnName) throws InterruptedException {
        WebElement header=driver.findElement(By.xpath("//div[h5[normalize-space()='Basic']]//th[contains(@aria-label,'"+columnName+"')]"));
        header.click();
        Thread.sleep(1000);

    }

    public List<Integer> getColumnValues(String columnName) throws InterruptedException {
        int columnIndex=getColumnIndex(columnName);
        if(columnIndex==-1)
        {
            throw new RuntimeException("column "+columnName+" not found");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[h5[normalize-space()='Basic']]//tbody/tr[1]/td[" + columnIndex + "]")
        ));

        List<WebElement> columnCells=driver.findElements(By.xpath("//div[h5[normalize-space()='Basic']]//tbody/tr//td["+columnIndex+"]"));
        List<Integer> values=new ArrayList<>();

        for(WebElement col:columnCells)
        {
            String text=(col.getText().trim().replaceAll("[^0-9]",""));
            if(!text.isEmpty())
            {
            values.add(Integer.parseInt(text));
            }
        }
        return values;

    }

    public int getColumnIndex(String columnName)
    {
      List<WebElement> headers=driver.findElements((By.xpath("//thead[@id='j_idt247:j_idt249_head']//th")));
      for(int i=0;i<headers.size();i++)
      {
          if(headers.get(i).getText().trim().equalsIgnoreCase(columnName))
          {
              return i+1;
          }
      }
      return -1;
    }
}
