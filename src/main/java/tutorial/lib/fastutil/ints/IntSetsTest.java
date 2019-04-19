package tutorial.lib.fastutil.ints;


import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntSets;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;


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
