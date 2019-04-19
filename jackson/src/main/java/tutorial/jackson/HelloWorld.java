package tutorial.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 09 Oct 2018, 3:07 PM
 */
public class HelloWorld
{
    @Test
    public void simpleDom() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        MyValue myValue = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
        assertEquals(myValue.name, "Bob");
        assertEquals(myValue.age, 13);

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, myValue);
        assertEquals(writer.toString(), "{\"name\":\"Bob\",\"age\":13}"); // {"name":"Bob","age":13}
    }
}
