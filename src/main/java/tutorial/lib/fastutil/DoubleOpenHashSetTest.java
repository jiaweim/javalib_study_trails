package tutorial.lib.fastutil;


import it.unimi.dsi.fastutil.doubles.DoubleOpenHashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoubleOpenHashSetTest
{
    @Test
    public void testNaNs()
    {
        DoubleOpenHashSet s = new DoubleOpenHashSet();
        s.add(Double.NaN);
        s.add(Double.NaN);
        assertEquals(1, s.size());
    }

    @Test
    public void testZeros()
    {
        DoubleOpenHashSet s = new DoubleOpenHashSet();
        assertTrue(s.add(-0.0d));
        assertTrue(s.add(+0.0d));
        assertEquals(2, s.size());
    }

}
