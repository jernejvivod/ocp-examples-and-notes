package si.jernej.ocp.test.abstractclasses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.oop.abstractclasses.AbstractClass;
import si.jernej.ocp.oop.abstractclasses.ConcreteClass;
import si.jernej.ocp.oop.abstractclasses.I;

class AbstractClassesTest
{
    @Test
    void testClassHierarchy()
    {
        ConcreteClass c = new ConcreteClass();
        Assertions.assertEquals(ConcreteClass.class, c.getClass());
        Assertions.assertEquals(AbstractClass.class, ConcreteClass.class.getSuperclass());
        Assertions.assertEquals(I.class, ConcreteClass.class.getSuperclass().getInterfaces()[0]);
    }

    @Test
    void testMethods()
    {
        ConcreteClass c = new ConcreteClass();
        Assertions.assertEquals(1, c.f());
        Assertions.assertEquals(2, c.g());
        Assertions.assertEquals(3, c.h());
        Assertions.assertEquals(4, c.i());
    }
}
