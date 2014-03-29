package com.app.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HssExcel {

    private static XSSFWorkbook hssBook;
    
    
    private HssExcel(InputStream is) throws IOException{
        hssBook = new XSSFWorkbook(is);
    }
    
    /**
     * load excel file stream
     * @param is
     * @throws IOException
     */
    public static HssExcel load(InputStream is) throws IOException{
        
        return new HssExcel(is);
    }
    
    /**
     * load excel file stream by file name
     * @param fileName
     * @throws IOException
     */
    public static HssExcel load(String fileName) throws IOException{
        InputStream is = new FileInputStream(fileName);
        return new HssExcel(is);
    }
    
    /**
     * get book sheet by sheet number
     * @param num
     * @return
     */
    public XSSFSheet getSheet(Integer num){
        XSSFSheet hssfSheet = hssBook.getSheetAt(num);
        return hssfSheet;
    }
    
}
