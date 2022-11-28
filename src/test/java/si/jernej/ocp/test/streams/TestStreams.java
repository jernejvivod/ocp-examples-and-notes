package si.jernej.ocp.test.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.functional.streams.StreamsExamples;

class TestStreams
{
    @Test
    void testGetStreamOfDistinctElements()
    {
        record A(int a, String b)
        {
        }

        Stream<A> res = StreamsExamples.getStreamOfDistinctElements(Stream.of(new A(1, "a"), new A(1, "a"), new A(2, "b"), new A(2, "c"), new A(2, "c")));
        List<A> resList = res.toList();

        Assertions.assertEquals(3, resList.size());

        Assertions.assertEquals(1, resList.get(0).a());
        Assertions.assertEquals("a", resList.get(0).b());
        Assertions.assertEquals(2, resList.get(1).a());
        Assertions.assertEquals("b", resList.get(1).b());
        Assertions.assertEquals(2, resList.get(2).a());
        Assertions.assertEquals("c", resList.get(2).b());
    }

    @Test
    void testGetStreamWithLimitedElements()
    {
        Stream<Integer> res = StreamsExamples.getStreamWithLimitedElements(Stream.of(1, 2, 3, 4, 5), 3);
        List<Integer> resList = res.toList();
        Assertions.assertEquals(3, resList.size());
        Assertions.assertEquals(List.of(1, 2, 3), resList);
    }

    @Test
    void testGetStreamWithSkippedElements()
    {
        Stream<Integer> res = StreamsExamples.getStreamWithSkippedElements(Stream.of(1, 2, 3, 4, 5), 3);
        List<Integer> resList = res.toList();
        Assertions.assertEquals(2, resList.size());
        Assertions.assertEquals(List.of(4, 5), resList);
    }

    @Test
    void testGetStreamWithElementsUpUntilPredicateFalse()
    {
        Stream<Integer> res = StreamsExamples.getStreamWithElementsUpUntilPredicateFalse(
                Stream.of(1, 2, 3, 4),
                e -> e < 3
        );

        List<Integer> resList = res.toList();

        Assertions.assertEquals(2, resList.size());
        Assertions.assertEquals(List.of(1, 2), resList);
    }

    @Test
    void testGetStreamWithElementsFromUntilPredicateFalse()
    {
        Stream<Integer> res = StreamsExamples.getStreamWithElementsFromUntilPredicateFalse(
                Stream.of(1, 2, 3, 4),
                e -> e < 3
        );

        List<Integer> resList = res.toList();

        Assertions.assertEquals(2, resList.size());
        Assertions.assertEquals(List.of(3, 4), resList);
    }

    @Test
    void testCollectStreamToArray()
    {
        Object[] res = StreamsExamples.collectStreamToArray(Stream.of("aa", "bb", "cc"));
        Assertions.assertArrayEquals(new String[] { "aa", "bb", "cc" }, res);
    }

    @Test
    void testGetMinElementFromStream()
    {
        record A(int a, String b)
        {
        }

        Optional<A> res = StreamsExamples.getMinElementFromStream(Stream.of(new A(7, "aa"), new A(3, "bb"), new A(37, "zz")), Comparator.comparingInt(A::a));

        Assertions.assertEquals(new A(3, "bb"), res.orElseThrow());
    }

    @Test
    void testGetMaxElementFromStream()
    {
        record A(int a, String b)
        {
        }

        Optional<A> res = StreamsExamples.getMaxElementFromStream(Stream.of(new A(7, "aa"), new A(3, "bb"), new A(37, "zz")), Comparator.comparingInt(A::a));

        Assertions.assertEquals(new A(37, "zz"), res.orElseThrow());
    }

    @Test
    void testCountElementsInStream()
    {
        Assertions.assertEquals(3, StreamsExamples.countElementsInStream(Stream.of(1, 2, 3)));
    }

    @Test
    void testGetNullableStreamOfElement()
    {
        Stream<Object> res1 = StreamsExamples.getNullableStreamOfElement(null);
        Stream<Object> res2 = StreamsExamples.getNullableStreamOfElement(1);

        Assertions.assertEquals(0, res1.toList().size());
        Assertions.assertEquals(List.of(1), res2.toList());
    }

    @Test
    void testGetStreamFromSeedAndUnaryOperator()
    {
        List<Integer> resList = StreamsExamples.getStreamFromSeedAndUnaryOperator(1, x -> x + 1).takeWhile(e -> e < 10).toList();
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), resList);
    }

    @Test
    void testGetStreamFromSeedPredicateAndUnaryOperator()
    {
        List<Integer> resList = StreamsExamples.getStreamFromSeedPredicateAndUnaryOperator(1, x -> x < 10, x -> x + 1).toList();
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), resList);
    }

    @Test
    void testGetStreamFromSupplier()
    {
        class Incrementor
        {
            private int initialValue;

            public Incrementor(int initialValue)
            {
                this.initialValue = initialValue;
            }

            public int getNext()
            {
                return this.initialValue++;
            }
        }

        Incrementor incrementor = new Incrementor(1);
        List<Integer> resList = StreamsExamples.getStreamFromSupplier(incrementor::getNext).limit(10).toList();
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), resList);
    }
}
