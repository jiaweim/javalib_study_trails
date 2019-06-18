package tutorial.lib.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.Callable;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 17 May 2019, 3:27 PM
 */
public class Login implements Callable<Object>
{
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    @Option(names = {"-p", "--password"}, description = "Passphrase", interactive = true)
    char[] password;

    @Override public Object call() throws Exception
    {
        byte[] bytes = new byte[password.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) password[i];
        }

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes);

        System.out.printf("Hi %s, your password is hashed to %s.%n", user, base64(md.digest()));

        // null out the arrays when done
        Arrays.fill(bytes, (byte) 0);
        Arrays.fill(password, ' ');

        return null;
    }

    private String base64(byte[] arr)
    {
        return Base64.getEncoder().encodeToString(arr);
    }

    public static void main(String[] args)
    {
        CommandLine.call(new Login(), "-u", "user123", "-p");
    }
}
