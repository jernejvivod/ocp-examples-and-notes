package si.jernej.ocp.oop.abstractclasses;

public abstract class AbstractClass implements I
{
    @Override
    public abstract int f();  // can redeclare abstract methods

    public abstract int h(); // can define abstract methods that the extending non-abstract class must implement

    public int k()
    {
        return 7;
    }
}
