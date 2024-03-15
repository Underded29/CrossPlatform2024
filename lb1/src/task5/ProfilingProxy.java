package task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class ProfilingProxy implements InvocationHandler {
    private Object target;

    public ProfilingProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Method '" + method.getName() + "' took " + duration + " nanoseconds to execute.");
        return result;
    }
}