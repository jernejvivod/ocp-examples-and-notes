package si.jernej.ocp.functional.streams;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class IntStreamExamples
{
    public static DoubleStream intStreamToDoubleStream(IntStream stream)
    {
        return stream.asDoubleStream();
    }

    public static LongStream intStreamToLongStream(IntStream stream)
    {
        return stream.asLongStream();
    }

    public static OptionalDouble intStreamAverageValue(IntStream stream)
    {
        return stream.average();
    }

    public static String intStreamToString(IntStream stream)
    {
        return stream.boxed().map(String::valueOf).collect(Collectors.joining(" "));
    }

    public record A(int a)
    {
    }

    public static Stream<A> intStreamToStreamOfA(IntStream stream)
    {
        return stream.mapToObj(A::new);
    }

    public static IntStream intStreamOnRangeOpen(int startInclusive, int endExclusive)
    {
        return IntStream.range(startInclusive, endExclusive);
    }

    public static IntStream intStreamOnRangeClosed(int startInclusive, int endInclusive)
    {
        return IntStream.rangeClosed(startInclusive, endInclusive);
    }

    public static IntSummaryStatistics getSummaryStatisticsForIntStream(IntStream stream)
    {
        return stream.summaryStatistics();
    }
}
