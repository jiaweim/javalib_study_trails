package tutorial.lib.fastutil.ints;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntMaps;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;


public class Int2IntMapsTest
{
    @SuppressWarnings("boxing")
    @Test
    void testSingletonMapEqualsShouldCheckTheTypeOfParamters()
    {
        Int2IntMap map = Int2IntMaps.singleton(1, 2);
        assertFalse(map.equals(Collections.singletonMap(null, 2)));
        assertFalse(map.equals(Collections.singletonMap(1, null)));
        assertFalse(map.equals(Collections.singletonMap("foo", 2)));
        assertFalse(map.equals(Collections.singletonMap(1, "foo")));
    }

    @Test
    void testToArrayShouldNullElementAfterLastEntry()
    {
        Int2IntMap map = Int2IntMaps.EMPTY_MAP;
        Object[] values = new Object[]{"test"};
        map.entrySet().toArray(values);
        assertNull(values[0]);
    }
}
