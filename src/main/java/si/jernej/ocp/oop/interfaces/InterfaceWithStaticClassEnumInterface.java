package si.jernej.ocp.oop.interfaces;

public interface InterfaceWithStaticClassEnumInterface
{
    // all nested classes, enums and interfaces are implicitly public and static
    class A
    {
    }

    abstract class B
    {
    }

    enum E
    {
        A, B, C
    }

    interface I
    {
    }
}
