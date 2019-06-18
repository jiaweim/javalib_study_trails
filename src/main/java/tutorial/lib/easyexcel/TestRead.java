package tutorial.lib.easyexcel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 24 May 2019, 8:39 AM
 */
public class TestRead
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String file = "Z:\\MaoJiawei\\test\\easyexcel\\raw_0.01.xlsx";

        InputStream inputStream = new FileInputStream(file);
        ExcelListener excelListener = new ExcelListener();

    }
}
