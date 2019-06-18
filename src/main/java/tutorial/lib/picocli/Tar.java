package tutorial.lib.picocli;

import org.testng.annotations.Test;
import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.Arrays;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 19 Apr 2019, 1:05 PM
 */
public class Tar
{
    @Option(names = "-c", description = "create a new archive")
    boolean create;

    @Option(names = {"-f", "--file"}, paramLabel = "ARCHIVE", description = "the archive file")
    File archive;

    @Parameters(paramLabel = "FILE", description = "one or more files to archive")
    File[] files;

    @Option(names = {"-h", "--hep"}, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    @Test
    public void test()
    {
        String[] args = {"-c", "--file", "result.tar", "file1.txt", "file2.txt"};
        Tar tar = new Tar();
        new CommandLine(tar).parse(args);

        assert !tar.helpRequested;
        assert tar.create;
        assert tar.archive.equals(new File("result.tar"));
        assert Arrays.equals(tar.files, new File[]{new File("file1.txt"), new File("file2.txt")});
    }
}
