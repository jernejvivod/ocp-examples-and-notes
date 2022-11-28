package si.jernej.ocp.test.proxy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import si.jernej.ocp.proxy.ProxyExamples;

class TestProxy
{
    @Test
    void testProxyingOfA()
    {
        ProxyExamples.I a = ProxyExamples.getProxiedA();
        Assertions.assertEquals(3, a.returnInt(3, false));
        Assertions.assertEquals(10, a.returnInt(3, true));
    }
}
