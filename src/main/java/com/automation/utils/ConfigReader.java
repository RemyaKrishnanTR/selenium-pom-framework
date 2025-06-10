package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static
    {
        try
        {
            FileInputStream fis=new FileInputStream(FilePathUtils.getTestDataFile("/config/env.properties"));
            properties=new Properties();
            properties.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found"+e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load env.properties"+e);
        }
    }

    public static String get(String key)
    {
        return properties.getProperty(key);
    }

}
