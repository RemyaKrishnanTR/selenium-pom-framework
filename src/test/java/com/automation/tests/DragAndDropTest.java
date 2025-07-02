package com.automation.tests;

import com.automation.pages.DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseTest {

    @Test(enabled = false)
    public void testDragAndDropAction()
    {
        driver.get("https://jqueryui.com/droppable/");
        DragAndDropPage dragAndDropPage=new DragAndDropPage(driver);
        dragAndDropPage.dragAndDrop();
        String text=dragAndDropPage.getDropText();
        Assert.assertEquals(text,"Dropped!", "Drag and drop failed!");

    }

    @Test(enabled = false)
    public void testDragAndDropActionWithoutInbuiltFunction()
    {
        driver.get("https://jqueryui.com/droppable/");
        DragAndDropPage dragAndDropPage=new DragAndDropPage(driver);
        dragAndDropPage.dragAndDropWithoutInbuilt();
        String text=dragAndDropPage.getDropText();
        Assert.assertEquals(text,"Dropped!", "Drag and drop failed!");

    }
}
