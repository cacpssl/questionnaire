package com.ssl.questionnaire;

import com.ssl.questionnaire.bean.Question;
import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ParseXLSX {

    public static void main(String args[]) {
        InputStream is;
        XSSFWorkbook workbook;
        try {
            is = new FileInputStream("/home/ssl/test.xlsx");
            workbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("can't read file");
            return;
        }
        XSSFSheet sheet = workbook.getSheetAt(0);

        for(Iterator ite = sheet.rowIterator(); ite.hasNext();){
            XSSFRow row = (XSSFRow)ite.next();
            XSSFCell cell = (XSSFCell)row.cellIterator().next();
            String qNum;
            if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
                qNum = cell.getRichStringCellValue().toString();
                char c = qNum.charAt(0);
                if (  !((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) ) {
                    continue;
                }
            } else
            {
                continue;
            }
            ParseXLSX.parseXSSFRow(row);
            System.out.print(cell.getRichStringCellValue().toString() + Question.TYPE.RADIO);
        }
    }

    private static void parseXSSFRow(XSSFRow row) {
        String number = null;
        int type = 0;
        String question;
        Iterator iterator = row.cellIterator();
        XSSFCell cell;
        if ( iterator.hasNext() ) {
            cell = (XSSFCell)iterator.next();
            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                number = cell.getRichStringCellValue().toString();
            }
        }
        if ( iterator.hasNext() ) {
            cell = (XSSFCell)iterator.next();
            if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                if(!HSSFDateUtil.isCellDateFormatted(cell)) {
                    type = new Double(cell.getNumericCellValue()).intValue();
                }
            }
        }
        if ( iterator.hasNext() ) {
            cell = (XSSFCell)iterator.next();
            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                question = cell.getRichStringCellValue().toString();
            }
        }
        /*for( Iterator iterator = row.cellIterator(); iterator.hasNext(); ){
            XSSFCell cell = (XSSFCell)iterator.next();
            switch(cell.getCellType()) {
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    System.out.print(cell.getBooleanCellValue()+" ");
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        //读取日期格式
                        System.out.print(cell.getDateCellValue()+" ");
                    }else{
                        //读取数字
                        System.out.print(cell.getNumericCellValue()+" -d");
                    }
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    //读取公式
                    System.out.print(cell.getCellFormula()+" ");
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    //读取String
                    System.out.print(cell.getRichStringCellValue().toString()+" ");
                    break;
            }
        }*/
    }
}
