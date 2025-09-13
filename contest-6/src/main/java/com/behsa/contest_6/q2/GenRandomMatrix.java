package com.behsa.contest_6.q2;

import java.util.*;

public class GenRandomMatrix {

    // تولید ماتریس n*m با k خانه 1 تصادفی و باقی 0
    public static int[][] generateMatrix(int n, int m, int k) {
        if (k > n * m) {
            throw new IllegalArgumentException("تعداد خانه‌های 1 نمی‌تواند بیشتر از تعداد کل خانه‌ها باشد");
        }

        int[][] matrix = new int[n][m];

        // ایجاد لیست تمام اندیس‌ها
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n * m; i++) {
            indices.add(i);
        }

        // shuffle برای تصادفی کردن اندیس‌ها
        Collections.shuffle(indices);

        // انتخاب k خانه اول برای مقدار 1
        for (int i = 0; i < k; i++) {
            int idx = indices.get(i);
            int row = idx / m;
            int col = idx % m;
            matrix[row][col] = 1;
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 5;
        int k = 7; // تعداد خانه‌های 1
        int[][] matrix = generateMatrix(n, m, k);

        printMatrix(matrix);
    }
}
