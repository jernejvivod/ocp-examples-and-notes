package si.jernej.ocp.functional.methodreferences;

import java.util.Comparator;
import java.util.List;

public class MethodReferenceExamples
{
    private MethodReferenceExamples()
    {
    }

    public static class StringUtils
    {
        private StringUtils()
        {
        }

        public static String capitalize(String s)
        {
            return s.toUpperCase();
        }
    }

    public static List<String> capitalizeListOfStrings(List<String> s)
    {
        return s.stream().map(StringUtils::capitalize).toList();
    }

    public static void sortStringListBySecondCharacterInString(List<String> strings)
    {
        for (String string : strings)
        {
            if (string.length() < 2)
                throw new IllegalArgumentException("String %s in argument list has length less than 2.".formatted(string));
        }

        class StringComparator implements Comparator<String>
        {
            @Override
            public int compare(String s1, String s2)
            {
                return Character.compare(s1.charAt(1), s2.charAt(1));
            }
        }

        Comparator<String> comparator = new StringComparator();
        strings.sort(comparator::compare);  // can pass just comparator
    }

    public static final class A
    {
        private int b;

        public A(int b)
        {
            this.b = b;
        }

        public void multiplyPropertyBy2()
        {
            this.b *= 2;
        }

        public int getB()
        {
            return this.b;
        }
    }

    public static void multiplyPropertyBy2ForEachInstanceInList(List<A> as)
    {
        as.forEach(A::multiplyPropertyBy2);
    }

    public static List<A> mapListOfIntsToAInstances(List<Integer> integers)
    {
        return integers.stream().map(A::new).toList();
    }

}
