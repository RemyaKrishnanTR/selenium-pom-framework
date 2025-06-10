package com.automation.utils;

import java.nio.file.Paths;

public class FilePathUtils {

    public static String getAbsolutePath(String relativePath)
    {
        return Paths.get(System.getProperty("user.dir"),relativePath).toAbsolutePath().toString();
    }

    public static String getTestDataFile(String fileName)
    {
        return getAbsolutePath("src/test/resources/"+fileName);
    }
}
