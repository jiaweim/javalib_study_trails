package tutorial.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 7:10 PM
 */
class ObjectMapperTest
{
    @Test
    void testRead() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        MyValue value = mapper.readValue(new File("data.json"), MyValue.class);
        // or:
        value = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);
        // or:
        value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
    }
}
