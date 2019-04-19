package tutorial.lib.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author JiaweiMao on 2017.09.06
 * @since 1.0.0
 */
public class MessageUtilTest3
{
    String message = "Manisha";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage()
    {
        System.out.println("Inside testPrintMessage()");
        Assert.assertEquals(message, messageUtil.printMessage());
    }

}
