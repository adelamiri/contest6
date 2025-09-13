package com.behsa.contest_6.q1;

import java.util.List;

public class Hypothesis {

    public static boolean evaluate(List<Boolean> h, Dateset inputDataset) {
        List<List<Integer>> lists = inputDataset.cloneData();
        for (int i = 0; i < h.size(); i++) {
            if (!h.get(i)) {
                List<Integer> record = lists.get(i);
                for (int i1 = 0; i1 < record.size(); i1++) {

                    Integer op = record.get(i1);
                    if (op == 1)
                        record.set(i1, -1);
                    else if (op == -1)
                        record.set(i1, 1);
                }
            }
        }
        return evaluate(h, lists);
    }

    private static boolean evaluate(List<Boolean> h, List<List<Integer>> lists) {
        for (List<Integer> record : lists) {
            for (int j = 0, recordSize = record.size(); j < recordSize; j++) {
                Integer integer = record.get(j);
                if (integer == 0)
                    continue;
                else if (integer == 1 && h.get(j))
                    continue;
                else if (integer == -1 && !h.get(j))
                    continue;
                else
                    return false;
            }
        }
        return true;
    }
}
