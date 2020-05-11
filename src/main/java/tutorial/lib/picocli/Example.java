package tutorial.lib.picocli;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 19 Apr 2019, 12:43 PM
 */
public class Example
{
    @Option(names = {"-v", "--verbose"}, description = "Be verbose.")
    private boolean verbose = false;

    @Parameters(arity = "1..*", paramLabel = "FILE", description = "File(s) to process.")
    private File[] inputFiles;

    @Test
    public void test()
    {
        String[] args = {"-v", "inputFile1", "inputFile2"};
        Example app = CommandLine.populateCommand(new Example(), args);
        assert app.verbose;
        assert app.inputFiles != null && app.inputFiles.length == 2;
    }
}
