package test.lib.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author JiaweiMao on 2017.07.18
 * @since 1.0-SNAPSHOT
 */
public class MessageUtilTest1
{
    String message = "Hello World";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() throws Exception
    {
        assertEquals(message, messageUtil.printMessage());
    }

}