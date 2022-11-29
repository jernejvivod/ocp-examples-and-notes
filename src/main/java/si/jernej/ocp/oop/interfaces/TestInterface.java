package si.jernej.ocp.oop.interfaces;

public interface TestInterface
{
    int f1();  // interface methods are implicitly public. You cannot declare protected package-private or private methods

    public static int f2()  // all abstract, default, and static methods in an interface are implicitly public
    {
        return 7;
    }

    static int f4()  // same access privileges as f2 (implicitly public)
    {
        return f5();
    }

    private static int f5()  // private static interface methods serve as auxiliary methods for public static methods (note use in f4)
    {
        return 10;
    }

    public default int f6()  // default methods are implicitly public
    {
        return f8();
    }

    default int f7()
    {
        return f8();
    }

    private int f8()  // private non-static methods serve as auxiliary methods for implementing default methods
    {
        return 11;
    }
}
