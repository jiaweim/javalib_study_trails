package test.lib.testng;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author JiaweiMao on 2017.07.17
 * @since 1.0-SNAPSHOT
 */
public class TestNGSimpleTest
{

    @Test
    public void testAdd()
    {
        String str = "TestNG is working fine";
        assertEquals("TestNG is working fine", str);
    }

}
