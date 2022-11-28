package si.jernej.ocp.functional.streams;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class StreamsExamples
{
    private StreamsExamples()
    {
    }

    public static <T> Stream<T> getStreamOfDistinctElements(Stream<T> stream)
    {
        return stream.distinct();
    }

    public static <T> Stream<T> getStreamWithLimitedElements(Stream<T> stream, long limit)
    {
        return stream.limit(limit);
    }

    public static <T> Stream<T> getStreamWithSkippedElements(Stream<T> stream, long skip)
    {
        return stream.skip(skip);
    }

    public static <T> Stream<T> getStreamWithElementsUpUntilPredicateFalse(Stream<T> stream, Predicate<T> predicate)
    {
        return stream.takeWhile(predicate);
    }

    public static <T> Stream<T> getStreamWithElementsFromUntilPredicateFalse(Stream<T> stream, Predicate<T> predicate)
    {
        return stream.dropWhile(predicate);
    }

    public static <T> Object[] collectStreamToArray(Stream<T> stream)
    {
        return stream.toArray(Object[]::new);
    }

    public static <T> Optional<T> getMinElementFromStream(Stream<T> stream, Comparator<T> comparator)
    {
        return stream.min(comparator);
    }

    public static <T> Optional<T> getMaxElementFromStream(Stream<T> stream, Comparator<T> comparator)
    {
        return stream.max(comparator);
    }

    public static long countElementsInStream(Stream<?> stream)
    {
        return stream.count();
    }

    public static <T> Stream<T> getNullableStreamOfElement(T arg)
    {
        return Stream.ofNullable(arg);
    }

    public static <T> Stream<T> getStreamFromSeedAndUnaryOperator(T seed, UnaryOperator<T> operator)
    {
        return Stream.iterate(seed, operator);
    }

    public static <T> Stream<T> getStreamFromSeedPredicateAndUnaryOperator(T seed, Predicate<T> predicate, UnaryOperator<T> operator)
    {
        return Stream.iterate(seed, predicate, operator);
    }

    public static <T> Stream<T> getStreamFromSupplier(Supplier<T> supplier)
    {
        return Stream.generate(supplier);
    }

}
