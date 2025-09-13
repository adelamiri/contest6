package com.behsa.contest_6.q1;

import java.util.*;

public class BooleanHypotheses {

    public static Map<String, List<Boolean>> generateAllHypotheses(int n) {
        Map<String, List<Boolean>> hypotheses = new LinkedHashMap<>();
        int total = 1 << n; // 2^n حالت

        for (int i = 0; i < total; i++) {
            List<Boolean> state = new ArrayList<>();
            for (int j = n - 1; j >= 0; j--) {
                state.add((i & (1 << j)) != 0);
            }
            hypotheses.put("H" + (i + 1), state);
        }

        return hypotheses;
    }


    // تولید همه حالت‌ها با یک سوم false و بقیه true
    public static Map<String, List<Boolean>> generateHypotheses(int n) {
        if (n % 3 != 0) {
            throw new IllegalArgumentException("n باید مضربی از 3 باشد");
        }

        int falseCount = n / 3;
        Map<String, List<Boolean>> hypotheses = new LinkedHashMap<>();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++) indices.add(i);

        List<List<Integer>> combinations = new ArrayList<>();
        combine(indices, falseCount, 0, new ArrayList<>(), combinations);

        int counter = 1;
        for (List<Integer> combo : combinations) {
            List<Boolean> state = new ArrayList<>(Collections.nCopies(n, true));
            for (int idx : combo) {
                state.set(idx, false);
            }
            hypotheses.put("H" + counter, state);
            counter++;
        }

        return hypotheses;
    }

    // متد کمکی برای تولید تمام ترکیب‌ها
    private static void combine(List<Integer> arr, int k, int start, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < arr.size(); i++) {
            path.add(arr.get(i));
            combine(arr, k, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }


}
