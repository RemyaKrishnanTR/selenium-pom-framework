package com.automation.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public String createTempFile(String fileName,String content) throws IOException {
        String baseDir = System.getProperty("user.dir");
        String filePath = baseDir + File.separator + "temp" + File.separator + fileName;
        File file=new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter writer=new FileWriter(file);
        writer.write(content);
        writer.close();
        return filePath;
    }

    public void deleteFile()
    {
        String tempFolderPath=System.getProperty("user.dir")+File.separator+"temp";
        File tempFolder=new File(tempFolderPath);
        if(tempFolder.exists() && tempFolder.isDirectory())
        {
           for(File file:tempFolder.listFiles())
           {
               file.delete();
           }
        }
        tempFolder.delete();
    }
}

