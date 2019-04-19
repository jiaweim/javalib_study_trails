package tutorial.lib.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author JiaweiMao on 2017.07.18
 * @since 1.0-SNAPSHOT
 */
public class IgnoreTest
{
    String message = "Manisha";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test(enabled = false)
    public void testPrintMessage()
    {
        System.out.println("Inside testPrintMessage()");
        message = "Manisha";
        assertEquals(message, messageUtil.printMessage());
    }

    @Test
    public void testSalutationMessage()
    {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Manisha";
        assertEquals(message, messageUtil.salutationMessage());
    }

}