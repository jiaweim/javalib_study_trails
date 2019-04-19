package tutorial.lib.poi;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.testng.Assert;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 17 May 2018, 11:48 AM
 */
public class SXSSFTest
{
    public static void main(String[] args) throws IOException
    {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        SXSSFSheet sh = wb.createSheet();
        for (int rownum = 0; rownum < 1000; rownum++) {
            SXSSFRow row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                SXSSFCell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }
        }

        // Rows with rownum < 900 are flushed and not accessible
        for (int rownum = 0; rownum < 900; rownum++) {
            Assert.assertNull(sh.getRow(rownum));
        }

        // ther last 100 rows are still in memory
        for (int rownum = 900; rownum < 1000; rownum++) {
            Assert.assertNotNull(sh.getRow(rownum));
        }

        FileOutputStream out = new FileOutputStream("sxssf.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }
}
