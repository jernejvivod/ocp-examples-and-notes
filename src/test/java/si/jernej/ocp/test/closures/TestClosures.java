package si.jernej.ocp.test.closures;

import java.util.function.Function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.functional.closures.ClosureExamples;

class TestClosures
{
    @Test
    void testClosure()
    {
        Function<Integer, Integer> function = ClosureExamples.f(1);
        Assertions.assertEquals(function.apply(1), function.apply(1));
    }
}
