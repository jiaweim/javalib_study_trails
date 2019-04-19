package tutorial.lib.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author JiaweiMao on 2017.09.06
 * @since 1.0.0
 */
public class MessageUtil1Test
{
    String message = "Manisha";
    MessageUtil1 messageUtil1 = new MessageUtil1(message);

    @Test(expectedExceptions = ArithmeticException.class)
    public void testPrintMessage() throws Exception
    {
        System.out.println("Inside testPrintMessage()");
        messageUtil1.printMessage();
    }

    @Test
    public void testSalutationMessage() throws Exception
    {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!Manisha";
        assertEquals(message, messageUtil1.salutationMessage());
    }

}