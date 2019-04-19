package tutorial.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 12 Oct 2017, 8:15 AM
 */
public class Item
{
    public int id;
    public String itemName;
    public User owner;

    public Item(int id, String itemName, User owner)
    {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }

    public static void main(String[] args) throws JsonProcessingException
    {
        Item myItem = new Item(1, "theItem", new User(2, "theUser"));
        String serialized = new ObjectMapper().writeValueAsString(myItem);
        System.out.println(serialized);
    }
}
