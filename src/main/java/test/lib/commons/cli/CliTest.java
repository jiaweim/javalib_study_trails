package test.lib.commons.cli;

import org.apache.commons.cli.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.*;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 14 Nov 2017, 5:53 PM
 */
public class CliTest
{

    @Test
    public void antTest()
    {
        Option help = new Option("help", "print this message");
        Option projecthelp = new Option("projecthelp", "print project help information");
        Option version = new Option("version", "print the version information and exit");
        Option quiet = new Option("quiet", "be extra quiet");
        Option verbose = new Option("verbose", "be extra verbose");
        Option debug = new Option("debug", "print debugging information");
        Option emacs = new Option("emacs", "produce logging information without adornments");

        Option logfile = Option.builder("logfile").argName("file").hasArg().desc("use given file for log").build();
        Option logger = Option.builder("logger").argName("classname").hasArg().desc("the class which it to perform logging").build();
        Option listener = Option.builder("listener").argName("classname").hasArg().desc("add an instance of class as a project listener").build();
        Option buildfile = Option.builder("buildfile").argName("file").hasArg().desc("use given buildfile").build();
        Option find = Option.builder("find").argName("file").hasArg().desc("search for buildfile towards the root of the filesystem and use it").build();

        Option property = Option.builder("D").argName("property=value").numberOfArgs(2).valueSeparator()
                .desc("use value for given property").build();

        Options options = new Options();

        options.addOption(help);
        options.addOption(projecthelp);
        options.addOption(version);
        options.addOption(quiet);
        options.addOption(verbose);
        options.addOption(debug);
        options.addOption(emacs);
        options.addOption(logfile);
        options.addOption(logger);
        options.addOption(listener);
        options.addOption(buildfile);
        options.addOption(find);
        options.addOption(property);

        String[] args = new String[]{};
        CommandLineParser parser = new DefaultParser();
        try {
            parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private CommandLineParser parser;
    private Options options;

    @BeforeClass
    public void setUp()
    {
        options = new Options()
                .addOption("a", "enable-a", false, "turn [a] on or off")
                .addOption("b", "bfile", true, "set the value of [b]")
                .addOption("c", "copt", false, "turn [c] on or off");
        parser = new DefaultParser();
    }

    @Test
    public void testSimpleShort() throws Exception
    {
        final String[] args = new String[]{"-a",
                "-b", "toast",
                "foo", "bar"};

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("a"), "Confirm -a is set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.getOptionValue("b").equals("toast"), "Confirm arg of -b");
        assertTrue(cl.getArgList().size() == 2, "Confirm size of extra args");
    }

    @Test
    public void testSimpleLong() throws Exception
    {
        final String[] args = new String[]{"--enable-a",
                "--bfile", "toast",
                "foo", "bar"};

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("a"), "Confirm -a is set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.getOptionValue("b").equals("toast"), "Confirm arg of -b");
        assertTrue(cl.getOptionValue("bfile").equals("toast"), "Confirm arg of --bfile");
        assertTrue(cl.getArgList().size() == 2, "Confirm size of extra args");
    }

    @Test
    public void testMultiple() throws Exception
    {
        final String[] args = new String[]{"-c",
                "foobar",
                "-b", "toast"};

        CommandLine cl = parser.parse(options, args, true);
        assertTrue(cl.hasOption("c"), "Confirm -c is set");
        assertTrue(cl.getArgList().size() == 3, "Confirm  3 extra args: " + cl.getArgList().size());

        cl = parser.parse(options, cl.getArgs());

        assertTrue(!cl.hasOption("c"), "Confirm -c is not set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.getOptionValue("b").equals("toast"), "Confirm arg of -b");
        assertTrue(cl.getArgList().size() == 1, "Confirm  1 extra arg: " + cl.getArgList().size());
        assertTrue(cl.getArgList().get(0).equals("foobar"), "Confirm  value of extra arg: " + cl.getArgList().get(0));
    }

    @Test
    public void testMultipleWithLong() throws Exception
    {
        final String[] args = new String[]{"--copt",
                "foobar",
                "--bfile", "toast"};

        CommandLine cl = parser.parse(options, args, true);
        assertTrue(cl.hasOption("c"), "Confirm -c is set");
        assertTrue(cl.getArgList().size() == 3, "Confirm  3 extra args: " + cl.getArgList().size());

        cl = parser.parse(options, cl.getArgs());

        assertTrue(!cl.hasOption("c"), "Confirm -c is not set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.getOptionValue("b").equals("toast"), "Confirm arg of -b");
        assertTrue(cl.getArgList().size() == 1, "Confirm  1 extra arg: " + cl.getArgList().size());
        assertTrue(cl.getArgList().get(0).equals("foobar"), "Confirm  value of extra arg: " + cl.getArgList().get(0));
    }

    @Test
    public void testUnrecognizedOption() throws Exception
    {
        final String[] args = new String[]{"-a", "-d", "-b", "toast", "foo", "bar"};

        try {
            parser.parse(options, args);
            fail("UnrecognizedOptionException wasn't thrown");
        } catch (final UnrecognizedOptionException e) {
            assertEquals("-d", e.getOption());
        }
    }

    @Test
    public void testMissingArg() throws Exception
    {
        final String[] args = new String[]{"-b"};

        boolean caught = false;

        try {
            parser.parse(options, args);
        } catch (final MissingArgumentException e) {
            caught = true;
            assertEquals("option missing an argument", "b", e.getOption().getOpt());
        }

        assertTrue(caught, "Confirm MissingArgumentException caught");
    }

    @Test
    public void testDoubleDash1() throws Exception
    {
        final String[] args = new String[]{"--copt",
                "--",
                "-b", "toast"};

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("c"), "Confirm -c is set");
        assertTrue(!cl.hasOption("b"), "Confirm -b is not set");
        assertTrue(cl.getArgList().size() == 2, "Confirm 2 extra args: " + cl.getArgList().size());
    }

    @Test
    public void testDoubleDash2() throws Exception
    {
        final Options options = new Options();
        options.addOption(Option.builder("n").hasArg().build());
        options.addOption(OptionBuilder.hasArg().create('n'));
        options.addOption(OptionBuilder.create('m'));

        try {
            parser.parse(options, new String[]{"-n", "--", "-m"});
            fail("MissingArgumentException not thrown for option -n");
        } catch (final MissingArgumentException e) {
            assertNotNull(e.getOption(), "option null");
            assertEquals("n", e.getOption().getOpt());
        }
    }

    @Test
    public void testSingleDash() throws Exception
    {
        final String[] args = new String[]{"--copt",
                "-b", "-",
                "-a",
                "-"};

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("a"), "Confirm -a is set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.getOptionValue("b").equals("-"), "Confirm arg of -b");
        assertTrue(cl.getArgList().size() == 1, "Confirm 1 extra arg: " + cl.getArgList().size());
        assertTrue(cl.getArgList().get(0).equals("-"), "Confirm value of extra arg: " + cl.getArgList().get(0));
    }

    @Test
    public void testStopAtUnexpectedArg() throws Exception
    {
        final String[] args = new String[]{"-c",
                "foober",
                "-b",
                "toast"};

        final CommandLine cl = parser.parse(options, args, true);
        assertTrue(cl.hasOption("c"), "Confirm -c is set");
        assertTrue(cl.getArgList().size() == 3, "Confirm  3 extra args: " + cl.getArgList().size());
    }

    @Test
    public void testStopAtExpectedArg() throws Exception
    {
        final String[] args = new String[]{"-b", "foo"};

        final CommandLine cl = parser.parse(options, args, true);

        assertTrue(cl.hasOption('b'), "Confirm -b is set");
        assertEquals("foo", cl.getOptionValue('b'), "Confirm -b is set");
        assertTrue(cl.getArgList().size() == 0, "Confirm no extra args: " + cl.getArgList().size());
    }

    @Test
    public void testStopAtNonOptionShort() throws Exception
    {
        final String[] args = new String[]{"-z",
                "-a",
                "-btoast"};

        final CommandLine cl = parser.parse(options, args, true);
        assertFalse(cl.hasOption("a"), "Confirm -a is not set");
        assertTrue(cl.getArgList().size() == 3, "Confirm  3 extra args: " + cl.getArgList().size());
    }

    @Test
    public void testStopAtNonOptionLong() throws Exception
    {
        final String[] args = new String[]{"--zop==1",
                "-abtoast",
                "--b=bar"};

        final CommandLine cl = parser.parse(options, args, true);

        assertFalse(cl.hasOption("a"), "Confirm -a is not set");
        assertFalse(cl.hasOption("b"), "Confirm -b is not set");
        assertTrue(cl.getArgList().size() == 3, "Confirm  3 extra args: " + cl.getArgList().size());
    }

    @Test
    public void testNegativeArgument() throws Exception
    {
        final String[] args = new String[]{"-b", "-1"};

        final CommandLine cl = parser.parse(options, args);
        assertEquals("-1", cl.getOptionValue("b"));
    }

    @Test
    public void testNegativeOption() throws Exception
    {
        final String[] args = new String[]{"-b", "-1"};

        options.addOption("1", false, null);

        final CommandLine cl = parser.parse(options, args);
        assertEquals("-1", cl.getOptionValue("b"));
    }

    @Test
    public void testArgumentStartingWithHyphen() throws Exception
    {
        final String[] args = new String[]{"-b", "-foo"};

        final CommandLine cl = parser.parse(options, args);
        assertEquals("-foo", cl.getOptionValue("b"));
    }

    @Test
    public void testShortWithEqual() throws Exception
    {
        final String[] args = new String[]{"-f=bar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasArg().create('f'));

        final CommandLine cl = parser.parse(options, args);

        assertEquals("bar", cl.getOptionValue("foo"));
    }

    @Test
    public void testShortWithoutEqual() throws Exception
    {
        final String[] args = new String[]{"-fbar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasArg().create('f'));

        final CommandLine cl = parser.parse(options, args);

        assertEquals("bar", cl.getOptionValue("foo"));
    }

    @Test
    public void testLongWithEqualDoubleDash() throws Exception
    {
        final String[] args = new String[]{"--foo=bar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasArg().create('f'));

        final CommandLine cl = parser.parse(options, args);

        assertEquals("bar", cl.getOptionValue("foo"));
    }

    @Test
    public void testLongWithEqualSingleDash() throws Exception
    {
        final String[] args = new String[]{"-foo=bar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasArg().create('f'));

        final CommandLine cl = parser.parse(options, args);

        assertEquals("bar", cl.getOptionValue("foo"));
    }

    @Test
    public void testLongWithoutEqualSingleDash() throws Exception
    {
        final String[] args = new String[]{"-foobar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasArg().create('f'));

        final CommandLine cl = parser.parse(options, args);

        assertEquals("bar", cl.getOptionValue("foo"));
    }

    @Test
    public void testAmbiguousLongWithoutEqualSingleDash() throws Exception
    {
        final String[] args = new String[]{"-b", "-foobar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasOptionalArg().create('f'));
        options.addOption(OptionBuilder.withLongOpt("bar").hasOptionalArg().create('b'));

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("b"));
        assertTrue(cl.hasOption("f"));
        assertEquals("bar", cl.getOptionValue("foo"));
    }

    @Test
    public void testLongWithoutEqualDoubleDash() throws Exception
    {
        final String[] args = new String[]{"--foobar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").hasArg().create('f'));

        final CommandLine cl = parser.parse(options, args, true);

        assertFalse(cl.hasOption("foo")); // foo isn't expected to be recognized with a double dash
    }

    @Test
    public void testLongWithUnexpectedArgument1() throws Exception
    {
        final String[] args = new String[]{"--foo=bar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").create('f'));

        try {
            parser.parse(options, args);
        } catch (final UnrecognizedOptionException e) {
            assertEquals("--foo=bar", e.getOption());
            return;
        }

        fail("UnrecognizedOptionException not thrown");
    }

    @Test
    public void testLongWithUnexpectedArgument2() throws Exception
    {
        final String[] args = new String[]{"-foobar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").create('f'));

        try {
            parser.parse(options, args);
        } catch (final UnrecognizedOptionException e) {
            assertEquals("-foobar", e.getOption());
            return;
        }

        fail("UnrecognizedOptionException not thrown");
    }

    @Test
    public void testShortWithUnexpectedArgument() throws Exception
    {
        final String[] args = new String[]{"-f=bar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("foo").create('f'));

        try {
            parser.parse(options, args);
        } catch (final UnrecognizedOptionException e) {
            assertEquals("-f=bar", e.getOption());
            return;
        }

        fail("UnrecognizedOptionException not thrown");
    }

    @Test
    public void testPropertiesOption1() throws Exception
    {
        final String[] args = new String[]{"-Jsource=1.5", "-J", "target", "1.5", "foo"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withValueSeparator().hasArgs(2).create('J'));

        final CommandLine cl = parser.parse(options, args);

        final List<String> values = Arrays.asList(cl.getOptionValues("J"));
        assertNotNull(values, "null values");
        assertEquals(4, values.size(), "number of values");
        assertEquals("value 1", "source", values.get(0));
        assertEquals("value 2", "1.5", values.get(1));
        assertEquals("value 3", "target", values.get(2));
        assertEquals("value 4", "1.5", values.get(3));

        final List<?> argsleft = cl.getArgList();
        assertEquals(1, argsleft.size(), "Should be 1 arg left");
        assertEquals("foo", argsleft.get(0), "Expecting foo");
    }

    @Test
    public void testPropertiesOption2() throws Exception
    {
        final String[] args = new String[]{"-Dparam1", "-Dparam2=value2", "-D"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withValueSeparator().hasOptionalArgs(2).create('D'));

        final CommandLine cl = parser.parse(options, args);

        final Properties props = cl.getOptionProperties("D");
        assertNotNull(props, "null properties");
        assertEquals(2, props.size(), "number of properties in " + props);
        assertEquals("property 1", "true", props.getProperty("param1"));
        assertEquals("property 2", "value2", props.getProperty("param2"));

        final List<?> argsleft = cl.getArgList();
        assertEquals(0, argsleft.size(), "Should be no arg left");
    }

    @Test
    public void testUnambiguousPartialLongOption1() throws Exception
    {
        final String[] args = new String[]{"--ver"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("version").create());
        options.addOption(OptionBuilder.withLongOpt("help").create());

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("version"), "Confirm --version is set");
    }

    @Test
    public void testUnambiguousPartialLongOption2() throws Exception
    {
        final String[] args = new String[]{"-ver"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("version").create());
        options.addOption(OptionBuilder.withLongOpt("help").create());

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("version"), "Confirm --version is set");
    }

    @Test
    public void testUnambiguousPartialLongOption3() throws Exception
    {
        final String[] args = new String[]{"--ver=1"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("verbose").hasOptionalArg().create());
        options.addOption(OptionBuilder.withLongOpt("help").create());

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("verbose"), "Confirm --verbose is set");
        assertEquals("1", cl.getOptionValue("verbose"));
    }

    @Test
    public void testUnambiguousPartialLongOption4() throws Exception
    {
        final String[] args = new String[]{"-ver=1"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("verbose").hasOptionalArg().create());
        options.addOption(OptionBuilder.withLongOpt("help").create());

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("verbose"), "Confirm --verbose is set");
        assertEquals("1", cl.getOptionValue("verbose"));
    }

    @Test
    public void testAmbiguousPartialLongOption1() throws Exception
    {
        final String[] args = new String[]{"--ver"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("version").create());
        options.addOption(OptionBuilder.withLongOpt("verbose").create());

        boolean caught = false;

        try {
            parser.parse(options, args);
        } catch (final AmbiguousOptionException e) {
            caught = true;
            assertEquals("--ver", e.getOption(), "Partial option");
            assertNotNull(e.getMatchingOptions(), "Matching options null");
            assertEquals(2, e.getMatchingOptions().size(), "Matching options size");
        }

        assertTrue(caught, "Confirm MissingArgumentException caught");
    }

    @Test
    public void testAmbiguousPartialLongOption2() throws Exception
    {
        final String[] args = new String[]{"-ver"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("version").create());
        options.addOption(OptionBuilder.withLongOpt("verbose").create());

        boolean caught = false;

        try {
            parser.parse(options, args);
        } catch (final AmbiguousOptionException e) {
            caught = true;
            assertEquals("Partial option", "-ver", e.getOption());
            assertNotNull(e.getMatchingOptions(), "Matching options null");
            assertEquals(2, e.getMatchingOptions().size(), "Matching options size");
        }

        assertTrue(caught, "Confirm MissingArgumentException caught");
    }

    @Test
    public void testAmbiguousPartialLongOption3() throws Exception
    {
        final String[] args = new String[]{"--ver=1"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("version").create());
        options.addOption(OptionBuilder.withLongOpt("verbose").hasOptionalArg().create());

        boolean caught = false;

        try {
            parser.parse(options, args);
        } catch (final AmbiguousOptionException e) {
            caught = true;
            assertEquals("Partial option", "--ver", e.getOption());
            assertNotNull(e.getMatchingOptions(), "Matching options null");
            assertEquals(2, e.getMatchingOptions().size(), "Matching options size");
        }

        assertTrue(caught, "Confirm MissingArgumentException caught");
    }

    @Test
    public void testAmbiguousPartialLongOption4() throws Exception
    {
        final String[] args = new String[]{"-ver=1"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("version").create());
        options.addOption(OptionBuilder.withLongOpt("verbose").hasOptionalArg().create());

        boolean caught = false;

        try {
            parser.parse(options, args);
        } catch (final AmbiguousOptionException e) {
            caught = true;
            assertEquals("Partial option", "-ver", e.getOption());
            assertNotNull(e.getMatchingOptions(), "Matching options null");
            assertEquals(2, e.getMatchingOptions().size(), "Matching options size");
        }

        assertTrue(caught, "Confirm MissingArgumentException caught");
    }

    @Test
    public void testPartialLongOptionSingleDash() throws Exception
    {
        final String[] args = new String[]{"-ver"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withLongOpt("version").create());
        options.addOption(OptionBuilder.hasArg().create('v'));

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("version"), "Confirm --version is set");
        assertTrue(!cl.hasOption("v"), "Confirm -v is not set");
    }

    @Test
    public void testWithRequiredOption() throws Exception
    {
        final String[] args = new String[]{"-b", "file"};

        final Options options = new Options();
        options.addOption("a", "enable-a", false, null);
        options.addOption(OptionBuilder.withLongOpt("bfile").hasArg().isRequired().create('b'));

        final CommandLine cl = parser.parse(options, args);

        assertTrue(!cl.hasOption("a"), "Confirm -a is NOT set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.getOptionValue("b").equals("file"), "Confirm arg of -b");
        assertTrue(cl.getArgList().size() == 0, "Confirm NO of extra args");
    }

    @Test
    public void testOptionAndRequiredOption() throws Exception
    {
        final String[] args = new String[]{"-a", "-b", "file"};

        final Options options = new Options();
        options.addOption("a", "enable-a", false, null);
        options.addOption(OptionBuilder.withLongOpt("bfile").hasArg().isRequired().create('b'));

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("a"), "Confirm -a is set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.getOptionValue("b").equals("file"), "Confirm arg of -b");
        assertTrue(cl.getArgList().size() == 0, "Confirm NO of extra args");
    }

    @Test
    public void testMissingRequiredOption()
    {
        final String[] args = new String[]{"-a"};

        final Options options = new Options();
        options.addOption("a", "enable-a", false, null);
        options.addOption(OptionBuilder.withLongOpt("bfile").hasArg().isRequired().create('b'));

        try {
            parser.parse(options, args);
            fail("exception should have been thrown");
        } catch (final MissingOptionException e) {
            assertEquals("Incorrect exception message", "Missing required option: b", e.getMessage());
            assertTrue(e.getMissingOptions().contains("b"));
        } catch (final ParseException e) {
            fail("expected to catch MissingOptionException");
        }
    }

    @Test
    public void testMissingRequiredOptions()
    {
        final String[] args = new String[]{"-a"};

        final Options options = new Options();
        options.addOption("a", "enable-a", false, null);
        options.addOption(OptionBuilder.withLongOpt("bfile").hasArg().isRequired().create('b'));
        options.addOption(OptionBuilder.withLongOpt("cfile").hasArg().isRequired().create('c'));

        try {
            parser.parse(options, args);
            fail("exception should have been thrown");
        } catch (final MissingOptionException e) {
            assertEquals("Incorrect exception message", "Missing required options: b, c", e.getMessage());
            assertTrue(e.getMissingOptions().contains("b"));
            assertTrue(e.getMissingOptions().contains("c"));
        } catch (final ParseException e) {
            fail("expected to catch MissingOptionException");
        }
    }

    @Test
    public void testMissingRequiredGroup() throws Exception
    {
        final OptionGroup group = new OptionGroup();
        group.addOption(OptionBuilder.create("a"));
        group.addOption(OptionBuilder.create("b"));
        group.setRequired(true);

        final Options options = new Options();
        options.addOptionGroup(group);
        options.addOption(OptionBuilder.isRequired().create("c"));

        try {
            parser.parse(options, new String[]{"-c"});
            fail("MissingOptionException not thrown");
        } catch (final MissingOptionException e) {
            assertEquals(1, e.getMissingOptions().size());
            assertTrue(e.getMissingOptions().get(0) instanceof OptionGroup);
        } catch (final ParseException e) {
            fail("Expected to catch MissingOptionException");
        }
    }

    @Test
    public void testOptionGroup() throws Exception
    {
        final OptionGroup group = new OptionGroup();
        group.addOption(OptionBuilder.create("a"));
        group.addOption(OptionBuilder.create("b"));

        final Options options = new Options();
        options.addOptionGroup(group);

        parser.parse(options, new String[]{"-b"});

        assertEquals("selected option", "b", group.getSelected());
    }

    @Test
    public void testOptionGroupLong() throws Exception
    {
        final OptionGroup group = new OptionGroup();
        group.addOption(OptionBuilder.withLongOpt("foo").create());
        group.addOption(OptionBuilder.withLongOpt("bar").create());

        final Options options = new Options();
        options.addOptionGroup(group);

        final CommandLine cl = parser.parse(options, new String[]{"--bar"});

        assertTrue(cl.hasOption("bar"));
        assertEquals("selected option", "bar", group.getSelected());
    }

    @Test
    public void testReuseOptionsTwice() throws Exception
    {
        final Options opts = new Options();
        opts.addOption(OptionBuilder.isRequired().create('v'));

        // first parsing
        parser.parse(opts, new String[]{"-v"});

        try {
            // second parsing, with the same Options instance and an invalid command line
            parser.parse(opts, new String[0]);
            fail("MissingOptionException not thrown");
        } catch (final MissingOptionException e) {
            // expected
        }
    }

    @Test
    public void testBursting() throws Exception
    {
        final String[] args = new String[]{"-acbtoast", "foo", "bar"};

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("a"), "Confirm -a is set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.hasOption("c"), "Confirm -c is set");
        assertTrue(cl.getOptionValue("b").equals("toast"), "Confirm arg of -b");
        assertTrue(cl.getArgList().size() == 2, "Confirm size of extra args");
    }

    @Test
    public void testUnrecognizedOptionWithBursting() throws Exception
    {
        final String[] args = new String[]{"-adbtoast", "foo", "bar"};

        try {
            parser.parse(options, args);
            fail("UnrecognizedOptionException wasn't thrown");
        } catch (final UnrecognizedOptionException e) {
            assertEquals("-adbtoast", e.getOption());
        }
    }

    @Test
    public void testMissingArgWithBursting() throws Exception
    {
        final String[] args = new String[]{"-acb"};

        boolean caught = false;

        try {
            parser.parse(options, args);
        } catch (final MissingArgumentException e) {
            caught = true;
            assertEquals("option missing an argument", "b", e.getOption().getOpt());
        }

        assertTrue(caught, "Confirm MissingArgumentException caught");
    }

    @Test
    public void testStopBursting() throws Exception
    {
        final String[] args = new String[]{"-azc"};

        final CommandLine cl = parser.parse(options, args, true);
        assertTrue(cl.hasOption("a"), "Confirm -a is set");
        assertFalse(cl.hasOption("c"), "Confirm -c is not set");

        assertTrue(cl.getArgList().size() == 1, "Confirm  1 extra arg: " + cl.getArgList().size());
        assertTrue(cl.getArgList().contains("zc"));
    }

    @Test
    public void testStopBursting2() throws Exception
    {
        final String[] args = new String[]{"-c", "foobar", "-btoast"};

        CommandLine cl = parser.parse(options, args, true);
        assertTrue(cl.hasOption("c"), "Confirm -c is set");
        assertTrue(cl.getArgList().size() == 2, "Confirm  2 extra args: " + cl.getArgList().size());

        cl = parser.parse(options, cl.getArgs());

        assertTrue(!cl.hasOption("c"), "Confirm -c is not set");
        assertTrue(cl.hasOption("b"), "Confirm -b is set");
        assertTrue(cl.getOptionValue("b").equals("toast"), "Confirm arg of -b");
        assertTrue(cl.getArgList().size() == 1, "Confirm  1 extra arg: " + cl.getArgList().size());
        assertTrue(cl.getArgList().get(0).equals("foobar"), "Confirm  value of extra arg: " + cl.getArgList().get(0));
    }

    @Test
    public void testUnlimitedArgs() throws Exception
    {
        final String[] args = new String[]{"-e", "one", "two", "-f", "alpha"};

        final Options options = new Options();
        options.addOption(OptionBuilder.hasArgs().create("e"));
        options.addOption(OptionBuilder.hasArgs().create("f"));

        final CommandLine cl = parser.parse(options, args);

        assertTrue(cl.hasOption("e"), "Confirm -e is set");
        assertEquals(2, cl.getOptionValues("e").length, "number of arg for -e");
        assertTrue(cl.hasOption("f"), "Confirm -f is set");
        assertEquals(1, cl.getOptionValues("f").length, "number of arg for -f");
    }

    @SuppressWarnings("deprecation")
    private CommandLine parse(final CommandLineParser parser, final Options opts, final String[] args, final Properties properties) throws ParseException
    {
        if (parser instanceof Parser) {
            return ((Parser) parser).parse(opts, args, properties);
        } else if (parser instanceof DefaultParser) {
            return ((DefaultParser) parser).parse(opts, args, properties);
        } else {
            throw new UnsupportedOperationException("Default options not supported by this parser");
        }
    }

    @Test
    public void testPropertyOptionSingularValue() throws Exception
    {
        final Options opts = new Options();
        opts.addOption(OptionBuilder.hasOptionalArgs(2).withLongOpt("hide").create());

        final Properties properties = new Properties();
        properties.setProperty("hide", "seek");

        final CommandLine cmd = parse(parser, opts, null, properties);
        assertTrue(cmd.hasOption("hide"));
        assertEquals("seek", cmd.getOptionValue("hide"));
        assertTrue(!cmd.hasOption("fake"));
    }

    @Test
    public void testPropertyOptionFlags() throws Exception
    {
        final Options opts = new Options();
        opts.addOption("a", false, "toggle -a");
        opts.addOption("c", "c", false, "toggle -c");
        opts.addOption(OptionBuilder.hasOptionalArg().create('e'));

        Properties properties = new Properties();
        properties.setProperty("a", "true");
        properties.setProperty("c", "yes");
        properties.setProperty("e", "1");

        CommandLine cmd = parse(parser, opts, null, properties);
        assertTrue(cmd.hasOption("a"));
        assertTrue(cmd.hasOption("c"));
        assertTrue(cmd.hasOption("e"));


        properties = new Properties();
        properties.setProperty("a", "false");
        properties.setProperty("c", "no");
        properties.setProperty("e", "0");

        cmd = parse(parser, opts, null, properties);
        assertTrue(!cmd.hasOption("a"));
        assertTrue(!cmd.hasOption("c"));
        assertTrue(cmd.hasOption("e")); // this option accepts an argument


        properties = new Properties();
        properties.setProperty("a", "TRUE");
        properties.setProperty("c", "nO");
        properties.setProperty("e", "TrUe");

        cmd = parse(parser, opts, null, properties);
        assertTrue(cmd.hasOption("a"));
        assertTrue(!cmd.hasOption("c"));
        assertTrue(cmd.hasOption("e"));


        properties = new Properties();
        properties.setProperty("a", "just a string");
        properties.setProperty("e", "");

        cmd = parse(parser, opts, null, properties);
        assertTrue(!cmd.hasOption("a"));
        assertTrue(!cmd.hasOption("c"));
        assertTrue(cmd.hasOption("e"));


        properties = new Properties();
        properties.setProperty("a", "0");
        properties.setProperty("c", "1");

        cmd = parse(parser, opts, null, properties);
        assertTrue(!cmd.hasOption("a"));
        assertTrue(cmd.hasOption("c"));
    }

    @Test
    public void testPropertyOptionMultipleValues() throws Exception
    {
        final Options opts = new Options();
        opts.addOption(OptionBuilder.hasArgs().withValueSeparator(',').create('k'));

        final Properties properties = new Properties();
        properties.setProperty("k", "one,two");

        final String[] values = new String[]{"one", "two"};

        final CommandLine cmd = parse(parser, opts, null, properties);
        assertTrue(cmd.hasOption("k"));
        assertTrue(Arrays.equals(values, cmd.getOptionValues('k')));
    }

    @Test
    public void testPropertyOverrideValues() throws Exception
    {
        final Options opts = new Options();
        opts.addOption(OptionBuilder.hasOptionalArgs(2).create('i'));
        opts.addOption(OptionBuilder.hasOptionalArgs().create('j'));

        final String[] args = new String[]{"-j", "found", "-i", "ink"};

        final Properties properties = new Properties();
        properties.setProperty("j", "seek");

        final CommandLine cmd = parse(parser, opts, args, properties);
        assertTrue(cmd.hasOption("j"));
        assertEquals("found", cmd.getOptionValue("j"));
        assertTrue(cmd.hasOption("i"));
        assertEquals("ink", cmd.getOptionValue("i"));
        assertTrue(!cmd.hasOption("fake"));
    }

    @Test
    public void testPropertyOptionRequired() throws Exception
    {
        final Options opts = new Options();
        opts.addOption(OptionBuilder.isRequired().create("f"));

        final Properties properties = new Properties();
        properties.setProperty("f", "true");

        final CommandLine cmd = parse(parser, opts, null, properties);
        assertTrue(cmd.hasOption("f"));
    }

    @Test
    public void testPropertyOptionUnexpected() throws Exception
    {
        final Options opts = new Options();

        final Properties properties = new Properties();
        properties.setProperty("f", "true");

        try {
            parse(parser, opts, null, properties);
            fail("UnrecognizedOptionException expected");
        } catch (final UnrecognizedOptionException e) {
            // expected
        }
    }

    @Test
    public void testPropertyOptionGroup() throws Exception
    {
        final Options opts = new Options();

        final OptionGroup group1 = new OptionGroup();
        group1.addOption(new Option("a", null));
        group1.addOption(new Option("b", null));
        opts.addOptionGroup(group1);

        final OptionGroup group2 = new OptionGroup();
        group2.addOption(new Option("x", null));
        group2.addOption(new Option("y", null));
        opts.addOptionGroup(group2);

        final String[] args = new String[]{"-a"};

        final Properties properties = new Properties();
        properties.put("b", "true");
        properties.put("x", "true");

        final CommandLine cmd = parse(parser, opts, args, properties);

        assertTrue(cmd.hasOption("a"));
        assertFalse(cmd.hasOption("b"));
        assertTrue(cmd.hasOption("x"));
        assertFalse(cmd.hasOption("y"));
    }

}
