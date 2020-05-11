package tutorial.lib.poi;

import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 11 Nov 2019, 10:34 AM
 */
class UpdateExcelExt
{
    private class Book
    {
        private String title;
        private double price;

        public Book(String title, double price)
        {
            this.title = title;
            this.price = price;
        }

        public String getTitle()
        {
            return title;
        }

        public double getPrice()
        {
            return price;
        }
    }

    @Test
    void test1() throws IOException
    {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Passionate Programmer", 16));
        books.add(new Book("Software Craftmanship", 26));
        books.add(new Book("The Art of Agile Development", 32));
        books.add(new Book("Continuous Delivery", 41));

        InputStream testStream = getClass().getClassLoader().getResourceAsStream("test.xlsx");
        Workbook sheets = WorkbookFactory.create(testStream);
        Sheet sheet = sheets.getSheetAt(0);

        int rowNr = sheet.getLastRowNum();
        for (Book book : books) {
            Row row = sheet.createRow(rowNr++);
            int colNr = 0;
            Cell cell = row.createCell(colNr);
            cell.setCellValue(rowNr);

            row.createCell(colNr++).setCellValue(book.getTitle());
            row.createCell(colNr).setCellValue(book.getPrice());
        }
        testStream.close();


    }
}
