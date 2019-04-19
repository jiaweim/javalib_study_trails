package tutorial.lib.fastutil.objects;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.bytes.ByteArrays;
import it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class ObjectOpenCustomHashSetTest {

    @Test
    void testGetNullKey() {
        final ObjectOpenCustomHashSet<Integer> s = new ObjectOpenCustomHashSet<Integer>(new Hash.Strategy<Integer>() {

            @Override
            public int hashCode(Integer o) {
                return o == null ? 0 : o % 10;
            }

            @Override
            public boolean equals(Integer a, Integer b) {
                if (((a == null) != (b == null)) || a == null) return false;
                return ((a - b) % 10) == 0;
            }
        });

        s.add(10);
        assertTrue(s.contains(0));
        assertEquals(10, s.iterator().next().intValue());
    }

    @Test
    void testNullKey() {
        Random random = new Random(0);
        ObjectOpenCustomHashSet<byte[]> s = new ObjectOpenCustomHashSet<byte[]>(ByteArrays.HASH_STRATEGY);
        for (int i = 0; i < 1000000; i++) {
            byte[] a = new byte[random.nextInt(10)];
            for (int j = a.length; j-- != 0; ) a[j] = (byte) random.nextInt(4);
            s.add(a);
        }
    }

}
