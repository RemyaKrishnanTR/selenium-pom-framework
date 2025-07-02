package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragAndDropPage {
    WebDriver driver;

    public DragAndDropPage(WebDriver driver)
    {
        this.driver=driver;
    }

    private By iframe=By.className("demo-frame");
    private By source=By.id("draggable");
    private By target=By.id("droppable");

    public void dragAndDrop()
    {
        driver.switchTo().frame(driver.findElement(iframe));
        WebElement dragElement=driver.findElement(source);
        WebElement dropElement=driver.findElement(target);
        Actions action=new Actions(driver);
        action.dragAndDrop(dragElement,dropElement).perform();
    }

    public void dragAndDropWithoutInbuilt()
    {
        driver.switchTo().frame(driver.findElement(iframe));
        WebElement dragElement=driver.findElement(source);
        WebElement dropElement=driver.findElement(target);
        Actions action=new Actions(driver);
        action.clickAndHold(dragElement).moveToElement(dropElement).release().build().perform();
    }

    public String getDropText()
    {
        String text=driver.findElement(target).getText();
        return text;
    }

}
