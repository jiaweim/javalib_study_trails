package tutorial.lib.fastutil.objects;


import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ObjectSetsTest
{
    @Test
    void testToArrayShouldNullElementAfterLastEntry()
    {
        ObjectSet<?> set = ObjectSets.EMPTY_SET;
        Object[] values = new Object[]{"test"};
        set.toArray(values);
        assertNull(values[0]);
    }
}
