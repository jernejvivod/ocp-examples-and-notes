package si.jernej.ocp.oop.interfaces;

public interface InterfaceWithDefaultMethod
{
    // default method (the implementing class is not obliged to override it and adding it does not break implementing classes)
    default int f()
    {
        return 1;
    }
}
