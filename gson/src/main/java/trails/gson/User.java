package trails.gson;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 07 Nov 2019, 8:55 AM
 */
public class User
{
    private long id;
    private String name;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", name=" + name + "]";
    }
}
