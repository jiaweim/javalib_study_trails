package tutorial.lib.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 03 Jan 2019, 1:14 PM
 */
public class ArgsDemo
{
    private static class Args1
    {
        @Parameter
        private List<String> parameters = new ArrayList<>();

        @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
        private Integer verbose = -1;

        @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
        private String groups;

        @Parameter(names = "-debug", description = "Debug mode")
        private boolean debug = false;
    }

    @Test
    public void testArgs1()
    {
        Args1 arg = new Args1();
        String[] argv = {"-log", "2", "-groups", "unit"};
        JCommander.newBuilder()
                .addObject(arg)
                .build()
                .parse(argv);
        assertEquals(arg.verbose.intValue(), 2);
        assertEquals(arg.groups, "unit");
        assertTrue(arg.parameters.isEmpty());
        assertFalse(arg.debug);
    }

    private class Args2
    {
        @Parameter(names = {"--length", "-l"})
        int length;
        @Parameter(names = {"--pattern", "-p"})
        int pattern;
    }


    @Test
    public void testArgs2()
    {
        String[] args = {"-l", "512", "--pattern", "2"};
        Args2 param = new Args2();
        JCommander.newBuilder().addObject(param)
                .build().parse(args);
        assertEquals(param.length, 512);
        assertEquals(param.pattern, 2);
    }

    private class ArgList
    {
        @Parameter(names = "-i")
        private List<String> list;
    }

    @Test
    public void testArgList()
    {
        String[] args = {"-i", "1", "-i", "3"};
        ArgList argList = new ArgList();
        JCommander jCommander = JCommander.newBuilder().addObject(argList).build();
        jCommander.parse(args);
        System.out.println(argList.list.size());
    }
}
