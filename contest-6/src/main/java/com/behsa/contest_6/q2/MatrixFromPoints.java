package com.behsa.contest_6.q2;

import java.util.Arrays;
import java.util.List;

public class MatrixFromPoints {

    public static int[][] generateMatrixWithPaths(int n, int m, List<int[]> points) {
        int[][] matrix = new int[n][m];

        for (int i = 0; i < points.size(); i++) {
            int[] p = points.get(i);
            matrix[p[0]][p[1]] = 1; // نقطه اصلی

            if (i > 0) {
                int[] prev = points.get(i - 1);
                markShortestPath(matrix, prev, p);
            }
        }

        return matrix;
    }

    private static void markShortestPath(int[][] matrix, int[] start, int[] end) {
        int x0 = start[0], y0 = start[1];
        int x1 = end[0], y1 = end[1];

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;

        while (true) {
            matrix[x0][y0] = 1;

            if (x0 == x1 && y0 == y1) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    // چاپ ماتریس
    public static void printMatrix(int[][] matrix) {
        int r = 0;
        for (int[] row : matrix) {
            System.out.println(r++ + ": " + Arrays.toString(row));
        }
    }

    public static int sumMatrix(int[][] matrix) {
        int sum = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        return sum;
    }

}
