package si.jernej.ocp.textblocks;

public class TextBlocksExamples
{

    // Same as "Test"
    public static final String EXAMPLE_1 = """
            Test""";

    // Ends with a newline
    public static final String EXAMPLE_2 = """
            Test
            """;

    // Note that indentation is taken from the leftmost element
    public static final String EXAMPLE_3 = """
            Test
             Test
            """;

    // We do not need to escape single quotes. We need to escape triple quotes (the first quote in the sequence).
    public static final String EXAMPLE_4 = """
            This "is a test" \""" test
            """;

    // All newlines are represented as \n. If we want to have a \n\r newline, we have to explicitly append the '\r'.
    public static final String EXAMPLE_5 = """
            This is a test\r
            And this is also a test""";

    // We can prevent a newline with an escape ('\')
    public static final String EXAMPLE_6 = """
            This is a \
            test""";

    // Trailing whitespace at the end of a line is ignored
    public static final String EXAMPLE_7 = """
            This is a test   """;

    // We can explicitly add trailing whitespace using '\s'.
    public static final String EXAMPLE_8 = """
            This is a test\s""";

    // Whitespace preceding '\s' is preserved
    public static final String EXAMPLE_9 = """
            This is a test  \s""";

    // We have the method String#formatted(Object...):
    public static final String EXAMPLE_10 = """
            This is a %s""".formatted("test");
}
