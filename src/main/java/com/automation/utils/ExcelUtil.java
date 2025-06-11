package com.automation.utils;
import java.io.*;
import java.util.List;

import com.google.common.collect.Table;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtil {
    public static void writeDataToExcel(String filepath,String sheetName,List<String> headers,List<List<String>> rows) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
        }

        for (int i = 0; i < rows.size(); i++) {
            Row row = sheet.createRow(i + 1);
            List<String> rowdata = rows.get(i);
            for (int j = 0; j < rowdata.size(); j++) {
                row.createCell(j).setCellValue(rowdata.get(j));
            }

        }

        try
        {
            FileOutputStream fos = new FileOutputStream(filepath);
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("Error writing to excel" + e.getMessage());
        }
    }

    public static boolean validateExcel(String filepath,List<String> expectedHeaders, int expectedCount,List<Integer> criticalCols) throws IOException {
      FileInputStream fis=new FileInputStream(filepath);
      Workbook workbook=new XSSFWorkbook(fis);
      Sheet sheet=workbook.getSheetAt(0);

        // Validate headers
      Row headerRow= sheet.getRow(0);
      for(int i=0;i<expectedHeaders.size();i++)
      {
          if(!headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(expectedHeaders.get(i)))
          {
              return false;
          }
      }

      // Validate row count
      int actualsize=sheet.getLastRowNum();
      if(actualsize!=expectedCount)
      {
          return false;
      }

        // Check for empty cells in critical columns
      for(int i=2;i<actualsize;i++)
      {
          Row row= sheet.getRow(i);
          for(int col:criticalCols)
          {
            if(row==null||row.getCell(col)==null||row.getCell(col).toString().isEmpty())
            {
                return false;
            }
          }
      }

        return true;
    }
}
