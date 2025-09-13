package com.behsa.contest_6.q2;

import java.util.*;

public class GenRandomPoints {

    public static List<int[]> generateRandomPoints(int n, int m, int count) {
        if (count > n * m) {
            throw new IllegalArgumentException("تعداد نقاط نمی‌تواند بیشتر از تعداد خانه‌ها باشد");
        }

        List<int[]> points = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        // همه خانه‌ها را به صورت اندیس خطی اضافه می‌کنیم
        for (int i = 0; i < n * m; i++) {
            indices.add(i);
        }

        // shuffle برای تصادفی کردن
        Collections.shuffle(indices);

        // انتخاب count خانه اول
        for (int i = 0; i < count; i++) {
            int idx = indices.get(i);
            int row = idx / m;
            int col = idx % m;
            points.add(new int[]{row, col});
        }

        return points;
    }
}
