package com.automation.utils;

import org.openqa.selenium.WebDriver;

public class WindowHandler {
    public static void switchToNewWindow(WebDriver driver)
    {
        String currentWindow=driver.getWindowHandle();
        for(String handle: driver.getWindowHandles())
        {
            if(!handle.equals(currentWindow))
            {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

}

