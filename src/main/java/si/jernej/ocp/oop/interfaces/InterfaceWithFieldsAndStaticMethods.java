package si.jernej.ocp.oop.interfaces;

public interface InterfaceWithFieldsAndStaticMethods
{
    int A = 7;                 // interface fields are implicitly public, static and final
    public int B = 8;          // equivalent
    static int C = 9;          // equivalent
    public static int D = 10;  // equivalent
    public static final int E = 11;  // equivalent

    static int f()             // static methods may be invoked on the interface class only
    {
        return 1;
    }
}
