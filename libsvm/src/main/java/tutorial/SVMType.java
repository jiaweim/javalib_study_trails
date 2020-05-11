package tutorial;

import java.util.Arrays;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 28 Oct 2019, 1:33 PM
 */
public enum SVMType
{
    C_SVC,
    NU_SVC,
    ONE_CLASS,
    EPSILON_SVR,
    NU_SVR;

    public static void main(String[] args)
    {
        String line = "+1   1:0.0424107142857143 2:0.0915178571428571 ";
        String[] values = line.split("[\\s+]");
        System.out.println(Arrays.toString(values));
    }
}
