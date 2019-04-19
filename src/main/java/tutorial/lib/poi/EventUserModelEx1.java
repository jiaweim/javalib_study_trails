package tutorial.lib.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;

import java.io.IOException;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 26 Jan 2018, 12:22 PM
 */
public class EventUserModelEx1
{
    public void processOneSheet(String filename) throws OpenXML4JException, IOException
    {
        OPCPackage aPackage = OPCPackage.open(filename);
        XSSFReader reader = new XSSFReader(aPackage);

        SharedStringsTable table = reader.getSharedStringsTable();


    }


}
