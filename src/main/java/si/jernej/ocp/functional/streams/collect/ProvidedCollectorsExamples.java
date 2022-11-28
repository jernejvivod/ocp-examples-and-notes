package si.jernej.ocp.functional.streams.collect;

import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProvidedCollectorsExamples
{
    private ProvidedCollectorsExamples()
    {
    }

    public static long countElementsInStream(Stream<?> stream)
    {
        // can be replaced with the count() method of Stream instance
        return stream.collect(Collectors.counting());
    }

    public static String joinElementsInStringStreamWithDelimiter(Stream<String> stream, String delim)
    {
        return stream.collect(Collectors.joining(delim));
    }

    public static String joinElementsInStringStreamWithDelimiterAndPrefixSuffix(Stream<String> stream, String delim, String prefix, String suffix)
    {
        return stream.collect(Collectors.joining(delim, prefix, suffix));
    }

    public static <T> List<T> collectStreamElementsToList(Stream<T> stream)
    {
        // note code smell reported by SonarLint
        return stream.collect(Collectors.toList());
    }

    public static <T> Set<T> collectStreamElementsToSet(Stream<T> stream)
    {
        return stream.collect(Collectors.toSet());
    }

    public static <T extends Collection<S>, S> T collectStreamElementsToCollection(Stream<S> stream, T collection)
    {
        return stream.collect(Collectors.toCollection(() -> collection));
    }

    public static <T> Optional<T> returnMaxStreamElement(Stream<T> stream, Comparator<T> comparator)
    {
        return stream.collect(Collectors.maxBy(comparator));
    }

    public static <T> Optional<T> returnMinStreamElement(Stream<T> stream, Comparator<T> comparator)
    {
        return stream.collect(Collectors.minBy(comparator));
    }

    public static class A
    {
        private int b;
        private double c;
        private long d;

        public A(int b)
        {
            this.b = b;
        }

        public A(double c)
        {
            this.c = c;
        }

        public A(long d)
        {
            this.d = d;
        }

        public int getB()
        {
            return this.b;
        }

        public double getC()
        {
            return this.c;
        }

        public long getD()
        {
            return this.d;
        }

        @Override
        public String toString()
        {
            return "{a: %d, b: %f, c: %d}".formatted(this.b, this.c, this.d);
        }
    }

    public static double averageDoubleValues(Stream<A> stream)
    {
        return stream.collect(Collectors.averagingDouble(A::getC));
    }

    public static double averageIntValues(Stream<A> stream)
    {
        return stream.collect(Collectors.averagingInt(A::getB));
    }

    public static double averageLongValues(Stream<A> stream)
    {
        return stream.collect(Collectors.averagingLong(A::getD));
    }

    public static <T, A, R, F> F collectWithFinalize(Stream<T> stream, Collector<T, A, R> collector, Function<R, F> finisher)
    {
        return stream.collect(Collectors.collectingAndThen(collector, finisher));
    }

    public static class B
    {
        private final int c;
        private final String d;

        public B(int c, String d)
        {
            this.c = c;
            this.d = d;
        }

        public int getC()
        {
            return this.c;
        }

        public String getD()
        {
            return this.d;
        }

        @Override
        public String toString()
        {
            return "{a: %d, b: %s}".formatted(this.c, this.d);
        }
    }

    public static <T, S> Map<S, List<T>> groupUsingClassificationFunction(Stream<T> stream, Function<T, S> classifier)
    {
        return stream.collect(Collectors.groupingBy(classifier));
    }

    public static <T, S, A, R> Map<S, R> groupUsingClassificationFunctionAndDownstreamCollector(Stream<T> stream, Function<T, S> classifier, Collector<T, A, R> downstreamCollector)
    {
        return stream.collect(Collectors.groupingBy(classifier, downstreamCollector));
    }

    public static <T, S, A, R> Map<S, R> groupUsingClassificationFunctionDownstreamCollectorAndMapSupplier(Stream<T> stream, Function<T, S> classifier, Collector<T, A, R> downstreamCollector, Supplier<Map<S, R>> mapSupplier)
    {
        return stream.collect(Collectors.groupingBy(classifier, mapSupplier, downstreamCollector));
    }

    public static <T> Map<Boolean, List<T>> partitionUsingPredicate(Stream<T> stream, Predicate<T> predicate)
    {
        return stream.collect(Collectors.partitioningBy(predicate));
    }

    public static <T, A, R> Map<Boolean, R> partitionUsingPredicateAndDownstreamCollector(Stream<T> stream, Predicate<T> predicate, Collector<T, A, R> downstreamCollector)
    {
        return stream.collect(Collectors.partitioningBy(predicate, downstreamCollector));
    }

    public static <T> Optional<T> reduceUsingBinaryOperator(Stream<T> stream, BinaryOperator<T> binaryOperator)
    {
        return stream.collect(Collectors.reducing(binaryOperator));
    }

    public static <T> T reduceUsingBinaryOperatorAndInitialAccumulatorValue(Stream<T> stream, T initialAccumulatorValue, BinaryOperator<T> binaryOperator)
    {
        return stream.collect(Collectors.reducing(initialAccumulatorValue, binaryOperator));
    }

    public static <T, S> S reduceUsingBinaryOperatorInitialAccumulatorValueAndCombiner(Stream<T> stream, S initialAccumulatorValue, Function<T, S> mapper, BinaryOperator<S> combiner)
    {
        return stream.collect(Collectors.reducing(initialAccumulatorValue, mapper, combiner));
    }

    public static DoubleSummaryStatistics computeDoubleSummaryStatistics(Stream<A> stream)
    {
        return stream.collect(Collectors.summarizingDouble(A::getC));
    }

    public static IntSummaryStatistics computeIntSummaryStatistics(Stream<A> stream)
    {
        return stream.collect(Collectors.summarizingInt(A::getB));
    }

    public static LongSummaryStatistics computeLongSummaryStatistics(Stream<A> stream)
    {
        return stream.collect(Collectors.summarizingLong(A::getD));
    }

    public static double sumDoubleValues(Stream<A> stream)
    {
        return stream.collect(Collectors.summingDouble(A::getC));
    }

    public static int sumIntValues(Stream<A> stream)
    {
        return stream.collect(Collectors.summingInt(A::getB));
    }

    public static long sumLongValues(Stream<A> stream)
    {
        return stream.collect(Collectors.summingLong(A::getD));
    }

    public static Map<Integer, String> putStreamElementsToMapWithNoDuplicatesAllowed(Stream<B> stream)
    {
        return stream.collect(Collectors.toMap(B::getC, B::getD));
    }

    public static Map<Integer, String> putStreamElementsToMapWithBinaryOperatorForDuplicateValues(Stream<B> stream, BinaryOperator<String> binaryOperator)
    {
        return stream.collect(Collectors.toMap(B::getC, B::getD, binaryOperator));
    }

    public static Map<Integer, String> putStreamElementsToMapWithBinaryOperatorForDuplicateValuesAndMapSupplier(Stream<B> stream, BinaryOperator<String> binaryOperator, Supplier<Map<Integer, String>> supplier)
    {
        return stream.collect(Collectors.toMap(B::getC, B::getD, binaryOperator, supplier));
    }

}
