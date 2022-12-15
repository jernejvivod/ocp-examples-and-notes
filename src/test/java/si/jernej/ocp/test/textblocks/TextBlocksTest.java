package si.jernej.ocp.test.textblocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.textblocks.TextBlocksExamples;

class TextBlocksTest
{
    @Test
    void testSimple()
    {
        Assertions.assertEquals("Test", TextBlocksExamples.EXAMPLE_1);
        Assertions.assertEquals("Test\n", TextBlocksExamples.EXAMPLE_2);
    }

    @Test
    void testIndentation()
    {
        Assertions.assertEquals("Test\n Test\n", TextBlocksExamples.EXAMPLE_3);
    }

    @Test
    void testEscapedQuotes()
    {
       Assertions.assertEquals("This \"is a test\" \"\"\" test\n", TextBlocksExamples.EXAMPLE_4);
    }

    @Test
    void testNewlineCharacters()
    {
        Assertions.assertEquals("This is a test\r\nAnd this is also a test", TextBlocksExamples.EXAMPLE_5);
    }

    @Test
    void testEscapedNewline()
    {
       Assertions.assertEquals("This is a test", TextBlocksExamples.EXAMPLE_6);
    }

    @Test
    void textTrailingWhitespace()
    {
        Assertions.assertEquals("This is a test", TextBlocksExamples.EXAMPLE_7);
    }

    @Test
    void testEscapedTrailingWhitespace()
    {
        Assertions.assertEquals("This is a test ", TextBlocksExamples.EXAMPLE_8);
        Assertions.assertEquals("This is a test   ", TextBlocksExamples.EXAMPLE_9);
    }

    @Test
    void testFormatting()
    {
        Assertions.assertEquals("This is a test", TextBlocksExamples.EXAMPLE_10);
    }

}
