package task4;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayMatrix {

    public static <T> T[] createArray(Class<T> clazz, int size) {
        return (T[]) Array.newInstance(clazz, size);
    }

    public static <T> T[][] createMatrix(Class<T> clazz, int rows, int columns) {
        T[][] matrix = (T[][]) Array.newInstance(clazz, rows, columns);
        for (int i = 0; i < rows; i++) {
            matrix[i] = createArray(clazz, columns);
        }
        return matrix;
    }

    public static <T> T[] resizeArray(T[] originalArray, int newSize) {
        return Arrays.copyOf(originalArray, newSize);
    }

    public static <T> T[][] resizeMatrix(T[][] originalMatrix, int newRows, int newColumns) {
        T[][] newMatrix = createMatrix((Class<T>) originalMatrix[0].getClass().getComponentType(), newRows, newColumns);
        for (int i = 0; i < Math.min(originalMatrix.length, newRows); i++) {
            newMatrix[i] = resizeArray(originalMatrix[i], newColumns);
        }
        return newMatrix;
    }

    public static <T> String arrayToString(T[] array) {
        return Arrays.toString(array);
    }

    public static <T> String matrixToString(T[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (T[] row : matrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }
}

