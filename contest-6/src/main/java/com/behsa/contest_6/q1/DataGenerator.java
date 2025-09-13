package com.behsa.contest_6.q1;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


@Component
public class DataGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataGenerator.class);
    private static final int[] VALUES = {-1, 0, 1};
    private static final Random RANDOM = new Random();
    private final Q1Orchestrator q1Orchestrator;

    public DataGenerator(Q1Orchestrator q1Orchestrator) {
        this.q1Orchestrator = q1Orchestrator;
    }

    public static List<List<Integer>> generateSquareMatrixAware(int n) {
        List<List<Integer>> matrix = new ArrayList<>();
        List<Boolean> booleans = generateBooleanList(n);
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i == j) row.add(1);
                else {
                    int value;
                    value = getValue(booleans.get(i), booleans.get(j));
                    row.add(value);
                }
            }
            matrix.add(row);
        }

        return matrix;
    }

    private static boolean valid(int value) {
        return false;
    }

    private static int getValue(Boolean speak, Boolean about) {
        int value;
        value = VALUES[RANDOM.nextInt(VALUES.length)];
        if (value != 0) {
            while (!validOp(speak, about, value))
                value = VALUES[RANDOM.nextInt(VALUES.length)];
        }
        return value;
    }

    private static boolean validOp(Boolean speak, Boolean about, int value) {
        if (speak == about && value == 1) return true;
        else if (!speak && about && value == -1) return true;
        else return speak && !about && value == -1;
    }

    @PostConstruct
    void init() {
        LOGGER.info("Initializing data generator");
        init(2);
        init(3);
        init(5);
        init(7);
    }

    void init(int n) {
        for (int i = 0; i < 100000; i++) {
            List<List<Integer>> lists = generateSquareMatrixAware(3 * n);
            while (!valid(lists)) {
                lists = generateSquareMatrixAware(3 * n);
            }
            int solve = q1Orchestrator.solve(lists.toString());
            if (solve > 0) {
                LOGGER.info("generator data:{},count:{}", lists, solve);
                return;
            }
        }
    }

    private boolean valid(List<List<Integer>> lists) {
        int n = lists.size() / 3;
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get(i);
            int count = 0;
            for (Integer num : integers) {
                if (num != null && num == -1) {
                    count++;
                }
            }
            if (count > n)
                return false;
        }
        return true;
    }

    public static List<Boolean> generateBooleanList(int n) {
        if (n % 3 != 0) {
            throw new IllegalArgumentException("n باید مضربی از 3 باشد");
        }

        int falseCount = n / 3;
        int trueCount = n - falseCount;

        List<Boolean> list = new ArrayList<>();

        // اضافه کردن falseها
        for (int i = 0; i < falseCount; i++) {
            list.add(false);
        }

        // اضافه کردن trueها
        for (int i = 0; i < trueCount; i++) {
            list.add(true);
        }
        Collections.shuffle(list);
        return list;
    }
}
