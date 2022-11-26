package si.jernej.ocp.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class AnnotationsExamples
{
    // 1. Annotations on classes

    @AnnotationOnClass
    @AnnotationOnClassWithValues(intParam = 1)
    public static class A
    {
    }

    @AnnotationOnClass
    @AnnotationOnClassWithValues(intParam = 2, strParam = "test")
    public static class B
    {
    }

    // 2. Annotations on instances

    @AnnotationOnInstance
    public final A a;

    public AnnotationsExamples()
    {
        this.a = new A();
    }

    // 3. Annotations on methods

    @AnnotationOnMethod(3)
    public int returnSeven()
    {
        return 7;
    }

    // 4. Annotations on method parameters
    public static int sumWithAnnotatedParameters(int a, @Times(5) int b, @Times(6) int c)
    {
        try
        {
            Method m = AnnotationsExamples.class.getMethod("sumWithAnnotatedParameters", int.class, int.class, int.class);
            Annotation[][] parameterAnnotations = m.getParameterAnnotations();
            List<Integer> paramMultipliers = Arrays.stream(parameterAnnotations).map(annotations ->
                    Arrays.stream(annotations).filter(Times.class::isInstance).map(t -> ((Times) t).value()).reduce(1, (x, y) -> x * y)
            ).toList();
            return a * paramMultipliers.get(0) + b * paramMultipliers.get(1) + c * paramMultipliers.get(2);
        }
        catch (NoSuchMethodException e)
        {
            throw new RuntimeException();
        }
    }

    // 5. Annotations on constructors
    @AnnotationOnConstructor
    public AnnotationsExamples(int a)
    {
        this.a = new A();
    }

    // 6. Annotations on annotations
    @FieldAnnotationWithMetaAnnotation
    private int d;

    // 7. Inner annotations
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InnerAnnotation
    {
    }

    // 8. Repeatable Annotations

    @RepeatableAnnotation(7)
    public int m1()
    {
        return 1;
    }

    @RepeatableAnnotation(7)
    @RepeatableAnnotation(3)
    public int m2()
    {
        return 10;
    }

    public static int invokeMethodAndMultiplyIntResultWithSumOfValuesInAnnotations(Method method, Object... args) throws InvocationTargetException, IllegalAccessException
    {
        if (method.isAnnotationPresent(RepeatableAnnotation.class))
        {
            return ((int) method.invoke(new AnnotationsExamples(), args)) * method.getAnnotation(RepeatableAnnotation.class).value();
        }
        else if (method.isAnnotationPresent(RepeatableAnnotations.class))
        {
            return ((int) method.invoke(new AnnotationsExamples(), args)) * Arrays.stream(method.getAnnotation(RepeatableAnnotations.class).value()).map(RepeatableAnnotation::value).mapToInt(e -> e).sum();
        }
        else
        {
            return (int) method.invoke(new AnnotationsExamples(), args);
        }
    }

    // 9. Inherited Annotations

    @InheritedAnnotation
    @NonInheritedAnnotation
    public class C
    {
    }

    public class D extends C
    {
    }

    // 10. Annotations containing annotations as values

    @AnnotationWithAnnotation(@ContainedAnnotation(1))
    private final int b = 1;

    // 11. Arrays in annotations and shorthands

    @AnnotationWithArray("test")
    private final int c = 1;

    @AnnotationWithArray(value = "test")
    private final int e = 1;

    @AnnotationWithArray(value = { "test" })
    private final int f = 1;

    // 12. Annotation members

    @Retention(RetentionPolicy.SOURCE)
    public @interface AnnotationWithMembers
    {
        int a = 1;

        @Retention(RetentionPolicy.RUNTIME)
        @interface MemberAnnotation
        {
        }

        class A
        {
        }

        enum E
        {
            A(1), B(2), C(3);

            private int i;

            E(int i)
            {
                this.i = i;
            }

            public int getI()
            {
                return i;
            }
        }

        interface I
        {
        }

        // static methods are not allowed
    }

    @AnnotationWithMembers.MemberAnnotation
    private int g = 1;

    // 13. Record component annotations
    public record RecordWithAnnotationsOnComponents(@AnnotationOnRecordComponent(a = 2, b = 3) int a, @AnnotationOnRecordComponent(a = 4, b = 5) int b)
    {
        public int get()
        {
            Integer[] integers = Arrays.stream(AnnotationsExamples.RecordWithAnnotationsOnComponents.class.getRecordComponents())
                    .map(c -> {
                        if (c.isAnnotationPresent(AnnotationOnRecordComponent.class))
                        {
                            AnnotationOnRecordComponent annotation = c.getAnnotation(AnnotationOnRecordComponent.class);
                            return annotation.a() + annotation.b();
                        }
                        else
                        {
                            return 1;
                        }
                    }).toArray(Integer[]::new);

            return a * integers[0] + b * integers[1];
        }
    }

    // 14. Package annotations

    // see package-info.java

    // 15. Type Parameter Annotations

    public class F<@AnnotationOnTypeParameter T>
    {
        private T a;

        public F(T a)
        {
            this.a = a;
        }

        public T getA()
        {
            return a;
        }
    }

}
