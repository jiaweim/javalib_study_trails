package tutorial.lib.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 03 Jan 2019, 1:14 PM
 */
public class ArgDemo1
{
    class ParamType
    {

    }

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
    private Integer verbose = -1;

    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    private String groups;

    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

    @Test
    public void test()
    {
        ArgDemo1 arg = new ArgDemo1();
        String[] argv = {"-log", "2", "-groups", "unit"};
        JCommander.newBuilder()
                .addObject(arg)
                .build()
                .parse(argv);
        assertEquals(arg.verbose.intValue(), 2);
    }

    class Param
    {
        @Parameter(names = {"--length", "-l"})
        int length;
        @Parameter(names = {"--pattern", "-p"})
        int pattern;
    }

    @Test
    public void test1()
    {
        String[] args = {"-l", "512", "--pattern", "2"};
        Param param = new Param();
        JCommander.newBuilder().addObject(param)
                .build().parse(args);
        assertEquals(param.length, 512);
        assertEquals(param.pattern, 2);
    }
}
