package tutorial.lib.fastutil.objects;

import it.unimi.dsi.fastutil.io.BinIO;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ReferenceArraySet;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class ReferenceArraySetTest {

    @Test
    void testSet() {
        for (int i = 0; i <= 2; i++) {
            final ReferenceArraySet<Object> s = i == 0 ? new ReferenceArraySet<>() : new
                    ReferenceArraySet<>(2);
            Integer one = 1, two = 2, three = 3;

            assertTrue(s.add(one));
            assertEquals(1, s.size());
            assertTrue(s.contains(one));
            assertFalse(s.contains(1));
            assertTrue(s.add(two));
            assertTrue(s.contains(two));
            assertFalse(s.contains(2));
            assertEquals(2, s.size());
            assertFalse(s.add(one));
            assertFalse(s.remove(three));
            assertTrue(s.add(three));
            assertEquals(3, s.size());
            assertTrue(s.contains(one));
            assertTrue(s.contains(two));
            assertTrue(s.contains(three));
            assertEquals(new ReferenceOpenHashSet<Object>(new Object[]{one, two, three}), new
                    ReferenceOpenHashSet<Object>(s.iterator()));
            assertTrue(s.remove(three));
            assertEquals(2, s.size());
            assertTrue(s.remove(one));
            assertEquals(1, s.size());
            assertFalse(s.contains(one));
            assertTrue(s.remove(two));
            assertEquals(0, s.size());
            assertFalse(s.contains(one));
        }
    }

    @Test
    void testClone() {
        ReferenceArraySet<Integer> s = new ReferenceArraySet<>();
        assertEquals(s, s.clone());

        Integer zero;
        s.add(zero = 0);
        assertEquals(s, s.clone());
        s.add(0);
        assertEquals(s, s.clone());
        s.add(1);
        assertEquals(s, s.clone());
        s.add(2);
        assertEquals(s, s.clone());
        s.remove(zero);
        assertEquals(s, s.clone());
    }

    @Test
    void testSerialisation() throws IOException, ClassNotFoundException {
        // We can't really test reference maps as equals() doesnt' work
        ObjectArraySet<Integer> s = new ObjectArraySet<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(s);
        oos.close();

        assertEquals(s, BinIO.loadObject(new ByteArrayInputStream(baos.toByteArray())));

        s.add(0);
        s.add(1);

        baos.reset();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(s);
        oos.close();
        assertEquals(s, BinIO.loadObject(new ByteArrayInputStream(baos.toByteArray())));
    }
}
