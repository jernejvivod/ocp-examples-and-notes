package si.jernej.ocp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyExamples
{
    private ProxyExamples()
    {
    }

    // interface
    public interface I
    {
        int returnInt(int val, boolean ignoredParameter);
    }

    // class implementing interface
    public static class A implements I
    {
        @Override
        public int returnInt(int val, boolean ignoredParameter)
        {
            return val;
        }
    }

    // handler for invocations of methods in interface I
    public static class DynamicInvocationHandler implements InvocationHandler
    {
        private final I inst;

        public DynamicInvocationHandler(I inst)
        {
            this.inst = inst;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
            if ((boolean) args[1])
            {
                return (int) method.invoke(this.inst, args) + 7;
            }
            else
            {
                return method.invoke(this.inst, args);
            }
        }

    }

    // Get proxied instance of A
    public static I getProxiedA()
    {
        return (I) Proxy.newProxyInstance(
                A.class.getClassLoader(),
                A.class.getInterfaces(),
                new DynamicInvocationHandler(new A())
        );
    }
}
