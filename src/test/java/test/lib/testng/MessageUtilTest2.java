package test.lib.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author JiaweiMao on 2017.07.18
 * @since 1.0-SNAPSHOT
 */
public class MessageUtilTest2
{
    String message = "Manisha";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testSalutationMessage()
    {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Manisha";
        assertEquals(message, messageUtil.salutationMessage());
    }

}