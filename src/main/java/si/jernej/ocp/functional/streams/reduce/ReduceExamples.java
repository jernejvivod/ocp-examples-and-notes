package si.jernej.ocp.functional.streams.reduce;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ReduceExamples
{
    private ReduceExamples()
    {
    }

    public static Optional<Integer> sumIntegersInStream(Stream<Integer> stream)
    {
        return stream.reduce(Integer::sum);
    }

    public static Integer sumIntegersInStreamWithInitialAccumulatorValue(Stream<Integer> stream, int initialAccumulatorValue)
    {
        return stream.reduce(initialAccumulatorValue, Integer::sum);
    }

    public static List<Integer> putIntegersInStreamToListUsingReduce(Stream<Integer> stream)
    {
        // note the accumulator is of different type (we need a combiner)
        return stream.reduce(new LinkedList<>(), (l, e) -> {
            l.add(e);
            return l;
        }, (l1, l2) -> {
            l1.addAll(l2);
            return l1;
        });
    }

}
