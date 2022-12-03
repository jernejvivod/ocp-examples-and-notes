package si.jernej.ocp.oop.abstractclasses;

public class ConcreteClass extends AbstractClass
{
    @Override
    public int f()  // redeclared method in abstract superclass (from its interface)
    {
        return 1;
    }

    @Override
    public int g()  // method from abstract superclass' interface
    {
        return 2;
    }

    @Override
    public int h()  // abstract method from abstract superclass
    {
        return 3;
    }

    public int i()  // subclass' method
    {
        return 4;
    }
}
