package si.jernej.ocp.test.streams;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.functional.streams.IntStreamExamples;

class TestIntStreams
{
    @Test
    void testIntStreamToDoubleStream()
    {
        DoubleStream res = IntStreamExamples.intStreamToDoubleStream(IntStream.of(1, 2, 3));
        Assertions.assertArrayEquals(new double[] { 1.0, 2.0, 3.0 }, res.toArray());
    }

    @Test
    void testIntStreamToLongStream()
    {
        LongStream res = IntStreamExamples.intStreamToLongStream(IntStream.of(1, 2, 3));
        Assertions.assertArrayEquals(new long[] { 1L, 2L, 3L }, res.toArray());
    }

    @Test
    void testIntStreamAverageValue()
    {
        Assertions.assertEquals(2.0, IntStreamExamples.intStreamAverageValue(IntStream.of(1, 2, 3)).orElseThrow());
    }

    @Test
    void testIntStreamToString()
    {
        Assertions.assertEquals("1 2 3", IntStreamExamples.intStreamToString(IntStream.of(1, 2, 3)));
    }

    @Test
    void testIntStreamToStreamOfA()
    {
        Stream<IntStreamExamples.A> res = IntStreamExamples.intStreamToStreamOfA(IntStream.of(1, 2, 3));

        List<IntStreamExamples.A> resList = res.toList();
        Assertions.assertEquals(1, resList.get(0).a());
        Assertions.assertEquals(2, resList.get(1).a());
        Assertions.assertEquals(3, resList.get(2).a());
    }

    @Test
    void testIntStreamOnRangeOpen()
    {
        Assertions.assertArrayEquals(new int[] { 0, 1, 2 }, IntStreamExamples.intStreamOnRangeOpen(0, 3).toArray());
    }

    @Test
    void testIntStreamOnRangeClosed()
    {
        Assertions.assertArrayEquals(new int[] { 0, 1, 2, 3 }, IntStreamExamples.intStreamOnRangeClosed(0, 3).toArray());
    }

    @Test
    void testGetSummaryStatisticsForIntStream()
    {
        IntSummaryStatistics res = IntStreamExamples.getSummaryStatisticsForIntStream(IntStream.of(1, 2, 3));
        Assertions.assertNotNull(res);
    }
}
