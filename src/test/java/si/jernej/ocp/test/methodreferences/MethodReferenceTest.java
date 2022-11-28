package si.jernej.ocp.test.methodreferences;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.functional.methodreferences.MethodReferenceExamples;

class MethodReferenceTest
{
    @Test
    void testStaticMethodMethodReference()
    {
        Assertions.assertEquals(List.of("THIS", "IS", "A", "TEST"), MethodReferenceExamples.capitalizeListOfStrings(List.of("this", "is", "a", "test")));
    }

    @Test
    void testSpecificInstanceMethodMethodReference()
    {
        List<String> listToSort = new ArrayList<>(List.of("zzu", "xav", "kbo", "olu"));
        MethodReferenceExamples.sortStringListBySecondCharacterInString(listToSort);
        Assertions.assertEquals(List.of("xav", "kbo", "olu", "zzu"), listToSort);
    }

    @Test
    void testGeneralInstanceMethodMethodReference()
    {
        List<MethodReferenceExamples.A> l = List.of(new MethodReferenceExamples.A(2), new MethodReferenceExamples.A(7), new MethodReferenceExamples.A(23));
        MethodReferenceExamples.multiplyPropertyBy2ForEachInstanceInList(l);
        List<Integer> expectedBValues = List.of(4, 14, 46);
        for (int i = 0; i < l.size(); i++)
        {
            Assertions.assertEquals(expectedBValues.get(i), l.get(i).getB());
        }
    }

    @Test
    void testConstructorMethodReference()
    {
        List<Integer> intsList = List.of(1, 2, 3, 4, 5);
        List<MethodReferenceExamples.A> res = MethodReferenceExamples.mapListOfIntsToAInstances(intsList);
        for (int i = 0; i < res.size(); i++)
        {
            Assertions.assertEquals(intsList.get(i), res.get(i).getB());
        }
    }
}
