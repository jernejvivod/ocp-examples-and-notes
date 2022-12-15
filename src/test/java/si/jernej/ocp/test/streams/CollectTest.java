package si.jernej.ocp.test.streams;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.functional.streams.collect.CollectExamples;
import si.jernej.ocp.functional.streams.collect.ProvidedCollectorsExamples;

class CollectTest
{
    // CollectExamples

    @Test
    void testCollectArgsIntoList()
    {
        List<Integer> integersList = CollectExamples.collectArgsIntoList(1, 2, 3);
        Assertions.assertEquals(List.of(1, 2, 3), integersList);
    }

    @Test
    void testCustomCollectors()
    {
        int sumStream1 = Stream.of(1, 2, 3, 4, 5)
                .collect(CollectExamples.collector);
        Assertions.assertEquals(15, sumStream1);

        int sumStream2 = Stream.of(1, 2, 3, 4, 5)
                .collect(CollectExamples.collectorFromFactoryMethod);
        Assertions.assertEquals(15, sumStream2);
    }

    @Test
    void testJoinStringsInStream()
    {
        Assertions.assertEquals("this is a test", CollectExamples.joinStringsInStream(Stream.of("this", "is", "a", "test")));
    }

    // ProvidedCollectorsExamples

    @Test
    void testCountElementsInStream()
    {
        Assertions.assertEquals(3, ProvidedCollectorsExamples.countElementsInStream(Stream.of(1, 2, 3)));
    }

    @Test
    void testJoinElementsInStringStream()
    {
        Assertions.assertEquals("this is a test", ProvidedCollectorsExamples.joinElementsInStringStreamWithDelimiter(Stream.of("this", "is", "a", "test"), " "));
        Assertions.assertEquals("<<this is a test>>", ProvidedCollectorsExamples.joinElementsInStringStreamWithDelimiterAndPrefixSuffix(Stream.of("this", "is", "a", "test"), " ", "<<", ">>"));
    }

    @Test
    void testCollectStreamElementsToList()
    {
        Assertions.assertEquals(List.of(1, 2, 3), ProvidedCollectorsExamples.collectStreamElementsToList(Stream.of(1, 2, 3)));
    }

    @Test
    void testCollectStreamElementsToSet()
    {
        Assertions.assertEquals(Set.of(1, 2, 3), ProvidedCollectorsExamples.collectStreamElementsToSet(Stream.of(1, 2, 3)));
    }

    @Test
    void testCollectStreamElementsToCollection()
    {
        Assertions.assertEquals(Set.of(1, 2, 3), ProvidedCollectorsExamples.collectStreamElementsToCollection(Stream.of(1, 2, 3), new HashSet<>()));
    }

    @Test
    void testMaxBy()
    {
        Assertions.assertEquals(3, ProvidedCollectorsExamples.returnMaxStreamElement(Stream.of(3, 2, 1), Comparator.comparingInt(e -> e)).orElseThrow());
    }

    @Test
    void testMinBy()
    {
        Assertions.assertEquals(1, ProvidedCollectorsExamples.returnMinStreamElement(Stream.of(1, 2, 3), Comparator.comparingInt(e -> e)).orElseThrow());
    }

    @Test
    void testAverageDoubleValues()
    {
        double res = ProvidedCollectorsExamples.averageDoubleValues(
                Stream.of(new ProvidedCollectorsExamples.A(1.0), new ProvidedCollectorsExamples.A(2.0), new ProvidedCollectorsExamples.A(3.0))
        );

        Assertions.assertEquals(2, res);
    }

    @Test
    void testAverageIntValues()
    {
        double res = ProvidedCollectorsExamples.averageIntValues(
                Stream.of(new ProvidedCollectorsExamples.A(1), new ProvidedCollectorsExamples.A(2), new ProvidedCollectorsExamples.A(3))
        );

        Assertions.assertEquals(2, res);
    }

    @Test
    void testAverageLongValues()
    {
        double res = ProvidedCollectorsExamples.averageLongValues(
                Stream.of(new ProvidedCollectorsExamples.A(1L), new ProvidedCollectorsExamples.A(2L), new ProvidedCollectorsExamples.A(3L))
        );

        Assertions.assertEquals(2, res);
    }

    @Test
    void testCollectWithFinalize()
    {
        int res = ProvidedCollectorsExamples.collectWithFinalize(
                Stream.of(1, 2, 3),
                Collectors.toList(),
                l -> l.stream().mapToInt(e -> e).sum()
        );

        Assertions.assertEquals(6, res);
    }

    @Test
    void testGroupUsingClassificationFunction()
    {
        Map<Integer, List<ProvidedCollectorsExamples.B>> res = ProvidedCollectorsExamples.groupUsingClassificationFunction(
                Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(1, "bb"), new ProvidedCollectorsExamples.B(2, "cc"), new ProvidedCollectorsExamples.B(2, "dd")),
                ProvidedCollectorsExamples.B::getC
        );

        Assertions.assertEquals(2, res.get(1).size());
        Assertions.assertEquals(2, res.get(2).size());

        Assertions.assertTrue(res.get(1).stream().allMatch(b -> b.getC() == 1));
        Assertions.assertTrue(res.get(2).stream().allMatch(b -> b.getC() == 2));

        Assertions.assertEquals(Set.of("aa", "bb"), res.get(1).stream().map(ProvidedCollectorsExamples.B::getD).collect(Collectors.toSet()));
        Assertions.assertEquals(Set.of("cc", "dd"), res.get(2).stream().map(ProvidedCollectorsExamples.B::getD).collect(Collectors.toSet()));
    }

    @Test
    void testGroupUsingClassificationFunctionAndDownstreamCollector()
    {
        Map<Integer, Set<String>> res = ProvidedCollectorsExamples.groupUsingClassificationFunctionAndDownstreamCollector(
                Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(1, "bb"), new ProvidedCollectorsExamples.B(2, "cc"), new ProvidedCollectorsExamples.B(2, "dd")),
                ProvidedCollectorsExamples.B::getC,
                Collectors.mapping(ProvidedCollectorsExamples.B::getD, Collectors.toSet())
        );

        Assertions.assertEquals(2, res.get(1).size());
        Assertions.assertEquals(2, res.get(2).size());

        Assertions.assertEquals(Set.of("aa", "bb"), res.get(1));
        Assertions.assertEquals(Set.of("cc", "dd"), res.get(2));
    }

    @Test
    void testGroupUsingClassificationFunctionDownstreamCollectorAndMapSupplier()
    {
        Map<Integer, Set<String>> res = ProvidedCollectorsExamples.groupUsingClassificationFunctionDownstreamCollectorAndMapSupplier(
                Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(1, "bb"), new ProvidedCollectorsExamples.B(2, "cc"), new ProvidedCollectorsExamples.B(2, "dd")),
                ProvidedCollectorsExamples.B::getC,
                Collectors.mapping(ProvidedCollectorsExamples.B::getD, Collectors.toSet()),
                TreeMap::new
        );

        Assertions.assertEquals(2, res.get(1).size());
        Assertions.assertEquals(2, res.get(2).size());

        Assertions.assertEquals(Set.of("aa", "bb"), res.get(1));
        Assertions.assertEquals(Set.of("cc", "dd"), res.get(2));
    }

    @Test
    void testPartitionUsingPredicate()
    {
        Map<Boolean, List<ProvidedCollectorsExamples.B>> res = ProvidedCollectorsExamples.partitionUsingPredicate(
                Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(1, "bb"), new ProvidedCollectorsExamples.B(2, "cc"), new ProvidedCollectorsExamples.B(3, "dd")),
                e -> e.getC() > 1
        );

        Assertions.assertEquals(2, res.get(false).size());
        Assertions.assertEquals(2, res.get(true).size());

        Assertions.assertTrue(res.get(false).stream().allMatch(b -> b.getC() <= 1));
        Assertions.assertTrue(res.get(true).stream().allMatch(b -> b.getC() > 1));

        Assertions.assertEquals(Set.of("aa", "bb"), res.get(false).stream().map(ProvidedCollectorsExamples.B::getD).collect(Collectors.toSet()));
        Assertions.assertEquals(Set.of("cc", "dd"), res.get(true).stream().map(ProvidedCollectorsExamples.B::getD).collect(Collectors.toSet()));
    }

    @Test
    void testPartitionUsingPredicateAndDownstreamCollector()
    {
        Map<Boolean, Set<String>> res = ProvidedCollectorsExamples.partitionUsingPredicateAndDownstreamCollector(
                Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(1, "bb"), new ProvidedCollectorsExamples.B(2, "cc"), new ProvidedCollectorsExamples.B(3, "dd")),
                e -> e.getC() > 1,
                Collectors.mapping(ProvidedCollectorsExamples.B::getD, Collectors.toSet())
        );

        Assertions.assertEquals(2, res.get(false).size());
        Assertions.assertEquals(2, res.get(true).size());

        Assertions.assertEquals(Set.of("aa", "bb"), res.get(false));
        Assertions.assertEquals(Set.of("cc", "dd"), res.get(true));
    }

    @Test
    void testReduceUsingBinaryOperator()
    {
        Assertions.assertEquals(6, ProvidedCollectorsExamples.reduceUsingBinaryOperator(
                        Stream.of(1, 2, 3),
                        (a, b) -> a * b
                ).orElseThrow()
        );
    }

    @Test
    void testReduceUsingBinaryOperatorAndInitialAcumulatorValue()
    {
        Assertions.assertEquals(42, ProvidedCollectorsExamples.reduceUsingBinaryOperatorAndInitialAccumulatorValue(
                        Stream.of(1, 2, 3),
                        7,
                        (a, b) -> a * b
                )
        );
    }

    @Test
    void testReduceUsingBinaryOperatorInitialAcumulatorValueAndCombiner()
    {
        Assertions.assertEquals(42, ProvidedCollectorsExamples.reduceUsingBinaryOperatorInitialAccumulatorValueAndCombiner(
                        Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(1, "bb"), new ProvidedCollectorsExamples.B(2, "cc"), new ProvidedCollectorsExamples.B(3, "dd")),
                        7,
                        ProvidedCollectorsExamples.B::getC,
                        (a, b) -> a * b
                )
        );
    }

    @Test
    void testComputeDoubleSummaryStatistics()
    {
        DoubleSummaryStatistics doubleSummaryStatistics = ProvidedCollectorsExamples.computeDoubleSummaryStatistics(
                Stream.of(new ProvidedCollectorsExamples.A(1.0), new ProvidedCollectorsExamples.A(2.0), new ProvidedCollectorsExamples.A(3.0))
        );
        Assertions.assertNotNull(doubleSummaryStatistics);
    }

    @Test
    void testComputeIntSummaryStatistics()
    {
        IntSummaryStatistics intSummaryStatistics = ProvidedCollectorsExamples.computeIntSummaryStatistics(
                Stream.of(new ProvidedCollectorsExamples.A(1), new ProvidedCollectorsExamples.A(2), new ProvidedCollectorsExamples.A(3))
        );
        Assertions.assertNotNull(intSummaryStatistics);
    }

    @Test
    void testComputeLongSummaryStatistics()
    {
        LongSummaryStatistics longSummaryStatistics = ProvidedCollectorsExamples.computeLongSummaryStatistics(
                Stream.of(new ProvidedCollectorsExamples.A(1L), new ProvidedCollectorsExamples.A(2L), new ProvidedCollectorsExamples.A(3L))
        );
        Assertions.assertNotNull(longSummaryStatistics);
    }

    @Test
    void testSumDoubleValues()
    {
        double res = ProvidedCollectorsExamples.sumDoubleValues(
                Stream.of(new ProvidedCollectorsExamples.A(1.0), new ProvidedCollectorsExamples.A(2.0), new ProvidedCollectorsExamples.A(3.0))
        );

        Assertions.assertEquals(6.0, res);
    }

    @Test
    void testSumIntValues()
    {
        int res = ProvidedCollectorsExamples.sumIntValues(
                Stream.of(new ProvidedCollectorsExamples.A(1), new ProvidedCollectorsExamples.A(2), new ProvidedCollectorsExamples.A(3))
        );

        Assertions.assertEquals(6, res);
    }

    @Test
    void testSumLongValues()
    {
        long res = ProvidedCollectorsExamples.sumLongValues(
                Stream.of(new ProvidedCollectorsExamples.A(1L), new ProvidedCollectorsExamples.A(2L), new ProvidedCollectorsExamples.A(3L))
        );

        Assertions.assertEquals(6L, res);
    }

    @Test
    void testPutStreamElementsToMapWithNoDuplicatesAllowed()
    {
        Map<Integer, String> res = ProvidedCollectorsExamples.putStreamElementsToMapWithNoDuplicatesAllowed(
                Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(2, "bb"), new ProvidedCollectorsExamples.B(3, "cc"), new ProvidedCollectorsExamples.B(4, "dd"))
        );

        Assertions.assertEquals(4, res.keySet().size());
        Assertions.assertEquals(Set.of(1, 2, 3, 4), res.keySet());
        Assertions.assertEquals("aa", res.get(1));
        Assertions.assertEquals("bb", res.get(2));
        Assertions.assertEquals("cc", res.get(3));
        Assertions.assertEquals("dd", res.get(4));
    }

    @Test
    void testPutStreamElementsToMapWithBinaryOperatorForDuplicateValues()
    {
        Map<Integer, String> res = ProvidedCollectorsExamples.putStreamElementsToMapWithBinaryOperatorForDuplicateValues(
                Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(2, "bb"), new ProvidedCollectorsExamples.B(3, "cc"), new ProvidedCollectorsExamples.B(3, "dd")),
                "%s %s"::formatted
        );

        Assertions.assertEquals(3, res.keySet().size());
        Assertions.assertEquals(Set.of(1, 2, 3), res.keySet());
        Assertions.assertEquals("aa", res.get(1));
        Assertions.assertEquals("bb", res.get(2));
        Assertions.assertEquals("cc dd", res.get(3));
    }

    @Test
    void testPutStreamElementsToMapWithBinaryOperatorForDuplicateValuesAndMapSupplier()
    {
        Map<Integer, String> res = ProvidedCollectorsExamples.putStreamElementsToMapWithBinaryOperatorForDuplicateValuesAndMapSupplier(
                Stream.of(new ProvidedCollectorsExamples.B(1, "aa"), new ProvidedCollectorsExamples.B(2, "bb"), new ProvidedCollectorsExamples.B(3, "cc"), new ProvidedCollectorsExamples.B(3, "dd")),
                "%s %s"::formatted,
                TreeMap::new
        );

        Assertions.assertEquals(3, res.keySet().size());
        Assertions.assertEquals(Set.of(1, 2, 3), res.keySet());
        Assertions.assertEquals("aa", res.get(1));
        Assertions.assertEquals("bb", res.get(2));
        Assertions.assertEquals("cc dd", res.get(3));
    }
}
