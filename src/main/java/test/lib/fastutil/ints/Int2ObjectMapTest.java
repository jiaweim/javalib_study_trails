package test.lib.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.testng.annotations.Test;

/**
 * @author JiaweiMao on 2017.09.07
 * @since 1.0.0
 */
public class Int2ObjectMapTest
{

    @Test
    public void defaultValue()
    {
        Int2ObjectMap<String> map = new Int2ObjectOpenHashMap<>();
        System.out.println(map.defaultReturnValue());
    }

}
