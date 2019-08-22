package tutorial.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.testng.Assert.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 18 Jun 2019, 10:06 PM
 */
public class ReadTest
{
    @Test
    public void testObjectMapper() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
        MyValue myValue = mapper.readValue(jsonString, MyValue.class);
        assertEquals(myValue.getName(), "Mahesh");
        assertEquals(myValue.getAge(), 21);

        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, myValue);
        assertEquals(writer.toString(), "{\"name\":\"Mahesh\",\"age\":21}");

        String s = mapper.writeValueAsString(myValue);
        System.out.println(s);
    }
}
