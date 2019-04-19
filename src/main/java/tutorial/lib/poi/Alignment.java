package tutorial.lib.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileOutputStream;
import java.io.IOException;


public class Alignment
{

    public static void main(String[] args) throws IOException
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");
        HSSFRow row = sheet.createRow(1);
        createCell(wb, row, 0, HorizontalAlignment.CENTER);
        createCell(wb, row, 1, HorizontalAlignment.CENTER_SELECTION);
        createCell(wb, row, 2, HorizontalAlignment.FILL);
        createCell(wb, row, 3, HorizontalAlignment.GENERAL);
        createCell(wb, row, 4, HorizontalAlignment.JUSTIFY);
        createCell(wb, row, 5, HorizontalAlignment.LEFT);
        createCell(wb, row, 6, HorizontalAlignment.RIGHT);

        FileOutputStream fout = new FileOutputStream("E:\\Data\\right.xls");
        wb.write(fout);
        fout.close();
    }

    private static void createCell(HSSFWorkbook wb, HSSFRow row, int column, HorizontalAlignment align)
    {
        HSSFCell cell = row.createCell(column);
        cell.setCellValue("Align It");
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(align);
        cell.setCellStyle(style);
    }

}
