package trails.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 07 Nov 2019, 1:26 PM
 */
public class JsonReaderTest
{
    @Test
    public void test() throws IOException
    {
        String json = "{'id': 1001,'firstName': 'Lokesh','lastName': 'Gupta','email': null}";

        JsonReader jsonReader = new JsonReader(new StringReader(json));
        jsonReader.setLenient(true);

        try {
            while (jsonReader.hasNext()) {
                JsonToken nextToken = jsonReader.peek();

                if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {

                    jsonReader.beginObject();

                } else if (JsonToken.NAME.equals(nextToken)) {

                    String name = jsonReader.nextName();
                    System.out.println("Token KEY >>>> " + name);

                } else if (JsonToken.STRING.equals(nextToken)) {

                    String value = jsonReader.nextString();
                    System.out.println("Token Value >>>> " + value);

                } else if (JsonToken.NUMBER.equals(nextToken)) {

                    long value = jsonReader.nextLong();
                    System.out.println("Token Value >>>> " + value);

                } else if (JsonToken.NULL.equals(nextToken)) {

                    jsonReader.nextNull();
                    System.out.println("Token Value >>>> null");

                } else if (JsonToken.END_OBJECT.equals(nextToken)) {

                    jsonReader.endObject();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            jsonReader.close();
        }
    }
}
