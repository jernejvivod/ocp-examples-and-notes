package si.jernej.ocp.test.streams;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.functional.streams.reduce.ReduceExamples;

class ReduceTest
{
    @Test
    void testSumIntegersInStream()
    {
        Assertions.assertEquals(15, ReduceExamples.sumIntegersInStream(Stream.of(1, 2, 3, 4, 5)).orElseThrow());
    }

    @Test
    void testSumIntegersInStreamWithInitialAccumulatorValue()
    {
        Assertions.assertEquals(15, ReduceExamples.sumIntegersInStreamWithInitialAccumulatorValue(Stream.of(1, 2, 3, 4, 5), 0));
        Assertions.assertEquals(18, ReduceExamples.sumIntegersInStreamWithInitialAccumulatorValue(Stream.of(1, 2, 3, 4, 5), 3));
        Assertions.assertEquals(1, ReduceExamples.sumIntegersInStreamWithInitialAccumulatorValue(Stream.empty(), 1));
    }

    @Test
    void testPutIntegersInStreamToListUsingReduce()
    {
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5), ReduceExamples.putIntegersInStreamToListUsingReduce(Stream.of(1, 2, 3, 4, 5)));
    }
}
