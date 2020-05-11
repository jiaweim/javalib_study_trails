package tutorial.lib.fastutil;


import it.unimi.dsi.fastutil.floats.FloatOpenHashSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FloatOpenHashSetTest
{

    @Test
    public void testNaNs()
    {
        FloatOpenHashSet s = new FloatOpenHashSet();
        s.add(Float.NaN);
        s.add(Float.NaN);
        assertEquals(1, s.size());
    }

    @Test
    public void testZeros()
    {
        FloatOpenHashSet s = new FloatOpenHashSet();
        assertTrue(s.add(-0.0f));
        assertTrue(s.add(+0.0f));
        assertEquals(2, s.size());
    }
}
