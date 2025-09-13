package com.behsa.contest_6.q1;

import java.util.ArrayList;
import java.util.List;

public class Dateset {
    private final List<List<Integer>> data;

    public Dateset(List<List<Integer>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "InputDateset{" +
                "data=" + data +
                '}';
    }

    public void validate() {
        if (!isPerfectSquareMatrix(data))
            throw new RuntimeException("Invalid matrix perfect square matrix");
    }

    public static boolean isPerfectSquareMatrix(List<List<Integer>> data) {
        if (data == null || data.isEmpty()) {
            return false;
        }

        int n = data.size();

        for (List<Integer> row : data) {
            if (row == null || row.size() != n) {
                return false;
            }
        }
        return true;
    }

    public int getSize() {
        return data.size();
    }

    // متد clone متفاوت (deep copy)
    public List<List<Integer>> cloneData() {
        List<List<Integer>> cloned = new ArrayList<>();
        for (List<Integer> row : data) {
            cloned.add(new ArrayList<>(row));
        }
        return cloned;
    }
}
