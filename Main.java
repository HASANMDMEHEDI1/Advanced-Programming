package com.mycompany.homework_1;

/**
 * Author: MD MEHEDI HASAN
 * Class responsible for running the program.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <n> <k>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        if (k > n) {
            System.out.println("Error: k should not be greater than n.");
            return;
        }

        long startTime = System.currentTimeMillis();

        Graph graph = new Graph(n, k);

        long endTime = System.currentTimeMillis();

        System.out.println("Number of edges (m): " + graph.getEdgeCount());
        System.out.println("Maximum degree (Δ): " + graph.getMaxDegree());
        System.out.println("Minimum degree (δ): " + graph.getMinDegree());
        System.out.println("Degree sum check: " + graph.isDegreeSumValid());

        if (n <= 50) {
            System.out.println("Adjacency Matrix:");
            graph.printMatrix();
        } else {
            System.out.println("Execution Time: " + (endTime - startTime) + " ms");
        }
    }
}
