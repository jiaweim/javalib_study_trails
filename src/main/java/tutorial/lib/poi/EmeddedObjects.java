/**
 * 
 */
package tutorial.lib.poi;

import org.apache.poi.hssf.usermodel.HSSFObjectData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 */
public class EmeddedObjects {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(args[0]));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);

		for (HSSFObjectData obj : workbook.getAllEmbeddedObjects()) {
			String oldName = obj.getOLE2ClassName();
			if (oldName.equals("Worksheet")) {
				DirectoryNode dn = (DirectoryNode) obj.getDirectory();
				HSSFWorkbook embeddedWorkbook = new HSSFWorkbook(dn, fs, false);
				System.out.println();
			}
		}
	}

}
