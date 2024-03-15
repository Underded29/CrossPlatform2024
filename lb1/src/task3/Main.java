package task3;

import java.lang.reflect.InvocationTargetException;


public class Main {

    public static void main(String[] args) throws MethodCall.FunctionNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, MethodCall.FunctionNotFoundException {
        Main main = new Main();
        double parameterValue = 5.5;
        MethodCall.callMethod(main, "multiply", parameterValue);
        System.out.println("Метод було викликано з такими параметрами: " + parameterValue);
    }

    public String multiply(Double number){
        return "Correct";
    }
}




