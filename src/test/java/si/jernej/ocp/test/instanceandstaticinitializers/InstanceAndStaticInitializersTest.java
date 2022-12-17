package si.jernej.ocp.test.instanceandstaticinitializers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.instanceandstaticinitializers.InstanceAndStaticInitializersExamples;

class InstanceAndStaticInitializersTest
{
    @Test
    void testClassHierarchyInitializationSequence()
    {
        InstanceAndStaticInitializersExamples.getBInstance();
        Assertions.assertEquals(
                List.of(
                        "static initializer of A called",
                        "static initializer of B called",
                        "instance initializer of A called",
                        "constructor of A called",
                        "instance initializer of B called",
                        "constructor of B called"
                ),
                InstanceAndStaticInitializersExamples.A.getSeq()
        );
    }
}
