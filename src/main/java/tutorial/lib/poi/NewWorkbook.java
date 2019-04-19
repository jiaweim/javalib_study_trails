/**********************************************************
 * File:NewWorkbook.java
 *
 * Author:	Jiawei Mao
 *
 * Created on Jan 28, 2016
 *
 * Copyright (c) Dalian Institute of Chemical Physics
 *	Chinese Academy of Sciences
 *
 * Contact: jiawei@dicp.ac.cn
 *
 *******************************************************/
package tutorial.lib.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NewWorkbook
{

    public static void main(String[] args) throws IOException
    {
        Workbook wb = new HSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
            wb.write(fileOut);
        }

        Workbook wb2 = new XSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb2.write(fileOut);
        }
    }
}
