package com.mycompany.homework_1;

import java.util.*;

/**
 * Author: MD MEHEDI HASAN
 * Class responsible for graph generation and adjacency matrix storage.
 */
public class Graph {
    private final int[][] matrix;
    private final int n;
    private final int k;

    public Graph(int n, int k) {
        this.n = n;
        this.k = k;
        this.matrix = new int[n][n];
        generateGraph();
    }

    private void generateGraph() {
        Random rand = new Random();

        List<Integer> cliqueNodes = new ArrayList<>();
        for (int i = 0; i < k; i++) cliqueNodes.add(i);
        for (int i = 0; i < k; i++)
            for (int j = i + 1; j < k; j++)
                matrix[i][j] = matrix[j][i] = 1;

        List<Integer> stableNodes = new ArrayList<>();
        for (int i = 0; i < k; i++) stableNodes.add(n - 1 - i);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!cliqueNodes.contains(i) && !cliqueNodes.contains(j) &&
                        !stableNodes.contains(i) && !stableNodes.contains(j)) {
                    matrix[i][j] = matrix[j][i] = rand.nextBoolean() ? 1 : 0;
                }
            }
        }
    }

    public int getEdgeCount() {
        return GraphUtils.countEdges(matrix);
    }

    public int getMinDegree() {
        return GraphUtils.computeMinDegree(matrix);
    }

    public int getMaxDegree() {
        return GraphUtils.computeMaxDegree(matrix);
    }

    public boolean isDegreeSumValid() {
        return GraphUtils.isDegreeSumValid(matrix);
    }

    public void printMatrix() {
        GraphUtils.printMatrix(matrix);
    }
}
