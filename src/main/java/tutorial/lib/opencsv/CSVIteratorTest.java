package tutorial.lib.opencsv;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CSVIteratorTest
{
    private static final String[] STRINGS = {"test1", "test2"};
    private CSVIterator iterator;
    private CSVReader mockReader;

    @BeforeAll
    public void setUp() throws IOException
    {
        mockReader = mock(CSVReader.class);
        when(mockReader.readNext()).thenReturn(STRINGS);
        iterator = new CSVIterator(mockReader);
    }

    @Test
    public void readerExceptionCausesRunTimeException() throws IOException
    {
        when(mockReader.readNext()).thenThrow(new IOException("reader threw test exception"));
        assertThrows(RuntimeException.class, () -> iterator.next());
    }

    @Test
    public void removethrowsUnsupportedOperationException()
    {
        assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
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
