package si.jernej.ocp.oop.interfaces;

public interface InterfaceWithDefaultMethod2
{
    // note that the default method has the same signature as the default method in si.jernej.ocp.oop.interfaces.InterfaceWithDefaultMethod (but has different body)
    default int f()
    {
        return 2;
    }
}
