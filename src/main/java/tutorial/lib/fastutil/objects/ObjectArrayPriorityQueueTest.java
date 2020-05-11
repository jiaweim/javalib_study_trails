package tutorial.lib.fastutil.objects;

import it.unimi.dsi.fastutil.io.BinIO;
import it.unimi.dsi.fastutil.objects.ObjectArrayPriorityQueue;
import it.unimi.dsi.fastutil.objects.ObjectComparators;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SuppressWarnings({"boxing", "unchecked"})
public class ObjectArrayPriorityQueueTest
{

    @Test
    void testEnqueueDequeue()
    {
        ObjectArrayPriorityQueue<Integer> q = new ObjectArrayPriorityQueue<Integer>();
        ObjectHeapPriorityQueue<Integer> h = new ObjectHeapPriorityQueue<Integer>();
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            h.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(h.first(), q.first());
            assertEquals(h.dequeue(), q.dequeue());
        }

        q = new ObjectArrayPriorityQueue<Integer>(10);
        h.clear();
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            h.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(h.first(), q.first());
            assertEquals(h.dequeue(), q.dequeue());
        }

        q = new ObjectArrayPriorityQueue<Integer>(200);
        h.clear();
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            h.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(h.first(), q.first());
            assertEquals(h.dequeue(), q.dequeue());
        }
    }


    @Test
    void testEnqueueDequeueComp()
    {
        ObjectArrayPriorityQueue<Integer> q = new ObjectArrayPriorityQueue<Integer>(ObjectComparators
                .OPPOSITE_COMPARATOR);
        ObjectHeapPriorityQueue<Integer> h = new ObjectHeapPriorityQueue<Integer>(ObjectComparators
                .OPPOSITE_COMPARATOR);
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            h.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(h.first(), q.first());
            assertEquals(h.dequeue(), q.dequeue());
        }

        q = new ObjectArrayPriorityQueue<Integer>(10, ObjectComparators.OPPOSITE_COMPARATOR);
        h.clear();
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            h.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(h.first(), q.first());
            assertEquals(h.dequeue(), q.dequeue());
        }

        q = new ObjectArrayPriorityQueue<Integer>(200, ObjectComparators.OPPOSITE_COMPARATOR);
        h.clear();
        for (int i = 0; i < 100; i++) {
            q.enqueue(i);
            h.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(h.first(), q.first());
            assertEquals(h.dequeue(), q.dequeue());
        }
    }

    @Test
    void testMix()
    {
        ObjectArrayPriorityQueue<Integer> q = new ObjectArrayPriorityQueue<Integer>();
        ObjectHeapPriorityQueue<Integer> h = new ObjectHeapPriorityQueue<Integer>();
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 20; j++) {
                q.enqueue(j + i * 20);
                h.enqueue(j + i * 20);
            }
            for (int j = 0; j < 10; j++) assertEquals(h.dequeue(), q.dequeue());
        }

        q = new ObjectArrayPriorityQueue<Integer>(10);
        h = new ObjectHeapPriorityQueue<Integer>();
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 20; j++) {
                q.enqueue(j + i * -20);
                h.enqueue(j + i * -20);
                q.first();
            }
            for (int j = 0; j < 10; j++) assertEquals(h.dequeue(), q.dequeue());
        }

        q = new ObjectArrayPriorityQueue<Integer>(200);
        h = new ObjectHeapPriorityQueue<Integer>();
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 20; j++) {
                q.enqueue(j + i * 20);
                h.enqueue(j + i * 20);
            }
            for (int j = 0; j < 10; j++) assertEquals(h.dequeue(), q.dequeue());
        }
    }

    @Test
    void testMixComp()
    {
        ObjectArrayPriorityQueue<Integer> q = new ObjectArrayPriorityQueue<Integer>(ObjectComparators
                .OPPOSITE_COMPARATOR);
        ObjectHeapPriorityQueue<Integer> h = new ObjectHeapPriorityQueue<Integer>(ObjectComparators
                .OPPOSITE_COMPARATOR);
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 20; j++) {
                q.enqueue(j + i * 20);
                h.enqueue(j + i * 20);
            }
            for (int j = 0; j < 10; j++) assertEquals(h.dequeue(), q.dequeue());
        }

        q = new ObjectArrayPriorityQueue<Integer>(10, ObjectComparators.OPPOSITE_COMPARATOR);
        h = new ObjectHeapPriorityQueue<Integer>(ObjectComparators.OPPOSITE_COMPARATOR);
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 20; j++) {
                q.enqueue(j + i * -20);
                h.enqueue(j + i * -20);
                q.first();
            }
            for (int j = 0; j < 10; j++) assertEquals(h.dequeue(), q.dequeue());
        }

        q = new ObjectArrayPriorityQueue<Integer>(200, ObjectComparators.OPPOSITE_COMPARATOR);
        h = new ObjectHeapPriorityQueue<Integer>(ObjectComparators.OPPOSITE_COMPARATOR);
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 20; j++) {
                q.enqueue(j + i * 20);
                h.enqueue(j + i * 20);
            }
            for (int j = 0; j < 10; j++) assertEquals(h.dequeue(), q.dequeue());
        }
    }

    @Test
    void testSerialize() throws IOException, ClassNotFoundException
    {
        ObjectArrayPriorityQueue<Integer> q = new ObjectArrayPriorityQueue<Integer>();
        for (int i = 0; i < 100; i++) q.enqueue(i);

        File file = File.createTempFile(getClass().getPackage().getName() + "-", "-tmp");
        file.deleteOnExit();
        BinIO.storeObject(q, file);
        ObjectArrayPriorityQueue<Integer> r = (ObjectArrayPriorityQueue<Integer>) BinIO.loadObject(file);
        file.delete();
        for (int i = 0; i < 100; i++) {
            assertEquals(q.first(), r.first());
            assertEquals(q.dequeue(), r.dequeue());
        }
    }
}

