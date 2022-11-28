package si.jernej.ocp.functional.closures;

import java.util.Random;
import java.util.function.Function;

public class ClosureExamples
{
    private ClosureExamples()
    {
    }

    private static final Random random = new Random();

    public static Function<Integer, Integer> f(int arg)
    {
        int randInt = random.nextInt();
        return x -> randInt + arg;  // note the use of the local variable randInt in the returned Function instance
    }
}
