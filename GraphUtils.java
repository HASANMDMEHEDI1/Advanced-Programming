package com.mycompany.homework_1;

import java.util.Arrays;

/**
 * Author: MD MEHEDI HASAN
 * Utility class for graph calculations.
 */
public class GraphUtils {
    public static int countEdges(int[][] matrix) {
        int count = 0;
        int n = matrix.length;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (matrix[i][j] == 1) count++;
        return count;
    }

    public static int computeMinDegree(int[][] matrix) {
        int n = matrix.length;
        int[] degrees = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                degrees[i] += matrix[i][j];
        return Arrays.stream(degrees).min().orElse(0);
    }

    public static int computeMaxDegree(int[][] matrix) {
        int n = matrix.length;
        int[] degrees = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                degrees[i] += matrix[i][j];
        return Arrays.stream(degrees).max().orElse(0);
    }

    public static boolean isDegreeSumValid(int[][] matrix) {
        int n = matrix.length;
        int[] degrees = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                degrees[i] += matrix[i][j];
        int degreeSum = Arrays.stream(degrees).sum();
        return degreeSum == 2 * countEdges(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}
