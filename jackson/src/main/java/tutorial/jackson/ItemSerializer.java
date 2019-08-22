package tutorial.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 12 Oct 2017, 8:21 AM
 */
public class ItemSerializer extends StdSerializer<Item>
{
    public ItemSerializer()
    {
        this(null);
    }

    protected ItemSerializer(Class<Item> t)
    {
        super(t);
    }

    @Override
    public void serialize(Item value, JsonGenerator gen, SerializerProvider provider) throws IOException
    {
        gen.writeStartObject();
        gen.writeNumberField("id", value.id);
        gen.writeStringField("itemName", value.itemName);
        gen.writeNumberField("owner", value.owner.id);
        gen.writeEndObject();
    }


    public static void main(String[] args) throws JsonProcessingException
    {
        Item myItem = new Item(1, "theItem", new User(2, "theUser"));
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Item.class, new ItemSerializer());
        mapper.registerModule(module);

        String s = mapper.writeValueAsString(myItem);
        System.out.println(s);
    }
}
