package tutorial.lib.fastutil.ints;

import it.unimi.dsi.fastutil.ints.IntHash;
import it.unimi.dsi.fastutil.ints.IntOpenCustomHashSet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


/**
 * Not a particularly good test, but it will check that we use everywhere the same hashing strategy.
 */
public class IntOpenCustomHashSetTest {

    @Test
    void testGetNullKey() {
        final IntOpenCustomHashSet s = new IntOpenCustomHashSet(new IntHash.Strategy() {

            @Override
            public int hashCode(int o) {
                return o % 10;
            }

            @Override
            public boolean equals(int a, int b) {
                return (a - b) % 10 == 0;
            }
        });

        s.add(10);
        assertTrue(s.contains(0));
        assertEquals(10, s.iterator().nextInt());
    }


    @Test
    void testCustomUsed() {
        IntOpenCustomHashSet set = new IntOpenCustomHashSet(new IntHash.Strategy() {
            @Override
            public int hashCode(int e) {
                return Integer.hashCode(e & 0xFFFF);
            }

            @Override
            public boolean equals(int a, int b) {
                return (a & 0xFFFF) == (b & 0xFFFF);
            }
        });

        set.add(1 << 16 | 1);
        set.add(1);
        assertEquals(1, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(1 << 16 | 1));
    }
}
