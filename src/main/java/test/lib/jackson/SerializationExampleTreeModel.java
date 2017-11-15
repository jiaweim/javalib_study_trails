package test.lib.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 11 Oct 2017, 4:02 PM
 */
public class SerializationExampleTreeModel
{

    public static void main(String[] args) throws IOException
    {
        JsonNodeFactory factory = new JsonNodeFactory(false);

        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator generator = jsonFactory.createGenerator(System.out);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode album = factory.objectNode();
        album.put("Album-Title", "Kind Of Blue");


        ArrayNode links = factory.arrayNode();
        links.add("link1").add("link2");
        album.set("links", links);


        ObjectNode artist = factory.objectNode();
        artist.put("Artist-Name", "Miles Davis");
        artist.put("birthDate", "26 May 1926");
        album.set("artist", artist);

        ObjectNode musicians = factory.objectNode();
        musicians.put("Julian Adderley", "Alto Saxophone");
        musicians.put("Miles Davis", "Trumpet, Band leader");
        album.set("musicians", musicians);

        mapper.writeTree(generator, album);
    }

}
