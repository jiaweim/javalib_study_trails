/**********************************************************
 * File:AddressReader.java
 *
 * Author:	Jiawei Mao
 *
 * Created on Jan 11, 2016
 *
 * Copyright (c) Dalian Institute of Chemical Physics
 *	Chinese Academy of Sciences
 *
 * Contact: jiawei@dicp.ac.cn
 *
 *******************************************************/
package tutorial.lib.opencsv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


public class AddressReader
{

    public static void main(String[] args) throws URISyntaxException, IOException
    {
        URI path = AddressReader.class.getResource("addresses.csv").toURI();
        CSVReader reader = new CSVReader(new FileReader(new File(path)));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            System.out
                    .println("NAME:[" + nextLine[0] + "]\nAddress:[" + nextLine[1] + "]\nEmail:[" + nextLine[2] + "]");
        }

        reader.close();

        // try reading it back out as csv to the console
        CSVReader reader2 = new CSVReader(new FileReader(new File(path)));
        List<String[]> allElements = reader2.readAll();
        StringWriter sw = new StringWriter();
        CSVWriter writer = new CSVWriter(sw);
        writer.writeAll(allElements);

        System.out.println("\n\nGenerated CSV File:\n\n");
        System.out.println(sw.toString());
    }
}
