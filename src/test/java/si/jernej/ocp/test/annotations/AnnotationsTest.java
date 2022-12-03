package si.jernej.ocp.test.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.annotations.AnnotationOnClass;
import si.jernej.ocp.annotations.AnnotationOnClassWithValues;
import si.jernej.ocp.annotations.AnnotationOnConstructor;
import si.jernej.ocp.annotations.AnnotationOnInstance;
import si.jernej.ocp.annotations.AnnotationOnMethod;
import si.jernej.ocp.annotations.AnnotationOnPackage;
import si.jernej.ocp.annotations.AnnotationOnTypeParameter;
import si.jernej.ocp.annotations.AnnotationWithAnnotation;
import si.jernej.ocp.annotations.AnnotationWithArray;
import si.jernej.ocp.annotations.AnnotationsExamples;
import si.jernej.ocp.annotations.FieldAnnotationWithMetaAnnotation;
import si.jernej.ocp.annotations.InheritedAnnotation;
import si.jernej.ocp.annotations.MetaAnnotation;
import si.jernej.ocp.annotations.NonInheritedAnnotation;

class AnnotationsTest
{
    @Test
    void testAnnotationsOnClassA()
    {
        Assertions.assertEquals(2, AnnotationsExamples.A.class.getDeclaredAnnotations().length);
        Assertions.assertEquals(AnnotationOnClass.class, AnnotationsExamples.A.class.getDeclaredAnnotations()[0].annotationType());
        Assertions.assertEquals(AnnotationOnClassWithValues.class, AnnotationsExamples.A.class.getDeclaredAnnotations()[1].annotationType());
        Assertions.assertEquals(1, AnnotationsExamples.A.class.getDeclaredAnnotation(AnnotationOnClassWithValues.class).intParam());
        Assertions.assertEquals("default value", AnnotationsExamples.A.class.getDeclaredAnnotation(AnnotationOnClassWithValues.class).strParam());
    }

    @Test
    void testAnnotationsOnClassB()
    {
        Assertions.assertEquals(2, AnnotationsExamples.B.class.getDeclaredAnnotation(AnnotationOnClassWithValues.class).intParam());
        Assertions.assertEquals("test", AnnotationsExamples.B.class.getDeclaredAnnotation(AnnotationOnClassWithValues.class).strParam());
    }

    @Test
    void testInstanceAnnotation() throws IllegalAccessException
    {
        Field fieldWithInstanceAnnotation = Arrays.stream(AnnotationsExamples.class.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(AnnotationOnInstance.class))
                .findAny()
                .orElseThrow();

        AnnotationsExamples annotationsExamples = new AnnotationsExamples();

        Assertions.assertEquals(AnnotationsExamples.A.class, fieldWithInstanceAnnotation.get(annotationsExamples).getClass());
    }

    @Test
    void testMethodAnnotation()
    {
        Method method = Arrays.stream(AnnotationsExamples.class.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(AnnotationOnMethod.class))
                .findAny()
                .orElseThrow();

        Assertions.assertEquals(3, method.getAnnotation(AnnotationOnMethod.class).value());
    }

    @Test
    void testParameterAnnotation()
    {
        Assertions.assertEquals(0, AnnotationsExamples.sumWithAnnotatedParameters(0, 0, 0));
        Assertions.assertEquals(1, AnnotationsExamples.sumWithAnnotatedParameters(1, 0, 0));
        Assertions.assertEquals(41, AnnotationsExamples.sumWithAnnotatedParameters(2, 3, 4));
    }

    @Test
    void testConstructorAnnotation()
    {
        Constructor<?> constructor = Arrays.stream(AnnotationsExamples.class.getConstructors())
                .filter(c -> c.isAnnotationPresent(AnnotationOnConstructor.class))
                .findAny()
                .orElseThrow();

        Assertions.assertEquals(1, constructor.getParameterCount());
        Assertions.assertEquals(AnnotationOnConstructor.class, constructor.getDeclaredAnnotation(AnnotationOnConstructor.class).annotationType());
    }

    @Test
    void testMetaAnnotations()
    {
        Field fieldWithAnnotationWithMetaAnnotation = Arrays.stream(AnnotationsExamples.class.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(FieldAnnotationWithMetaAnnotation.class))
                .findAny()
                .orElseThrow();

        Assertions.assertEquals(FieldAnnotationWithMetaAnnotation.class, fieldWithAnnotationWithMetaAnnotation.getAnnotation(FieldAnnotationWithMetaAnnotation.class).annotationType());

        Assertions.assertEquals("test", fieldWithAnnotationWithMetaAnnotation.getAnnotation(FieldAnnotationWithMetaAnnotation.class).annotationType().getAnnotation(MetaAnnotation.class).value());
    }

    @Test
    @AnnotationsExamples.InnerAnnotation
    void testInnerAnnotation() throws NoSuchMethodException
    {
        AnnotationsExamples.InnerAnnotation innerAnnotation = AnnotationsTest.class.getDeclaredMethod("testInnerAnnotation").getAnnotation(AnnotationsExamples.InnerAnnotation.class);
        Assertions.assertEquals(AnnotationsExamples.InnerAnnotation.class, innerAnnotation.annotationType());
    }

    @Test
    void testRepeatableAnnotations() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Assertions.assertEquals(7, AnnotationsExamples.invokeMethodAndMultiplyIntResultWithSumOfValuesInAnnotations(AnnotationsExamples.class.getMethod("m1")));
        Assertions.assertEquals(100, AnnotationsExamples.invokeMethodAndMultiplyIntResultWithSumOfValuesInAnnotations(AnnotationsExamples.class.getMethod("m2")));
    }

    @Test
    void testRepeatableAnnotations2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Assertions.assertEquals(7, AnnotationsExamples.invokeMethodAndMultiplyIntResultWithSumOfValuesInAnnotations2(AnnotationsExamples.class.getMethod("m1")));
        Assertions.assertEquals(100, AnnotationsExamples.invokeMethodAndMultiplyIntResultWithSumOfValuesInAnnotations2(AnnotationsExamples.class.getMethod("m2")));
    }

    @Test
    void testInheritedAnnotation()
    {
        Assertions.assertTrue(AnnotationsExamples.D.class.isAnnotationPresent(InheritedAnnotation.class));
        Assertions.assertFalse(AnnotationsExamples.D.class.isAnnotationPresent(NonInheritedAnnotation.class));

        Assertions.assertTrue(AnnotationsExamples.D.class.getSuperclass().isAnnotationPresent(InheritedAnnotation.class));
        Assertions.assertTrue(AnnotationsExamples.D.class.getSuperclass().isAnnotationPresent(NonInheritedAnnotation.class));
    }

    @Test
    void testAnnotationWithAnnotation() throws NoSuchFieldException
    {
        Assertions.assertTrue(AnnotationsExamples.class.getDeclaredField("b").isAnnotationPresent(AnnotationWithAnnotation.class));
        Assertions.assertTrue(AnnotationsExamples.class.getDeclaredField("b").isAnnotationPresent(AnnotationWithAnnotation.class));

        Assertions.assertEquals(1, AnnotationsExamples.class.getDeclaredField("b").getAnnotation(AnnotationWithAnnotation.class).value().value());
    }

    @Test
    void testAnnotationWithArray()
    {
        List<Method> methodsWithAnnotationWithArray = Arrays.stream(AnnotationsExamples.class.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(AnnotationWithArray.class))
                .toList();

        for (Method m : methodsWithAnnotationWithArray)
        {
            Assertions.assertEquals(1, m.getAnnotation(AnnotationWithArray.class).value().length);
            Assertions.assertEquals("test", m.getAnnotation(AnnotationWithArray.class).value()[0]);
        }
    }

    @Test
    void testAnnotationWithMembers() throws NoSuchFieldException
    {
        Assertions.assertEquals(1, AnnotationsExamples.AnnotationWithMembers.a);
        Assertions.assertEquals(1, AnnotationsExamples.AnnotationWithMembers.E.A.getI());

        AnnotationsExamples.AnnotationWithMembers.A a = new AnnotationsExamples.AnnotationWithMembers.A();
        Assertions.assertEquals(AnnotationsExamples.AnnotationWithMembers.A.class, a.getClass());

        class A implements AnnotationsExamples.AnnotationWithMembers.I
        {
        }

        Assertions.assertTrue(AnnotationsExamples.AnnotationWithMembers.I.class.isAssignableFrom(A.class));

        Assertions.assertTrue(AnnotationsExamples.class.getDeclaredField("g").isAnnotationPresent(AnnotationsExamples.AnnotationWithMembers.MemberAnnotation.class));
    }

    @Test
    void testAnnotationOnRecordComponent()
    {
        var r = new AnnotationsExamples.RecordWithAnnotationsOnComponents(2, 3);
        Assertions.assertEquals(37, r.get());
    }

    @Test
    void testAnnotationOnPackage()
    {
        Assertions.assertTrue(AnnotationsExamples.class.getPackage().isAnnotationPresent(AnnotationOnPackage.class));
        Assertions.assertFalse(AnnotationsExamples.class.getPackage().isAnnotationPresent(AnnotationOnClass.class));
    }

    @Test
    void testAnnotationOnTypeParameter()
    {
        TypeVariable<Class<AnnotationsExamples.F>> typeVariable = Arrays.stream(AnnotationsExamples.F.class.getTypeParameters())
                .filter(tp -> tp.isAnnotationPresent(AnnotationOnTypeParameter.class))
                .findAny()
                .orElseThrow();

        Assertions.assertEquals(1, typeVariable.getAnnotation(AnnotationOnTypeParameter.class).a());
    }
}
