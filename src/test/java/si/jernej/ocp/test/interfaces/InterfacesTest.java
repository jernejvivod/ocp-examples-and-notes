package si.jernej.ocp.test.interfaces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.oop.interfaces.EmptyInterface;
import si.jernej.ocp.oop.interfaces.InterfaceExamples;
import si.jernej.ocp.oop.interfaces.InterfaceWithDefaultMethod;
import si.jernej.ocp.oop.interfaces.InterfaceWithDefaultMethod2;
import si.jernej.ocp.oop.interfaces.InterfaceWithFieldsAndStaticMethods;
import si.jernej.ocp.oop.interfaces.InterfaceWithFieldsAndStaticMethods2;
import si.jernej.ocp.oop.interfaces.InterfaceWithStaticClassEnumInterface;

class InterfacesTest
{
    @Test
    void testClassImplementsInterface()
    {
        // class si.jernej.ocp.oop.interfaces.InterfaceExamples#A implements only interface si.jernej.ocp.oop.interfaces.EmptyInterface
        Assertions.assertEquals(1, InterfaceExamples.A.class.getInterfaces().length);
        Assertions.assertEquals(EmptyInterface.class, InterfaceExamples.A.class.getInterfaces()[0]);
    }

    @Test
    void testInterfaceWithFieldsAndStaticMethods()
    {
        // test public static final
        Assertions.assertEquals(7, InterfaceWithFieldsAndStaticMethods.A);
        Assertions.assertEquals(8, InterfaceWithFieldsAndStaticMethods.B);
        Assertions.assertEquals(9, InterfaceWithFieldsAndStaticMethods.C);
        Assertions.assertEquals(10, InterfaceWithFieldsAndStaticMethods.D);
        Assertions.assertEquals(11, InterfaceWithFieldsAndStaticMethods.E);
    }

    @Test
    void testInterfaceExamples()
    {
        // class si.jernej.ocp.oop.interfaces.InterfaceExamples#A implements only interface si.jernej.ocp.oop.interfaces.EmptyInterface
        InterfaceExamples.A a = new InterfaceExamples.A();
        Assertions.assertEquals(EmptyInterface.class, a.getClass().getInterfaces()[0]);

        // we can assign instances of si.jernej.ocp.oop.interfaces.InterfaceExamples#A to variable of type si.jernej.ocp.oop.interfaces.EmptyInterface
        Assertions.assertTrue(EmptyInterface.class.isAssignableFrom(a.getClass()));

        // test return value of implemented methods
        InterfaceExamples.C1 c1 = new InterfaceExamples.C1();
        Assertions.assertEquals(3, c1.f1());

        InterfaceExamples.C2 c2 = new InterfaceExamples.C2();
        Assertions.assertEquals(23, c2.f6());
    }

    @Test
    void testInterfaceInheritence()
    {

        // Class C implements interface and hides static field
        class C implements InterfaceWithFieldsAndStaticMethods
        {
            public static final int D = 11;
        }

        C c = new C();

        Assertions.assertEquals(c.A, InterfaceWithFieldsAndStaticMethods.A);
        Assertions.assertEquals(C.A, InterfaceWithFieldsAndStaticMethods.A);
        Assertions.assertNotEquals(C.D, InterfaceWithFieldsAndStaticMethods.D);
        // Assertions.assertEquals(1, c.f());  // Note that the following does not work (Static method may be invoked on containing interface class only)

        // class D implements two interfaces that both define a field with the same name
        // they must not be used ambiguously
        class D implements InterfaceWithFieldsAndStaticMethods, InterfaceWithFieldsAndStaticMethods2
        {
        }

        D d = new D();

        Assertions.assertEquals(2, D.class.getInterfaces().length);

        // int r = d.B;  // Reference to 'B' is ambiguous, both 'InterfaceWithFieldsAndStaticMethods.B' and 'InterfaceWithFieldsAndStaticMethods2.B' match
        // int r = D.B;  // Reference to 'B' is ambiguous, both 'InterfaceWithFieldsAndStaticMethods.B' and 'InterfaceWithFieldsAndStaticMethods2.B' match

        // interface I extends two interfaces that both define a field with the same name
        // they must not be used ambiguously
        interface I extends InterfaceWithFieldsAndStaticMethods, InterfaceWithFieldsAndStaticMethods2
        {
        }

        // int r2 = I.B  // Reference to 'B' is ambiguous, both 'InterfaceWithFieldsAndStaticMethods.B' and 'InterfaceWithFieldsAndStaticMethods2.B' match

        // int r3 = I.f()  // Static methods may be invoked on containing interface class only

        // class implements interface I
        class E implements I
        {
            // public static final int a = I.B;  // Reference to 'B' is ambiguous, both 'InterfaceWithFieldsAndStaticMethods.B' and 'InterfaceWithFieldsAndStaticMethods2.B' match        }
            public static final int b1 = InterfaceWithFieldsAndStaticMethods.B;
            public static final int b2 = InterfaceWithFieldsAndStaticMethods2.B;
        }

        E e = new E();

        // can use class to access static field
        Assertions.assertEquals(8, E.b1);
        Assertions.assertEquals(9, E.b2);

        // can use instance to access static field
        Assertions.assertEquals(8, e.b1);
        Assertions.assertEquals(9, e.b2);
    }

    @Test
    void testInterfaceAmbiguousDefaultMethods()
    {
        // F inherits unrelated defaults for f() from types si.jernej.ocp.oop.interfaces.InterfaceWithDefaultMethod and si.jernej.ocp.oop.interfaces.InterfaceWithDefaultMethod2
        class F implements InterfaceWithDefaultMethod, InterfaceWithDefaultMethod2
        {
            // We must implement/override f to resolve ambiguity
            @Override
            public int f()
            {
                return InterfaceWithDefaultMethod.super.f();
            }

            // calling default method f from InterfaceWithDefaultMethod
            public int callDefaultMethodFFromInterfaceWithDefaultMethod()
            {
                return InterfaceWithDefaultMethod.super.f();
            }

            // calling default method f from InterfaceWithDefaultMethod2
            public int callDefaultMethodFFromInterfaceWithDefaultMethod2()
            {
                return InterfaceWithDefaultMethod2.super.f();
            }
        }

        F f = new F();

        // test methods that call the default methods with equal signatures from the two different interfaces
        Assertions.assertEquals(1, f.callDefaultMethodFFromInterfaceWithDefaultMethod());
        Assertions.assertEquals(2, f.callDefaultMethodFFromInterfaceWithDefaultMethod2());
    }

    @Test
    void testInterfaceWithStaticClassEnumInterface()
    {
        // the inner class is public and static
        InterfaceWithStaticClassEnumInterface.A a = new InterfaceWithStaticClassEnumInterface.A();

        Assertions.assertEquals(InterfaceWithStaticClassEnumInterface.A.class, a.getClass());

        // the nested classes are not final (can be extended)
        class B extends InterfaceWithStaticClassEnumInterface.B
        {
        }

        // test superclass
        Assertions.assertEquals(InterfaceWithStaticClassEnumInterface.B.class, B.class.getSuperclass());

        // test inner enum
        InterfaceWithStaticClassEnumInterface.E e = InterfaceWithStaticClassEnumInterface.E.A;
        Assertions.assertEquals(e, InterfaceWithStaticClassEnumInterface.E.A);

        // class C implements inner interface si.jernej.ocp.oop.interfaces.InterfaceWithStaticClassEnumInterface#I
        class C implements InterfaceWithStaticClassEnumInterface.I
        {
        }

        Assertions.assertEquals(InterfaceWithStaticClassEnumInterface.I.class, C.class.getInterfaces()[0]);
    }
}
