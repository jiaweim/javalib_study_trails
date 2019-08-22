package tutorial.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 19 Jun 2019, 10:16 AM
 */
@JsonPropertyOrder({"name", "id"})
public class MyBean2
{
    public int id;
    public String name;

    public MyBean2(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
