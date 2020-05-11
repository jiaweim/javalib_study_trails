package tutorial.lib.fastutil;

import it.unimi.dsi.fastutil.ints.IntBidirectionalIterator;
import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSortedSet;
import it.unimi.dsi.fastutil.longs.Long2IntAVLTreeMap;
import it.unimi.dsi.fastutil.longs.Long2IntSortedMap;
import it.unimi.dsi.fastutil.longs.LongBidirectionalIterator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author JiaweiMao 2017.03.26
 * @since 1.0-SNAPSHOT
 */
public class Example
{
    @Test
    void sortedMap()
    {
        Long2IntSortedMap m = new Long2IntAVLTreeMap();

        // Modify and access its content
        m.put(1, 5);
        m.put(2, 6);
        m.put(3, 7);
        m.put(1000000000L, 10);
        assertEquals(5, m.get(1));
        assertEquals(0, m.get(4)); // default return value is 0

        // change the default return value
        m.defaultReturnValue(-1);
        assertEquals(-1, m.get(4));

        LongBidirectionalIterator it = m.keySet().iterator();
        // sum all keys
        long s = 0;
        while (it.hasNext())
            s += it.nextLong();
        assertEquals(1000000006, s);

        // this map contains only keys smaller than 4
        Long2IntSortedMap m1 = m.headMap(4);
        // This iterator is positioned between 2 and 3
        LongBidirectionalIterator iterator = m1.keySet().iterator(2);
        assertEquals(3, iterator.nextLong());
    }

    @Test
    void sortedSet()
    {
        IntSortedSet s = new IntLinkedOpenHashSet(new int[]{4, 3, 2, 1});
        assertEquals(4, s.firstInt());
        assertEquals(1, s.lastInt());
        assertFalse(s.contains(5));

        IntBidirectionalIterator iterator = s.iterator(s.lastInt());
        assertEquals(1, iterator.previousInt());
        assertEquals(2, iterator.previousInt());


    }

}
