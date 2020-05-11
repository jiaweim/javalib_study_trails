package trails.gson;

import com.google.gson.stream.JsonWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 06 Nov 2019, 4:34 PM
 */
class JsonWriterTest
{
    @Test
    void test() throws IOException
    {
        StringWriter strings = new StringWriter();
        JsonWriter writer = new JsonWriter(strings);
        writer.setIndent("  ");
        writer.beginObject();
        for (int i = 0; i < 20; i++) {
            writer.name("a");
            writer.beginObject();
        }
        for(int i = 0; i< 20; i++){
            writer.endObject();
        }
        writer.endObject();
        System.out.println(strings.toString());

    }
}
