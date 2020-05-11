package tutorial.lib.fastutil.objects;


import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectArrayListTest
{
    @SuppressWarnings("boxing")
    @Test
    void testRemoveAll()
    {
        ObjectArrayList<Integer> l = ObjectArrayList.wrap(new Integer[]{0, 1, 1, 2});
        l.removeAll(ObjectSets.singleton(1));

        assertEquals(ObjectArrayList.wrap(new Integer[]{0, 2}), l);

        assertTrue(l.elements()[2] == null);
        assertTrue(l.elements()[3] == null);
    }
}
