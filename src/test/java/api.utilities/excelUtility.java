package api.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class excelUtility {

    public FileInputStream fis;
    String path;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public excelUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fis= new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(sheetName);
        int rowCount=sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowCount;
    }
    public int getCellCount(String sheetName,int rowNum) throws IOException {
        fis= new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        int cellCount=row.getLastCellNum();
        workbook.close();
        fis.close();
        return cellCount;
    }
    public String getCellValue(String sheetName,int rowNum,int cellNum) throws IOException {
        fis= new FileInputStream(path);
        workbook=new XSSFWorkbook(fis);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNum);
        cell=row.getCell(cellNum);
        DataFormatter formatter=new DataFormatter();
        String data;
        try{
            data=formatter.formatCellValue(cell);
        }
        catch(Exception e){
            data="";
        }
        workbook.close();
        fis.close();
        return data;
    }
}
