package test.lib.fastutil.ints;


import it.unimi.dsi.fastutil.ints.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class IntCollectionsTest {

	@Test
	public void testIsNotEmpty() {
		IntCollection test = IntCollections.asCollection(new IntIterable() {
			@Override
			public IntIterator iterator() {
				return IntSets.singleton(0).iterator();
			}
		});
		
		assertFalse(test.isEmpty());
	}

	@Test
	public void testEmpty() {
		IntCollection test = IntCollections.asCollection(new IntIterable() {
			@Override
			public IntIterator iterator() {
				return IntSets.EMPTY_SET.iterator();
			}
		});
		
		assertTrue(test.isEmpty());
	}
}
