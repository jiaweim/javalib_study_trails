package tutorial.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 09 Oct 2018, 2:26 PM
 */
public class ExtendableBean
{
    public String name;
    private Map<String, String> properties;

    public ExtendableBean()
    {
        properties = new HashMap<>();
    }

    public ExtendableBean(final String name)
    {
        this.name = name;
        properties = new HashMap<>();
    }

    @JsonAnySetter
    public void add(final String key, final String value)
    {
        properties.put(key, value);
    }


    @JsonAnyGetter
    public Map<String, String> getProperties()
    {
        return properties;
    }
}
