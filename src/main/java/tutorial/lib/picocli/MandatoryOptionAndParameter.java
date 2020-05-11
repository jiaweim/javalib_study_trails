package tutorial.lib.picocli;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 19 Apr 2019, 1:37 PM
 */
public class MandatoryOptionAndParameter
{
    @CommandLine.Parameters(arity = "1..*", description = "at least one File")
    File[] files;

    @CommandLine.Option(names = "-n", required = true, description = "mandatory number")
    int number;

    @Test
    public void test()
    {
        String[] args = {"-n", "123"};
        assertThrows(CommandLine.MissingParameterException.class, () -> CommandLine.populateCommand(new MandatoryOptionAndParameter(), args));
    }
}
