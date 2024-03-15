package task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class TracingProxy implements InvocationHandler {
    private Object target;

    public TracingProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method '" + method.getName() + "' is called with arguments:");
        for (Object arg : args) {
            System.out.println(arg);
        }
        Object result = method.invoke(target, args);
        System.out.println("Method '" + method.getName() + "' returned: " + result);
        return result;
    }
}