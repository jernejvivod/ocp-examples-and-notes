package si.jernej.ocp.oop.interfaces;

public interface InterfaceWithFieldsAndStaticMethods2
{
    int B = 9;  // note that this field has the same name as the field in si.jernej.ocp.oop.interfaces.InterfaceWithFieldsAndStaticMethods

    // note that this method has the same signature as the method in si.jernej.ocp.oop.interfaces.InterfaceWithFieldsAndStaticMethods
    static int f()
    {
        return 2;
    }
}
