package test.lib.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author JiaweiMao on 2017.09.06
 * @since 1.0.0
 */
public class ParameterizedTest1
{
    @Test
    @Parameters("myName")
    public void parameterTest(String myName)
    {
        System.out.println("Parameterized value is : " + myName);
    }

}
