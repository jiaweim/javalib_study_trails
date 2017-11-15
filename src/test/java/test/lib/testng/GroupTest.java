package test.lib.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author JiaweiMao on 2017.07.18
 * @since 1.0-SNAPSHOT
 */
public class GroupTest
{
    String message = "tutorialspoint.com";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test(groups = {"functest", "checkintest"})
    public void testPrintMessage()
    {
        System.out.println("Inside testPrintMessage()");
        message = "tutorialspoint.com";
        assertEquals(message, messageUtil.printMessage());
    }

    @Test(groups = {"checkintest"})
    public void testSalutationMessage()
    {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!tutorialspoint.com";
        assertEquals(message, messageUtil.salutationMessage());
    }

    @Test(groups = {"functest"})
    public void testingExitMessage()
    {
        System.out.println("Inside testExitMessage()");
        message = "www.tutorialspoint.com";
        assertEquals(message, messageUtil.exitMessage());
    }

}