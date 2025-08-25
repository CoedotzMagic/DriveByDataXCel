package com.coedotzmagic.drivebydataxcel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * write by Coedotz
 * 19-02-2024
 */

public class DriveByDataXCel {

    /**
     * <b>readTestDataFromExcel</b>
     * used to read data and contents from Excel files
     *
     * @param filePath file path directory target excel
     * @param sheetName sheet name in excel
     * @return returns a value in the form of a list containing each data from the selected Excel file column.
     *
     * @since 1.1 from QATools
     */
    public static List<Map<String, String>> readTestDataFromExcel(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            List<Map<String, String>> data = new ArrayList<>();

            // Membaca header dari row pertama
            Row headerRow = sheet.getRow(0);
            String[] headers = new String[colCount];
            for (int j = 0; j < colCount; j++) {
                headers[j] = headerRow.getCell(j).toString().trim();
            }

            // Membaca data dari baris berikutnya
            for (int i = 1; i <= rowCount; i++) {
                Row dataRow = sheet.getRow(i);
                Map<String, String> rowMap = new HashMap<>();

                for (int j = 0; j < colCount; j++) {
                    String key = headers[j];
                    Cell cell = dataRow.getCell(j);
                    String value = (cell != null) ? cell.toString().trim() : "";
                    rowMap.put(key, value);
                }

                data.add(rowMap);
            }

            workbook.close();
            fis.close();

            return data;
        } catch (Exception e) {
            System.out.println("[Error] CoedotzMagic - DriveByDataXCel: " + e.getMessage());
            return null;
        }
    }

}