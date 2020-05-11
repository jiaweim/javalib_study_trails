package tutorial.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 7:00 PM
 */
class JsonGeneratorTest
{
    @Test
    void write() throws IOException
    {
        StringWriter writer = new StringWriter();
        JsonFactory factory = new JsonFactory();
        JsonGenerator jsonGenerator = factory.createGenerator(writer);

        jsonGenerator.writeStartObject();
        // "age" : 21
        jsonGenerator.writeNumberField("age", 21);

        // "verified" : false
        jsonGenerator.writeBooleanField("verified", false);

        // "marks" : [100, 90, 85]
        jsonGenerator.writeFieldName("marks");

        // [
        jsonGenerator.writeStartArray();
        // 100, 90, 85
        jsonGenerator.writeNumber(100);
        jsonGenerator.writeNumber(90);
        jsonGenerator.writeNumber(85);
        // ]
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
        jsonGenerator.close();
        System.out.println(writer.toString());
    }
}
