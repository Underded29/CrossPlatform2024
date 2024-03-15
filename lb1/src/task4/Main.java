package task4;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Integer[] intArray = ArrayMatrix.createArray(Integer.class, 5);
        String[][] stringMatrix = ArrayMatrix.createMatrix(String.class, 3, 4);

        Random rand = new Random();
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] =rand.nextInt(100);
        }
        for (int i = 0; i < stringMatrix.length; ++i) {
            for (int j = 0; j < stringMatrix[0].length; ++j){
                stringMatrix[i][j] = ("Str" + i + j);
            }
        }

        System.out.println("Int: \n" + ArrayMatrix.arrayToString(intArray));
        System.out.println("String: \n" + ArrayMatrix.matrixToString(stringMatrix));

        intArray = ArrayMatrix.resizeArray(intArray, 8);
        stringMatrix = ArrayMatrix.resizeMatrix(stringMatrix, 4, 5);


        System.out.println("Int resized: \n" + ArrayMatrix.arrayToString(intArray));
        System.out.println("String resized: \n" + ArrayMatrix.matrixToString(stringMatrix));
    }
}





