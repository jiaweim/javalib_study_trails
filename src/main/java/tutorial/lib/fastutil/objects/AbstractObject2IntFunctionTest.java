package tutorial.lib.fastutil.objects;


import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AbstractObject2IntFunctionTest {

    @Test
    void testRemove() {
        final Object2IntArrayMap<Object> a = new Object2IntArrayMap<>();
        final Object key = new Object();
        a.put(key, 1);
        assertEquals(1, a.removeInt(key));
    }
}

