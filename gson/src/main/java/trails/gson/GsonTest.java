package trails.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 9:18 PM
 */
class GsonTest
{
    @Test
    void testSerializationPrimitives()
    {
        Gson gson = new Gson();
        assertEquals(gson.toJson(1), "1");
        assertEquals(gson.toJson("abcd"), "\"abcd\"");
        assertEquals(gson.toJson(new Long(10)), "10");
        assertEquals(gson.toJson(new int[]{1}), "[1]");
    }

    @Test
    void testDeserializationPrimitives()
    {
        Gson gson = new Gson();
        assertEquals(gson.fromJson("1", int.class), 1);
        assertEquals(gson.fromJson("1", Integer.class), new Integer(1));
        assertEquals(gson.fromJson("1", Long.class), new Long(1));
        assertEquals(gson.fromJson("false", Boolean.class), Boolean.FALSE);

        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
    }

    @Test
    void testSerialization()
    {
        Gson gson = new Gson();
        Employee emp = new Employee(1001, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
        assertEquals("{\"id\":1001,\"firstName\":\"Lokesh\",\"lastName\":\"Gupta\",\"email\":\"howtodoinjava@gmail.com\"}", gson.toJson(emp));
    }

    @Test
    void testDeserialization()
    {
        String jsonString = "{'id':1001, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'}";

        Gson gson = new Gson();

        Employee empObject = gson.fromJson(jsonString, Employee.class);
        assertEquals(empObject.getId(), 1001);
        assertEquals(empObject.getFirstName(), "Lokesh");
        assertEquals(empObject.getLastName(), "Gupta");
        assertEquals(empObject.getEmail(), "howtodoinjava@gmail.com");
    }

    @Test
    void testPrettyFormat()
    {
        Employee employeeObj = new Employee(1, "Lokesh", "Gupta", "howtogoinjava@gmail.com");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        System.out.println(gson.toJson(employeeObj));
    }

    @Test
    void testArray()
    {
        String userJson = "[{'name': 'Alex','id': 1}, "
                + "{'name': 'Brian','id':2}, "
                + "{'name': 'Charles','id': 3}]";

        Gson gson = new Gson();

        User[] userArray = gson.fromJson(userJson, User[].class);

        for (User user : userArray) {
            System.out.println(user);
        }
    }

    @Test
    void testList()
    {
        String userJson = "[{'name': 'Alex','id': 1}, "
                + "{'name': 'Brian','id':2}, "
                + "{'name': 'Charles','id': 3}]";

        Gson gson = new Gson();

        Type userListType = new TypeToken<ArrayList<User>>() {}.getType();

        ArrayList<User> userArray = gson.fromJson(userJson, userListType);

        for (User user : userArray) {
            System.out.println(user);
        }
    }

    @Test
    void testMemeberArray()
    {
        String departmentJson = "{'id' : 1, "
                + "'name': 'HR',"
                + "'users' : ["
                + "{'name': 'Alex','id': 1}, "
                + "{'name': 'Brian','id':2}, "
                + "{'name': 'Charles','id': 3}]}";

        Gson gson = new Gson();

        Department department = gson.fromJson(departmentJson, Department.class);

        System.out.println(department);
    }

    @Test
    void testInteger()
    {
        System.out.println(Integer.MAX_VALUE / 1024 / 1024);
    }
}
