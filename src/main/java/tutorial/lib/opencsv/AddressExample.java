package tutorial.lib.opencsv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * @author JiaweiM
 * @date Aug 14, 2015 2:06:07 PM
 */
public class AddressExample
{
    private static final String ADDRESS_FILE = "src/cn/ac/dicp/io/csv/addresses.csv";


    public static void main(String[] args) throws IOException
    {

        CSVReader reader = new CSVReader(new FileReader(ADDRESS_FILE));

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out.println(nextLine[0] + "\t" + nextLine[1] + "\t" + nextLine[2]);
        }

        reader.close();

        CSVReader reader2 = new CSVReader(new FileReader(ADDRESS_FILE));
        List<String[]> allElements = reader2.readAll();
        reader2.close();

        StringWriter sw = new StringWriter();
        CSVWriter writer = new CSVWriter(sw);
        writer.writeAll(allElements);

        System.out.println(sw.toString());

        writer.close();
    }
}
