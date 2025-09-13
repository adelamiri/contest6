package com.behsa.contest_6.q2;

import java.util.Arrays;
import java.util.List;

public class MatrixFromPoints {

    // تولید ماتریس با نقاط مشخص
    public static int[][] generateMatrix(int n, int m, List<int[]> points) {
        int[][] matrix = new int[n][m];

        for (int[] point : points) {
            int row = point[0];
            int col = point[1];

            if (row < 0 || row >= n || col < 0 || col >= m) {
                throw new IllegalArgumentException("نقطه خارج از محدوده ماتریس است: (" + row + "," + col + ")");
            }

            matrix[row][col] = 1;
        }

        return matrix;
    }

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

    private static void markGridPath(int[][] matrix, int[] start, int[] end) {
        int x = start[0];
        int y = start[1];

        // حرکت به سمت ردیف مقصد
        int dx = Integer.compare(end[0], x);
        while (x != end[0]) {
            x += dx;
            matrix[x][y] = 1;
        }

        // حرکت به سمت ستون مقصد
        int dy = Integer.compare(end[1], y);
        while (y != end[1]) {
            y += dy;
            matrix[x][y] = 1;
        }
    }

    private static void markPath(int[][] matrix, int[] start, int[] end) {
        int x0 = start[0], y0 = start[1];
        int x1 = end[0], y1 = end[1];

        int dx = Integer.compare(x1, x0); // -1,0,1
        int dy = Integer.compare(y1, y0); // -1,0,1

        int x = x0, y = y0;
        while (x != x1 || y != y1) {
            x += dx;
            y += dy;
            matrix[x][y] = 1;
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
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

}
