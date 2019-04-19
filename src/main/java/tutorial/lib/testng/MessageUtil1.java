package tutorial.lib.testng;

/**
 * @author JiaweiMao on 2017.09.06
 * @since 1.0.0
 */
public class MessageUtil1
{
    private String message;

    //Constructor
    //@param message to be printed
    public MessageUtil1(String message)
    {
        this.message = message;
    }

    // prints the message
    public void printMessage()
    {
        System.out.println(message);
        int a = 0;
        int b = 1 / a;
    }

    // add "Hi!" to the message
    public String salutationMessage()
    {
        message = "Hi!" + message;
        System.out.println(message);
        return message;
    }
}
