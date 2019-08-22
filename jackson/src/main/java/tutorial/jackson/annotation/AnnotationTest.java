package tutorial.jackson.annotation;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 09 Oct 2018, 2:28 PM
 */
public class AnnotationTest
{
    @Test
    public void whenSerializingUsingJsonAnyGetter_thenCorrect() throws JsonProcessingException
    {
        ExtendableBean bean = new ExtendableBean("My bean");
        bean.add("attr1", "val1");
        bean.add("attr2", "val2");

        String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(result);

        assertTrue(result.contains("attr1"));
        assertTrue(result.contains("val1"));
        assertEquals(result, "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}");
    }

    @Test
    public void whenSerializingUsingJsonGetter_thenCorrect()
            throws JsonProcessingException
    {
        MyBean bean = new MyBean(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);

        System.out.println(result);
        assert result.contains("My bean");
        assert result.contains("1");
    }

    @Test
    public void whenSerializingUsingJsonPropertyOrder_thenCorrect()
            throws JsonProcessingException
    {

        MyBean2 bean = new MyBean2(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);
        assertEquals(result, "{\"name\":\"My bean\",\"id\":1}");
    }

    @Test
    public void whenSerializingUsingJsonRawValue_thenCorrect()
            throws JsonProcessingException
    {
        RawBean bean = new RawBean("My bean", "{\"attr\":false}");
        String result = new ObjectMapper().writeValueAsString(bean);
        assertEquals(result, "{\"name\":\"My bean\",\"json\":{\"attr\":false}}");
    }

    @Test
    public void whenSerializingUsingJsonValue_thenCorrect()
            throws IOException
    {
        String enumAsString = new ObjectMapper().writeValueAsString(TypeEnumWithValue.TYPE1);
        assertEquals(enumAsString, "\"Type A\"");
    }
}
