package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class dataProviders {

    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException {

        String path=System.getProperty("user.dir")+ File.separator+"testData"+File.separator+"Book1.xlsx";
        excelUtility excel= new excelUtility(path);
        int rowNum= excel.getRowCount("Sheet1");
        int cellNum= excel.getCellCount("Sheet1", 1);
        String[][] apidata= new String[rowNum][cellNum];
        for(int i=1;i<=rowNum;i++){
            for(int j=0;j<cellNum;j++){
                apidata[i-1][j]= excel.getCellValue("Sheet1", i, j);
            }
        }
        return apidata;
    }

    @DataProvider(name="userNames")
    public String[] getUserName() throws IOException {

        String path=System.getProperty("user.dir")+File.separator+"testData"+File.separator+"Book1.xlsx";
        excelUtility excel= new excelUtility(path);
        int rowNum= excel.getRowCount("Sheet1");
        String[] apidata = new String[rowNum];
        for(int i=1;i<=rowNum;i++){
            apidata[i-1]= excel.getCellValue("Sheet1", i, 1);
        }
        return apidata;
    }
}
