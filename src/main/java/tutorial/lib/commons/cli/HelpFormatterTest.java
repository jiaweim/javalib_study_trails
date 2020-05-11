package tutorial.lib.commons.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 15 Nov 2017, 8:42 AM
 */
public class HelpFormatterTest
{
    private static final String EOL = System.getProperty("line.separator");

    @Test
    public void test()
    {
        Options options = new Options();
        options.addOption(Option.builder("f").longOpt("file").hasArg().argName("FILE")
                .required().desc("The file to be processed").build());
        options.addOption(Option.builder("v").longOpt("version")
                .desc("Print the version of the application").build());
        options.addOption(Option.builder("h").longOpt("help").build());

        String header = "Do something useful with an input file\n\n";
        String footer = "\nPlease report issues at http://example.com/issues";

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("myapp", header, options, footer, true);
    }


    @Test
    public void testPrintHelpWithEmptySyntax()
    {
        final HelpFormatter formatter = new HelpFormatter();
        try {
            formatter.printHelp(null, new Options());
            fail("null command line syntax should be rejected");
        } catch (final IllegalArgumentException e) {
            // expected
        }

        try {
            formatter.printHelp("", new Options());
            fail("empty command line syntax should be rejected");
        } catch (final IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testAutomaticUsage() throws Exception
    {
        final HelpFormatter hf = new HelpFormatter();

        Options options = null;
        String expected = "usage: app [-a]";
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintWriter pw = new PrintWriter(out);

        options = new Options().addOption("a", false, "aaaa aaaa aaaa aaaa aaaa");
        hf.printUsage(pw, 60, "app", options);
        pw.flush();
        assertEquals(expected, out.toString().trim(), "simple auto usage");
        out.reset();

        expected = "usage: app [-a] [-b]";
        options = new Options().addOption("a", false, "aaaa aaaa aaaa aaaa aaaa")
                .addOption("b", false, "bbb");
        hf.printUsage(pw, 60, "app", options);
        pw.flush();
        assertEquals("simple auto usage", expected, out.toString().trim());
        out.reset();
    }

    // This test ensures the options are properly sorted
    // See https://issues.apache.org/jira/browse/CLI-131
    @Test
    public void testPrintUsage()
    {
        final Option optionA = new Option("a", "first");
        final Option optionB = new Option("b", "second");
        final Option optionC = new Option("c", "third");
        final Options opts = new Options();
        opts.addOption(optionA);
        opts.addOption(optionB);
        opts.addOption(optionC);
        final HelpFormatter helpFormatter = new HelpFormatter();
        final ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        final PrintWriter printWriter = new PrintWriter(bytesOut);
        helpFormatter.printUsage(printWriter, 80, "app", opts);
        printWriter.close();
        assertEquals("usage: app [-a] [-b] [-c]" + EOL, bytesOut.toString());
    }

    @Test
    public void testPrintSortedUsageWithNullComparator()
    {
        final Options opts = new Options();
        opts.addOption(new Option("c", "first"));
        opts.addOption(new Option("b", "second"));
        opts.addOption(new Option("a", "third"));

        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.setOptionComparator(null);

        final StringWriter out = new StringWriter();
        helpFormatter.printUsage(new PrintWriter(out), 80, "app", opts);

        assertEquals("usage: app [-c] [-b] [-a]" + EOL, out.toString());
    }

    @Test
    public void testPrintOptionGroupUsage()
    {
        final OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        group.addOption(Option.builder("c").build());

        final Options options = new Options();
        options.addOptionGroup(group);

        final StringWriter out = new StringWriter();

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printUsage(new PrintWriter(out), 80, "app", options);

        assertEquals("usage: app [-a | -b | -c]" + EOL, out.toString());
    }

    @Test
    public void testPrintRequiredOptionGroupUsage()
    {
        final OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("a").build());
        group.addOption(Option.builder("b").build());
        group.addOption(Option.builder("c").build());
        group.setRequired(true);

        final Options options = new Options();
        options.addOptionGroup(group);

        final StringWriter out = new StringWriter();

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printUsage(new PrintWriter(out), 80, "app", options);

        assertEquals("usage: app -a | -b | -c" + EOL, out.toString());
    }

    @Test
    public void testPrintOptionWithEmptyArgNameUsage()
    {
        final Option option = new Option("f", true, null);
        option.setArgName("");
        option.setRequired(true);

        final Options options = new Options();
        options.addOption(option);

        final StringWriter out = new StringWriter();

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printUsage(new PrintWriter(out), 80, "app", options);

        assertEquals("usage: app -f" + EOL, out.toString());
    }

    @Test
    public void testDefaultArgName()
    {
        final Option option = Option.builder("f").hasArg().required(true).build();

        final Options options = new Options();
        options.addOption(option);

        final StringWriter out = new StringWriter();

        final HelpFormatter formatter = new HelpFormatter();
        formatter.setArgName("argument");
        formatter.printUsage(new PrintWriter(out), 80, "app", options);

        assertEquals("usage: app -f <argument>" + EOL, out.toString());
    }


    @Test
    public void testAccessors()
    {
        final HelpFormatter formatter = new HelpFormatter();

        formatter.setArgName("argname");
        assertEquals("arg name", "argname", formatter.getArgName());

        formatter.setDescPadding(3);
        assertEquals(3, formatter.getDescPadding(), "desc padding");

        formatter.setLeftPadding(7);
        assertEquals(7, formatter.getLeftPadding(), "left padding");

        formatter.setLongOptPrefix("~~");
        assertEquals("long opt prefix", "~~", formatter.getLongOptPrefix());

        formatter.setNewLine("\n");
        assertEquals("new line", "\n", formatter.getNewLine());

        formatter.setOptPrefix("~");
        assertEquals("opt prefix", "~", formatter.getOptPrefix());

        formatter.setSyntaxPrefix("-> ");
        assertEquals("syntax prefix", "-> ", formatter.getSyntaxPrefix());

        formatter.setWidth(80);
        assertEquals(80, formatter.getWidth(), "width");
    }

    @Test
    public void testHeaderStartingWithLineSeparator()
    {
        // related to Bugzilla #21215
        final Options options = new Options();
        final HelpFormatter formatter = new HelpFormatter();
        final String header = EOL + "Header";
        final String footer = "Footer";
        final StringWriter out = new StringWriter();
        formatter.printHelp(new PrintWriter(out), 80, "foobar", header, options, 2, 2, footer, true);
        assertEquals(
                "usage: foobar" + EOL +
                        "" + EOL +
                        "Header" + EOL +
                        "" + EOL +
                        "Footer" + EOL
                , out.toString());
    }

    @Test
    public void testIndentedHeaderAndFooter()
    {
        // related to CLI-207
        final Options options = new Options();
        final HelpFormatter formatter = new HelpFormatter();
        final String header = "  Header1\n  Header2";
        final String footer = "  Footer1\n  Footer2";
        final StringWriter out = new StringWriter();
        formatter.printHelp(new PrintWriter(out), 80, "foobar", header, options, 2, 2, footer, true);

        assertEquals(
                "usage: foobar" + EOL +
                        "  Header1" + EOL +
                        "  Header2" + EOL +
                        "" + EOL +
                        "  Footer1" + EOL +
                        "  Footer2" + EOL
                , out.toString());
    }

    @Test
    public void testOptionWithoutShortFormat()
    {
        // related to Bugzilla #19383 (CLI-67)
        final Options options = new Options();
        options.addOption(new Option("a", "aaa", false, "aaaaaaa"));
        options.addOption(new Option(null, "bbb", false, "bbbbbbb"));
        options.addOption(new Option("c", null, false, "ccccccc"));

        final HelpFormatter formatter = new HelpFormatter();
        final StringWriter out = new StringWriter();
        formatter.printHelp(new PrintWriter(out), 80, "foobar", "", options, 2, 2, "", true);
        assertEquals(
                "usage: foobar [-a] [--bbb] [-c]" + EOL +
                        "  -a,--aaa  aaaaaaa" + EOL +
                        "     --bbb  bbbbbbb" + EOL +
                        "  -c        ccccccc" + EOL
                , out.toString());
    }

    @Test
    public void testOptionWithoutShortFormat2()
    {
        // related to Bugzilla #27635 (CLI-26)
        final Option help = new Option("h", "help", false, "print this message");
        final Option version = new Option("v", "version", false, "print version information");
        final Option newRun = new Option("n", "new", false, "Create NLT cache entries only for new items");
        final Option trackerRun = new Option("t", "tracker", false, "Create NLT cache entries only for tracker items");

        final Option timeLimit = Option.builder("l")
                .longOpt("limit")
                .hasArg()
                .valueSeparator()
                .desc("Set time limit for execution, in mintues")
                .build();

        final Option age = Option.builder("a").longOpt("age")
                .hasArg()
                .valueSeparator()
                .desc("Age (in days) of cache item before being recomputed")
                .build();

        final Option server = Option.builder("s").longOpt("server")
                .hasArg()
                .valueSeparator()
                .desc("The NLT server address")
                .build();

        final Option numResults = Option.builder("r").longOpt("results")
                .hasArg()
                .valueSeparator()
                .desc("Number of results per item")
                .build();

        final Option configFile = Option.builder().longOpt("config")
                .hasArg()
                .valueSeparator()
                .desc("Use the specified configuration file")
                .build();

        final Options mOptions = new Options();
        mOptions.addOption(help);
        mOptions.addOption(version);
        mOptions.addOption(newRun);
        mOptions.addOption(trackerRun);
        mOptions.addOption(timeLimit);
        mOptions.addOption(age);
        mOptions.addOption(server);
        mOptions.addOption(numResults);
        mOptions.addOption(configFile);

        final HelpFormatter formatter = new HelpFormatter();
        final String EOL = System.getProperty("line.separator");
        final StringWriter out = new StringWriter();
        formatter.printHelp(new PrintWriter(out), 80, "commandline", "header", mOptions, 2, 2, "footer", true);
        assertEquals(
                "usage: commandline [-a <arg>] [--config <arg>] [-h] [-l <arg>] [-n] [-r <arg>]" + EOL +
                        "       [-s <arg>] [-t] [-v]" + EOL +
                        "header" + EOL +
                        "  -a,--age <arg>      Age (in days) of cache item before being recomputed" + EOL +
                        "     --config <arg>   Use the specified configuration file" + EOL +
                        "  -h,--help           print this message" + EOL +
                        "  -l,--limit <arg>    Set time limit for execution, in mintues" + EOL +
                        "  -n,--new            Create NLT cache entries only for new items" + EOL +
                        "  -r,--results <arg>  Number of results per item" + EOL +
                        "  -s,--server <arg>   The NLT server address" + EOL +
                        "  -t,--tracker        Create NLT cache entries only for tracker items" + EOL +
                        "  -v,--version        print version information" + EOL +
                        "footer" + EOL
                , out.toString());
    }

    @Test
    public void testHelpWithLongOptSeparator() throws Exception
    {
        final Options options = new Options();
        options.addOption("f", true, "the file");
        options.addOption(Option.builder("s").longOpt("size").desc("the size").hasArg().argName("SIZE").build());
        options.addOption(Option.builder().longOpt("age").desc("the age").hasArg().build());

        final HelpFormatter formatter = new HelpFormatter();
        assertEquals(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, formatter.getLongOptSeparator());
        formatter.setLongOptSeparator("=");
        assertEquals("=", formatter.getLongOptSeparator());

        final StringWriter out = new StringWriter();

        formatter.printHelp(new PrintWriter(out), 80, "create", "header", options, 2, 2, "footer");

        assertEquals(
                "usage: create" + EOL +
                        "header" + EOL +
                        "     --age=<arg>    the age" + EOL +
                        "  -f <arg>          the file" + EOL +
                        "  -s,--size=<SIZE>  the size" + EOL +
                        "footer" + EOL,
                out.toString());
    }

    @Test
    public void testUsageWithLongOptSeparator() throws Exception
    {
        final Options options = new Options();
        options.addOption("f", true, "the file");
        options.addOption(Option.builder("s").longOpt("size").desc("the size").hasArg().argName("SIZE").build());
        options.addOption(Option.builder().longOpt("age").desc("the age").hasArg().build());

        final HelpFormatter formatter = new HelpFormatter();
        formatter.setLongOptSeparator("=");

        final StringWriter out = new StringWriter();

        formatter.printUsage(new PrintWriter(out), 80, "create", options);

        assertEquals("usage: create [--age=<arg>] [-f <arg>] [-s <SIZE>]", out.toString().trim());
    }
}
