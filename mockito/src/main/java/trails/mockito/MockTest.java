package trails.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Nov 2019, 11:38 AM
 */
class MockTest
{
    @Test
    void testList()
    {
        // mock creation
        List mockList = mock(List.class);

        // using mock object
        mockList.add("one");
        mockList.clear();

        // verification
        verify(mockList).add("one");
        verify(mockList).clear();
    }

    @Test
    void testReturn()
    {
        // mock concrete class
        LinkedList mockList = mock(LinkedList.class);

        // stubbing
        when(mockList.get(0)).thenReturn("first");
        when(mockList.get(1)).thenThrow(new RuntimeException());

        assertEquals(mockList.get(0), "first");
        assertThrows(RuntimeException.class, () -> mockList.get(1));

        // get(999) was not stubbed
        assertNull(mockList.get(999));
    }

    @Test
    void testArgumentMatcher()
    {
        LinkedList mockList = mock(LinkedList.class);
        when(mockList.get(anyInt())).thenReturn("element");


    }

    @Test
    void testInvocationNumber()
    {
        LinkedList mockedList = mock(LinkedList.class);
        //using mock
        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }

}
