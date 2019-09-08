package tutorial.lib.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 15 May 2018, 2:25 PM
 */
public class FilevsInputStream {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        // Use a file
        Workbook wb = WorkbookFactory.create(new File("MyExcel.xls"));

        // Use an InputStream, needs more memory
        Workbook wb2 = WorkbookFactory.create(new FileInputStream("MyExcel.xlsx"));
    }
}
