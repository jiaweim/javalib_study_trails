package test.lib.fastutil.objects;


import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;


public class ObjectSetsTest {
    @Test
    void testToArrayShouldNullElementAfterLastEntry() {
        ObjectSet<?> set = ObjectSets.EMPTY_SET;
        Object[] values = new Object[]{"test"};
        set.toArray(values);
        assertNull(values[0]);
    }
}
