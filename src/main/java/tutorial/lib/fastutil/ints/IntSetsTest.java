package tutorial.lib.fastutil.ints;


import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class IntSetsTest
{
    @Test
    void testToArrayShouldNullElementAfterLastEntry()
    {
        IntSet set = IntSets.EMPTY_SET;
        Object[] values = new Object[]{"test"};
        set.toArray(values);
        assertNull(values[0]);
    }
}
