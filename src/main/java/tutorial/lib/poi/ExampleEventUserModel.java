package tutorial.lib.poi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 01 Oct 2018, 7:31 PM
 */
public class ExampleEventUserModel
{
    public void processOneSheet(String filename) throws InvalidFormatException
    {
        OPCPackage pkg = OPCPackage.open(filename);

    }
}
