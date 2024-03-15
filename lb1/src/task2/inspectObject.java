package task2;

import java.lang.reflect.*;
import java.util.Scanner;

public class inspectObject {

    public static void inspectObject(Object obj) {
        Class<?> objClass = obj.getClass();
        int ka = 0;
        // Отримуємо список полів і значень об'єкту
        Field[] fields = objClass.getDeclaredFields();
        System.out.println("Поля:");
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println((ka++) + ". " + field.getName() + "()" +  " = " + field.get(obj));
            } catch (IllegalAccessException e) {
                System.out.println(field.getName() + " " + "Доступу немає");
            }
        }
        int k = 0;
        // Отримуємо список методів класу
        Method[] methods = objClass.getDeclaredMethods();
        System.out.println("Методи:");
        for (Method method : methods) {
            System.out.println((k++) + ". " + method.getName() + "()");
        }

        // Запитуємо користувача, який метод потрібно викликати
        Scanner scanner = new Scanner(System.in);
        int methodName = 0;
        while (methodName != -1) {
            Method methodToInvoke = null;
            System.out.print("Введіть номер методу (-1 щоб вийти):");
            methodName = scanner.nextInt();
            if(methodName == -1){
                continue;
            }
            if(methodName >= methods.length || methodName < -1){
                System.out.println("Введіть вірне значення.");
                continue;
            }
            if(methods[methodName].getParameterCount() == 0) {
                methodToInvoke = methods[methodName];
            } else {
                System.out.println("Метод має параметри, тому його не можна визвати");
                continue;
            }
            if (methodToInvoke == null) {
                System.out.println("Нема методу");
            } else {
                methodToInvoke.setAccessible(true);
                try {
                    methodToInvoke.invoke(obj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("Визов обірвано.");
                }
            }
        }
    }
}
