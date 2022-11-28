package si.jernej.ocp.functional.streams.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectExamples
{
    private CollectExamples()
    {
    }

    @SafeVarargs
    public static <T> List<T> collectArgsIntoList(T... args)
    {
        // return Arrays.stream(args).collect(() -> new ArrayList<>(), (a, e) -> a.add(e), (a1, a2) -> a1.addAll(a2));
        return Arrays.stream(args).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);  // Supplier, BiConsumer (accumulator), BiConsumer (combiner)
    }

    // Creating an anonymous class implementing the Collector interface
    public static final Collector<Integer, List<Integer>, Integer> collector = new Collector<>()
    {
        // return Supplier instance for obtaining the initial accumulator
        @Override
        public Supplier<List<Integer>> supplier()
        {
            return ArrayList::new;
        }

        // add next value to accumulator
        @Override
        public BiConsumer<List<Integer>, Integer> accumulator()
        {
            return List::add;
        }

        // combine two accumulators
        @Override
        public BinaryOperator<List<Integer>> combiner()
        {
            return (l1, l2) -> {
                l1.addAll(l2);
                return l1;
            };
        }

        // apply final operation on the final accumulator value
        @Override
        public Function<List<Integer>, Integer> finisher()
        {
            return l -> l.stream().mapToInt(e -> e).sum();
        }

        // the characteristics of a Collector can be used to optimize the implementation of the reduction operation
        @Override
        public Set<Collector.Characteristics> characteristics()
        {
            return Set.of();
        }
    };

    public static final Collector<Integer, List<Integer>, Integer> collectorFromFactoryMethod = Collector.of(
            ArrayList::new,  // Supplier
            List::add,       // BiConsumer (accumulator)
            (a1, a2) -> {    // BinaryOperator (combiner)
                a1.addAll(a2);
                return a1;
            },
            a -> a.stream().mapToInt(e -> e).sum() // function (finisher)
    );

    public static String joinStringsInStream(Stream<String> stream)
    {
        return stream.collect(
                Collector.of(
                        () -> new StringJoiner(" "),
                        StringJoiner::add,
                        StringJoiner::merge,
                        StringJoiner::toString
                )
        );
    }

}
