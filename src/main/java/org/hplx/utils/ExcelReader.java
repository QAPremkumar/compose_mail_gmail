package org.hplx.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.hplx.config_manager.ConfigFactory.getConfig;

public class ExcelReader {
    static String path;
    static String sheet_name;
    public static FileInputStream excelFile;
    public static XSSFWorkbook excelWbook;
    public static XSSFSheet excelWSheet;
    public static XSSFCell cell;
    public static XSSFRow row;

    public static void setexcelFileInfo() throws Exception {
        // Open Excel File

    }
    //This function will read the data from the specified excel sheet and return data as a string of two dimensional array


    public Map<String, Map<String, String>> getExcelAsMap() throws Exception {

        try {
            String Excel_Path = System.getProperty("user.dir") + File.separator + "src/test/resources/TestData/TestData.xlsx";
            //String Excel_Path ="/Users/vinayrevankar/Desktop/ui_automation_bitbucket/playwright-ui-automation/src/test/resources/test_data/TestData.xlsx";
            excelFile = new FileInputStream(Excel_Path);
            // Open Excel Workbook
            excelWbook = new XSSFWorkbook(excelFile);
            // Access the required data sheet
            excelWSheet = excelWbook.getSheet(getConfig().environment());
            Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
            List<String> columnHeader = new ArrayList<String>();
            Row row = excelWSheet.getRow(0);

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                columnHeader.add(cellIterator.next().getStringCellValue());
            }
            Map<String, String> singleRowData = null;
            for (int i = 1; i <= getRowCount(); i++) {
                singleRowData = new HashMap<String, String>();
                Row row1 = excelWSheet.getRow(i);
                for (int j = 0; j < getColumnCount(1); j++) {
                    Cell cell = row1.getCell(j);
                    singleRowData.put(columnHeader.get(j), getCellValueAsString(cell));
                }
                completeSheetData.put(singleRowData.get("ACCOUNT_TYPE"), singleRowData);
            }
            return completeSheetData;

        } catch (Exception e) {
            return null;
        } finally {
            if (excelWbook != null)
                excelWbook.close();
            if (excelFile != null)
                excelFile.close();
        }

    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "BLANK";
            default:
                return "DEFAULT";
        }
    }

    // This function is to get the row count using POI's getLastRowNum function
    public static int getRowCount() throws Exception {
        try {
            int rowCount = excelWSheet.getLastRowNum();
            System.out.println("No of row :" + rowCount);
            return rowCount;
        } catch (Exception e) {
            return 0;
        }
    }

    // This function is to get the cell count using POI's getLastCellNum function
    public static int getColumnCount(int RowNum) throws Exception {
        try {
            // Row = ExcelWSheet.getRow(RowNum).getLastCellNum();
            row = excelWSheet.getRow(RowNum);
            int cellCount = row.getLastCellNum();
            return cellCount;
        } catch (Exception e) {
            return 0;
        }
    }

    public void readData(){

    }

    public static void main(String[] args) throws Exception {
        //https://thoughtcoders.com/blogs/store-excel-data-with-hashmap-in-selenium/

        ExcelReader excelReader = new ExcelReader();
        Map<String, Map<String, String>> excelData = excelReader.getExcelAsMap();
        System.out.println("excelData as Map: " + excelData);
        System.out.println(excelData.get("admin").get("DOC_EMAIL_ID"));
        System.out.println(excelData.get("admin").get("ACCOUNT_TYPE"));
        System.out.println(excelData.get("admin").get("PASSWORD"));
        System.out.println(excelData.get("admin").get("BRANCH_NAME"));

        System.out.println(excelData.get("non-admin").get("DOC_EMAIL_ID"));
        System.out.println(excelData.get("non-admin").get("ACCOUNT_TYPE"));
        System.out.println(excelData.get("non-admin").get("PASSWORD"));
        System.out.println(excelData.get("non-admin").get("BRANCH_NAME"));

    }
}
