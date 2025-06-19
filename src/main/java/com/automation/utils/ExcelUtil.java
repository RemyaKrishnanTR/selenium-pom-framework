package com.automation.utils;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    private String filepath;
    private String filename;
    private Workbook workbook;
    private Sheet sheet;
    private FileInputStream fis;

    public ExcelUtil(String filepath,String sheetName) throws IOException {
        this.filepath=filepath;
        this.filename=filename;
        fis=new FileInputStream(filepath);
        workbook=new XSSFWorkbook(fis);
        sheet= workbook.getSheet(sheetName);

    }

    public void writeDataToExcel(String filepath, String sheetName, List<String> headers, List<List<String>> rows) throws IOException {
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

    public boolean validateExcel(String filepath, List<String> expectedHeaders, int expectedCount, List<Integer> criticalCols) throws IOException
    {
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

    public List<Map<String,String>> readFromExcel(String filePath,String sheetName) throws IOException {

        List<Map<String,String>> data = new ArrayList<>();
        Row headerRow=sheet.getRow(0);

        for(int i=1;i<=sheet.getLastRowNum();i++)
        {
            Row row= sheet.getRow(i);
            if(row==null || isRowEmpty(row))continue;
            Map<String,String> rowData=new HashMap<>();
            for(int j=0;j<row.getLastCellNum();j++)
            {
                String key=headerRow.getCell(j).getStringCellValue().trim();
                String value=getCellValue(row.getCell(j));
                rowData.put(key,value);
            }
            data.add(rowData);
        }
        return data;
    }

    public boolean isRowEmpty(Row row)
    {
        for(int c=0;c<row.getLastCellNum();c++)
        {
            Cell cell= row.getCell(c);
            if(cell!=null && cell.getCellType()!= CellType.BLANK && !getCellValue(cell).isEmpty())
            {
                return false;
            }
        }
        return true;
    }


    public List<List<String>> readCredentials(String filepath,String sheetname)
    {
        List<List<String>> credentialsList= new ArrayList<>();
        for(int i=1;i<=sheet.getLastRowNum();i++)
        {
            Row row=sheet.getRow(i);
            if(row==null)continue;
            List<String> credentials=new ArrayList<>();
            for(int j=0;j<row.getLastCellNum();j++)
            {
                String cred=getCellValue(row.getCell(j));
                credentials.add(cred);
            }
            credentialsList.add(credentials);
        }
        return credentialsList;
    }

    public String getCellValue(Cell cell)
    {
        if(cell==null)
        {
            return "";
        }
        return switch(cell.getCellType())
        {
            case STRING->cell.getStringCellValue();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            default -> "";
        };
    }

    public void writeResultToExcel(String testId, String result) throws IOException {
        for(int i=1;i<=sheet.getLastRowNum();i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;
            if (row.getCell(0)!=null && row.getCell(0).getStringCellValue().equalsIgnoreCase(testId)) {
                Cell resultCell = row.createCell(4);
                resultCell.setCellValue(result);

                Cell timeStampCell = row.createCell(5);
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                timeStampCell.setCellValue(time);
                break;
            }
        }
        FileOutputStream fos=new FileOutputStream(filepath);
        workbook.write(fos);
        fos.close();
    }




    public void close() throws IOException {
        workbook.close();
    }
}
