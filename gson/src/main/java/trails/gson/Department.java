package trails.gson;

import java.util.Arrays;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 07 Nov 2019, 9:35 AM
 */
public class Department
{
    private long id;
    private String name;
    private User[] users;

    //Getters and Setters


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

    public User[] getUsers()
    {
        return users;
    }

    public void setUsers(User[] users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return "Department [id=" + id + ", name=" + name + ", users=" + Arrays.toString(users) + "]";
    }
}
