package tutorial.jackson.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

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

        assertTrue(result.contains("attr1"));
        assertTrue(result.contains("val1"));
        assertEquals(result, "{\"name\":\"My bean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}");
    }
}
