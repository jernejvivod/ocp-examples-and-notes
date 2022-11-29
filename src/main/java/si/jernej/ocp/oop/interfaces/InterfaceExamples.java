package si.jernej.ocp.oop.interfaces;

public class InterfaceExamples
{
    private InterfaceExamples()
    {
    }

    // class implementing si.jernej.ocp.oop.EmptyInterface
    public static class A implements EmptyInterface
    {
        public int get()  // additional method not in the interface
        {
            return 1;
        }
    }

    // class C2 implements I2 (see its implementation)
    public static class C1 implements TestInterface
    {
        @Override
        public int f1()  // The overriding method must not have weaker access privileges
        {
            return 3;
        }
    }

    // class C3 implements I2 and additionally overrides the default method f6()
    public static class C2 implements TestInterface
    {
        @Override
        public int f1()
        {
            return 3;
        }

        @Override
        public int f6()
        {
            return 12 + TestInterface.super.f6();  // I2.super.f6() is a way to refer to the implemented interface's method
            // this is different from using 'super' in the context of extending classes, as a class can implement multiple interfaces
        }
    }

}
