package tutorial.jackson.aalto;

import com.fasterxml.aalto.AsyncXMLInputFactory;
import org.codehaus.stax2.XMLInputFactory2;

import javax.xml.stream.XMLInputFactory;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 15 Oct 2019, 10:25 AM
 */
public class Test {
    public static void main(String[] args) {
        XMLInputFactory xmlInputFactory =
                AsyncXMLInputFactory.newFactory();
    }
}
