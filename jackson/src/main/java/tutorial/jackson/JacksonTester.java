package tutorial.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 6:37 PM
 */
public class JacksonTester
{
    static class Student
    {
        private String name;
        private int age;

        public Student() {}

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getAge()
        {
            return age;
        }

        public void setAge(int age)
        {
            this.age = age;
        }

        public String toString()
        {
            return "Student [ name: " + name + ", age: " + age + " ]";
        }
    }

    @Test
    void testMapper() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        Student student = mapper.readValue(jsonString, Student.class);
        System.out.println(student);

        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(s);
    }
}
