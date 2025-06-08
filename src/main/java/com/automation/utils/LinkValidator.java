package com.automation.utils;

import com.automation.pages.SearchResultsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LinkValidator {
    public static void validateAllLinks(WebDriver driver)
    {
        final Logger logger= LogManager.getLogger(SearchResultsPage.class);
        List<WebElement> links=driver.findElements(By.tagName("a"));
        for(WebElement link:links)
        {
            String url=link.getAttribute("href");
            if(url==null||url.isEmpty()|| !(url.startsWith("https://")||url.startsWith("http://"))) //If the URL is either null, empty, or does not start with http:// or https://, then skip it."
            {
                continue;
            }
            try
            {
                HttpURLConnection connection=(HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();
                int responseCode=connection.getResponseCode();
                if(responseCode>=400)
                {
                    logger.info("Broken link found "+url+"-> "+responseCode);
                }
                else {
                    logger.info("valid link found "+url+"-> "+responseCode);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
