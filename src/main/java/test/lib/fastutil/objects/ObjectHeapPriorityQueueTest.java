package test.lib.fastutil.objects;

import it.unimi.dsi.fastutil.io.BinIO;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class ObjectHeapPriorityQueueTest
{

    @SuppressWarnings({"unchecked", "boxing"})
    @Test
    void testSerialize() throws IOException, ClassNotFoundException
    {
        ObjectHeapPriorityQueue<Integer> q = new ObjectHeapPriorityQueue<Integer>();
        for (int i = 0; i < 100; i++) q.enqueue(i);

        File file = File.createTempFile(getClass().getPackage().getName() + "-", "-tmp");
        file.deleteOnExit();
        BinIO.storeObject(q, file);
        ObjectHeapPriorityQueue<Integer> r = (ObjectHeapPriorityQueue<Integer>) BinIO.loadObject(file);
        file.delete();
        for (int i = 0; i < 100; i++) {
            assertEquals(q.first(), r.first());
            assertEquals(q.dequeue(), r.dequeue());
        }
    }
}

