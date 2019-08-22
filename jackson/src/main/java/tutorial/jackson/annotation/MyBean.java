package tutorial.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 19 Jun 2019, 9:21 AM
 */
public class MyBean
{
    public int id;
    public String name;

    public MyBean(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @JsonGetter("name")
    public String getTheName()
    {
        return name;
    }
}
