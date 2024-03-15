package task1;

import java.lang.reflect.*;
import java.util.Scanner;

public class task1 {
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            System.out.println("Введіть назву класу (наприклад 'java.util.Date')");
            String Name = in.next();
            Class<?> myClass = Class.forName(Name);
            int modifiers = myClass.getModifiers();
            String className = myClass.getName();
            Package pkg = myClass.getPackage();
            String packageName = pkg.getName();
            String modifiersStr = Modifier.toString(modifiers);
            Class<?>[] interfaces = myClass.getInterfaces();
            StringBuilder interfacesStr = new StringBuilder();
            for (Class<?> intf : interfaces) {
                interfacesStr.append(intf.getSimpleName()).append(", ");
            }
            if (interfacesStr.length() > 0) {
                interfacesStr.setLength(interfacesStr.length() - 2);
            }
            System.out.println(modifiersStr + " class " + className + " implements " + interfacesStr);
            System.out.println("***");
            Constructor [] classConstructors = myClass.getConstructors();
            for (Constructor constructor :classConstructors) {
                System.out.println(constructor);
            }
            System.out.println("***");
            System.out.println("Fields:");
            Field[] declaredFields = myClass.getDeclaredFields();
            for (Field field :declaredFields) {
                System.out.println(field);
            }
            Method[] declaredMethods = myClass.getDeclaredMethods();
            System.out.println("***");
            System.out.println("Methods:");
            for (Method method : declaredMethods) {
                System.out.println(method);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class was not found");
        }
    }
}