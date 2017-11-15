package test.lib.fastutil.ints;


import it.unimi.dsi.fastutil.ints.IntHeapPriorityQueue;
import it.unimi.dsi.fastutil.io.BinIO;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;


public class IntHeapPriorityQueueTest {
	@SuppressWarnings("deprecation")
	@Test
	public void testSerialize() throws IOException, ClassNotFoundException {
		IntHeapPriorityQueue q = new IntHeapPriorityQueue();
		for( int i = 0; i < 100; i++ ) q.enqueue( i );
		
		File file = File.createTempFile( getClass().getPackage().getName() + "-", "-tmp" );
		file.deleteOnExit();
		BinIO.storeObject( q, file );
		IntHeapPriorityQueue r = (IntHeapPriorityQueue)BinIO.loadObject( file );
		file.delete();
		for( int i = 0; i < 100; i++ ) {
			assertEquals( q.first(), r.first() );
			assertEquals( q.dequeue(), r.dequeue() );
		}
	}
}

