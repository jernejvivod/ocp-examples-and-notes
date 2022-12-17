package si.jernej.ocp.instanceandstaticinitializers;

import java.util.ArrayList;
import java.util.List;

public class InstanceAndStaticInitializersExamples
{
    public static class A
    {
        private static List<String> seq = new ArrayList<>(3);

        public A()
        {
            seq.add("constructor of A called");
        }

        static
        {
            seq.add("static initializer of A called");
        }

        {
            seq.add("instance initializer of A called");
        }

        public static List<String> getSeq()
        {
            return seq;
        }
    }

    public static class B extends A
    {
        public B()
        {
            A.seq.add("constructor of B called");
        }

        static
        {
            A.seq.add("static initializer of B called");
        }

        {
            A.seq.add("instance initializer of B called");
        }
    }

    public static void getAInstance()
    {
        new A();
    }

    public static void getBInstance()
    {
        new B();
    }

}
