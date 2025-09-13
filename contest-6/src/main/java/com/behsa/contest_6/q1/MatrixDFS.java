package com.behsa.contest_6.q1;

import java.util.*;

public class MatrixDFS {

    // پیمایش DFS از یک نقطه مشخص
    public static void dfs(List<List<Integer>> matrix, int i, int j,
                           boolean[][] visited, List<Integer> path, List<List<Integer>> hypotheses) {
        int n = matrix.size();

        // شرط توقف
        if (i < 0 || j < 0 || i >= n || j >= n || visited[i][j]) {
            return;
        }

        // علامت‌گذاری به عنوان بازدیدشده
        visited[i][j] = true;
        path.add(matrix.get(i).get(j));

        // یک فرضیه (مسیر فعلی) رو ذخیره می‌کنیم
        hypotheses.add(new ArrayList<>(path));

        // چهار جهت حرکت: بالا، پایین، چپ، راست
        dfs(matrix, i - 1, j, visited, path, hypotheses);
        dfs(matrix, i + 1, j, visited, path, hypotheses);
        dfs(matrix, i, j - 1, visited, path, hypotheses);
        dfs(matrix, i, j + 1, visited, path, hypotheses);

        // backtrack
        path.remove(path.size() - 1);
        visited[i][j] = false;
    }

    // تابع اصلی برای ایجاد مجموعه فرضیه‌ها
    public static List<List<Integer>> generateHypotheses(List<List<Integer>> matrix) {
        int n = matrix.size();
        boolean[][] visited = new boolean[n][n];
        List<List<Integer>> hypotheses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dfs(matrix, i, j, visited, new ArrayList<>(), hypotheses);
            }
        }

        return hypotheses;
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        List<List<Integer>> hypotheses = generateHypotheses(matrix);

        for (List<Integer> h : hypotheses) {
            System.out.println(h);
        }
    }
}
