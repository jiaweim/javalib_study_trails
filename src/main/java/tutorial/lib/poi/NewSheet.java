package tutorial.lib.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 11 Apr 2018, 10:09 AM
 */
public class NewSheet
{
    public static void main(String[] args) throws IOException
    {
        Workbook wb = new HSSFWorkbook(); // or new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("second sheet");

        // sheet name 最多 31 个字符，并且不能包含如下字符：
        // 0x0000
        // 0x0003
        // colon (:)
        // backslash (\)
        // asterisk (*)
        // question mark (?)
        // forward slash (/)
        // opening square bracket ([)
        // closing square bracket (])

        try(OutputStream fileOut = new FileOutputStream("workbook.xls")){
            wb.write(fileOut);
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("a");

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Title");

        for (int i = 0; i < 10; i++) {
            XSSFRow row1 = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int k = 0; k < 3; k++) {
                row1.createCell(row1.getLastCellNum() + 1).setCellValue(k);
            }
        }

        FileOutputStream fos = new FileOutputStream("a.xlsx");
        workbook.write(fos);
        fos.close();
    }
}
