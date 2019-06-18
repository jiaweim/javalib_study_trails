package tutorial.lib.picocli;

import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 29 Apr 2019, 8:34 PM
 */
@CommandLine.Command(description = "Prints the checksum (MD5 by default) of a file to STDOUT.",
        name = "checksum", mixinStandardHelpOptions = true, version = "checksum 3.0")
public class CheckSum implements Callable<Void>
{
    @Override public Void call() throws Exception
    {
        return null;
    }
}
