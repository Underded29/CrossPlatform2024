package task5;

import java.lang.reflect.*;
public class Main {
    public static void main(String[] args) {
        SomeInterface obj = new SomeClass();

        // Створюємо проксі для профілювання методу
        SomeInterface profilingProxy = (SomeInterface) Proxy.newProxyInstance(
                SomeInterface.class.getClassLoader(),
                new Class<?>[]{SomeInterface.class},
                new ProfilingProxy(obj));

        // Створюємо проксі для трасування методу
        SomeInterface tracingProxy = (SomeInterface) Proxy.newProxyInstance(
                SomeInterface.class.getClassLoader(),
                new Class<?>[]{SomeInterface.class},
                new TracingProxy(obj));

        // Викликаємо методи через проксі
        profilingProxy.doSomething();
        System.out.println("Result of calculation: " + profilingProxy.calculate(5, 3));

        tracingProxy.doSomething();
        System.out.println("Result of calculation: " + tracingProxy.calculate(10, 7));
    }
}