package task3;

import java.lang.reflect.*;


public class MethodCall {

    public static void callMethod(Object obj, String methodName, Object... params)
            throws FunctionNotFoundException, IllegalAccessException, InvocationTargetException {
        Class<?>[] paramTypes = new Class<?>[params.length];
        for (int i = 0; i < params.length; i++) {
            paramTypes[i] = params[i].getClass();
            //System.out.println(paramTypes[i]);
        }
        try {
            Method method = obj.getClass().getMethod(methodName, paramTypes);
            Object result = method.invoke(obj, params);
            System.out.println(result);
        } catch (NoSuchMethodException e) {
            throw new FunctionNotFoundException("Method not found: " + methodName);
        } catch (InvocationTargetException e) {
            throw new InvocationTargetException(e.getCause());
        }
    }

    public static class FunctionNotFoundException extends Exception {
        public FunctionNotFoundException(String message) {
            super(message);
        }
        public FunctionNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
