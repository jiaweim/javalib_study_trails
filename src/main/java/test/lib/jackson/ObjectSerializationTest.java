package test.lib.jackson;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * @author JiaweiMao 2017.04.13
 * @since 1.0-SNAPSHOT
 */
public class ObjectSerializationTest {

    public static void main(String[] args) throws IOException {

        ObjectSerializationTest test = new ObjectSerializationTest();
        Student student = new Student();
        student.setAge(10);
        student.setName("Mar");
        test.writeJson(student);

        Student student1 = test.readJson();
        System.out.println(student1);
    }

    private void writeJson(Student student) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("student.json"), student);
    }

    private Student readJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("student.json"), Student.class);
    }

    @Test
    void readString() throws IOException {
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(jsonString, Student.class);
        assertEquals("Student [ name: Mahesh, age: 21 ]", student.toString());

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        jsonString = mapper.writeValueAsString(student);
        assertEquals("{\r\n" +
                "  \"name\" : \"Mahesh\",\r\n" +
                "  \"age\" : 21\r\n" +
                "}", jsonString);
    }

    @Test
    void simpleDataBinding() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> stringObjectMap = new HashMap<>();
        int[] marks = {1, 2, 3};
        Student s1 = new Student();
        s1.setAge(10);
        s1.setName("zhangchen");

        // java object
        stringObjectMap.put("student", s1);

        // java string
        stringObjectMap.put("name", "ChenZhang");

        // java boolean
        stringObjectMap.put("verified", Boolean.FALSE);

        // Array
        stringObjectMap.put("marks", marks);

        mapper.writeValue(new File("student.json"), stringObjectMap);

    }

    @Test
    void genericDataBinding() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, UserData> userDataMap = new HashMap<>();
        UserData studentData = new UserData();
        int[] marks = {1, 2, 3};

        Student student = new Student();
        student.setAge(10);
        student.setName("Mahesh");

        // JAVA Object
        studentData.setStudent(student);

        // JAVA String
        studentData.setName("Mahesh Kumar");

        // JAVA Boolean
        studentData.setVerified(Boolean.FALSE);

        // Array
        studentData.setMarks(marks);
        TypeReference ref = new TypeReference<Map<String, UserData>>() {
        };

        userDataMap.put("studentData1", studentData);
        mapper.writeValue(new File("student.json"), userDataMap);

        // {
        //    "studentData1":
        //    {
        //       "student":
        //        {
        //           "name":"Mahesh",
        //           "age":10
        //        },
        //    "name":"Mahesh Kumar",
        //    "verified":false,
        //    "marks":[1,2,3]
        //    }
        // }

        userDataMap = mapper.readValue(new File("student.json"), ref);

        System.out.println(userDataMap.get("studentData1").getStudent());
        System.out.println(userDataMap.get("studentData1").getName());
        System.out.println(userDataMap.get("studentData1").getVerified());
        System.out.println(Arrays.toString(userDataMap.get("studentData1").getMarks()));
    }

    @Test
    void treeModel() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh Kumar\", \"age\":21,\"verified\":false,\"marks\": [100,90,85]}";

        // create tree from JSON
        JsonNode jsonNode = mapper.readTree(jsonString);
        JsonNode name = jsonNode.path("name");
        assertEquals("Mahesh Kumar", name.textValue());

        JsonNode age = jsonNode.path("age");
        assertEquals(21, age.intValue());


        JsonNode verified = jsonNode.path("verified");
        assertEquals(false, verified.booleanValue());

        JsonNode marks = jsonNode.path("marks");
        Iterator<JsonNode> elements = marks.elements();
        while (elements.hasNext()) {
            System.out.println(elements.next().textValue());
        }

    }

    @Test
    void jsonParser() throws IOException {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(carJson);
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            System.out.println("jsonToken=" + jsonToken);
        }
    }

    @Test
    void tree2Json() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode rootNode = mapper.createObjectNode();
        ArrayNode marksNode = mapper.createArrayNode();

        marksNode.add(100);
        marksNode.add(90);
        marksNode.add(85);

        rootNode.put("name", "jiawei");
        rootNode.put("age", 21);
        rootNode.put("verified", false);
        rootNode.put("marks", marksNode);

        mapper.writeValue(new File("student.json"), rootNode);
        JsonNode jsonNode = mapper.readTree(new File("student.json"));
        rootNode = (ObjectNode) mapper.readTree(new File("student.json"));

        JsonNode nameNode = rootNode.path("name");
        System.out.println("Name: " + nameNode.textValue());

        JsonNode ageNode = rootNode.path("age");
        System.out.println("Age: " + ageNode.intValue());

        JsonNode verifiedNode = rootNode.path("verified");
        System.out.println("Verified: " + (verifiedNode.booleanValue() ? "Yes" : "No"));

        JsonNode marksNode1 = rootNode.path("marks");
        Iterator<JsonNode> iterator = marksNode1.elements();
        System.out.print("Marks: [ ");

        while (iterator.hasNext()) {
            JsonNode marks = iterator.next();
            System.out.print(marks.intValue() + " ");
        }

        System.out.println("]");
    }

    @Test
    void stream() throws IOException {
        JsonFactory jasonFactory = new JsonFactory();

        JsonGenerator jsonGenerator = jasonFactory.createGenerator(new File("student.json"), JsonEncoding.UTF8);

        // {
        jsonGenerator.writeStartObject();

        // "name" : "Mahesh Kumar"
        jsonGenerator.writeStringField("name", "Mahesh Kumar");

        // "age" : 21
        jsonGenerator.writeNumberField("age", 21);

        // "verified" : false
        jsonGenerator.writeBooleanField("verified", false);

        // "marks" : [100, 90, 85]
        jsonGenerator.writeFieldName("marks");

        // [
        jsonGenerator.writeStartArray();
        // 100, 90, 85
        jsonGenerator.writeNumber(100);
        jsonGenerator.writeNumber(90);
        jsonGenerator.writeNumber(85);
        // ]

        jsonGenerator.writeEndArray();

        // }

        jsonGenerator.writeEndObject();
        jsonGenerator.close();

        //result student.json
        //{
        //   "name":"Mahesh Kumar",
        //   "age":21,
        //   "verified":false,
        //   "marks":[100,90,85]
        //}

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> dataMap = mapper.readValue(new File("student.json"), Map.class);

        System.out.println(dataMap.get("name"));
        System.out.println(dataMap.get("age"));
        System.out.println(dataMap.get("verified"));
        System.out.println(dataMap.get("marks"));

        JsonParser jsonParser = jasonFactory.createParser(new File("student.json"));

        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            //get the current token
            String fieldname = jsonParser.getCurrentName();

            if ("name".equals(fieldname)) {
                //move to next token
                jsonParser.nextToken();
                System.out.println(jsonParser.getText());
            }

            if ("age".equals(fieldname)) {
                //move to next token
                jsonParser.nextToken();
                System.out.println(jsonParser.getNumberValue());
            }

            if ("verified".equals(fieldname)) {
                //move to next token
                jsonParser.nextToken();
                System.out.println(jsonParser.getBooleanValue());
            }

            if ("marks".equals(fieldname)) {
                //move to [
                jsonParser.nextToken();
                // loop till token equal to "]"

                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    System.out.println(jsonParser.getNumberValue());
                }
            }
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student [ name: " + name + ", age: " + age + " ]";
    }
}

class UserData {
    private Student student;
    private String name;
    private Boolean verified;
    private int[] marks;

    public UserData() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }
}
