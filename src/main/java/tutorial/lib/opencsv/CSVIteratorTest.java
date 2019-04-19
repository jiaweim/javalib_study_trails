package tutorial.lib.opencsv;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class CSVIteratorTest
{
    private static final String[] STRINGS = {"test1", "test2"};
    private CSVIterator iterator;
    private CSVReader mockReader;

    @BeforeClass
    public void setUp() throws IOException
    {
        mockReader = mock(CSVReader.class);
        when(mockReader.readNext()).thenReturn(STRINGS);
        iterator = new CSVIterator(mockReader);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void readerExceptionCausesRunTimeException() throws IOException
    {
        when(mockReader.readNext()).thenThrow(new IOException("reader threw test exception"));
        iterator.next();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void removethrowsUnsupportedOperationException()
    {
        iterator.remove();
    }

    @Test
    public void initialReadReturnsStrings()
    {
        assertArrayEquals(STRINGS, iterator.next());
    }

    @Test
    public void hasNextWorks() throws IOException
    {
        when(mockReader.readNext()).thenReturn(null);
        assertTrue(iterator.hasNext()); // initial read from constructor
        iterator.next();
        assertFalse(iterator.hasNext());
    }
}
