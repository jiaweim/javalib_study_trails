package tutorial.jackson;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 7:14 PM
 */
public class Car
{
    private String color;
    private String type;

    public Car(String color, String type)
    {
        this.color = color;
        this.type = type;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
