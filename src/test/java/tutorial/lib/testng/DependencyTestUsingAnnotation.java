package tutorial.lib.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author JiaweiMao on 2017.09.06
 * @since 1.0.0
 */
public class DependencyTestUsingAnnotation
{
    String message = "Manisha";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage()
    {
        System.out.println("Inside testPrintMessage()");
        message = "Manisha";
        Assert.assertEquals(message, messageUtil.printMessage());
    }

    @Test(dependsOnMethods = {"initEnvironmentTest"})
    public void testSalutationMessage()
    {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Manisha";
        Assert.assertEquals(message, messageUtil.salutationMessage());
    }

    @Test
    public void initEnvironmentTest()
    {
        System.out.println("This is initEnvironmentTest");
    }


}
