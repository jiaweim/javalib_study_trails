package tutorial.lib.picocli;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 19 Apr 2019, 1:14 PM
 */
public class MandatoryOption
{
    @CommandLine.Option(names = "-n", required = true, description = "mandatory number")
    int number;

    @CommandLine.Parameters
    File[] files;

    @Test
    public void test()
    {
        String[] args = {"file1", "file2", "file3"};
        MandatoryOption mandatoryOption = new MandatoryOption();
        assertThrows(CommandLine.MissingParameterException.class, () -> CommandLine.populateCommand(mandatoryOption, args));
    }
}
