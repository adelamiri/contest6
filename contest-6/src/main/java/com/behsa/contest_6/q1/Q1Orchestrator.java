package com.behsa.contest_6.q1;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Q1Orchestrator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Q1Orchestrator.class);
    private final DataReader dataReader;

    public Q1Orchestrator(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    @PostConstruct
    public void init() {
        String input = """
                [
                [ 1,-1, 0],
                [ 0, 1, 0],
                [-1, 1, 1]
                ]
                """;
        int count = solve(input);
        LOGGER.info("Input dataset: {}\t response count:{}\n", input, count);
    }

    public int solve(String input) {
        AtomicInteger counter = new AtomicInteger();
        try {
            Dateset dataset = dataReader.getInputDataset(input);

            dataset.validate();

            Map<String, List<Boolean>> hypotheses = BooleanHypotheses.generateHypotheses(dataset.getSize());

            hypotheses.forEach((k, v) -> {
                boolean evaluate = Hypothesis.evaluate(v, dataset);
                if (evaluate) {
                    String s = generateResponse(v);
                    LOGGER.info("{} -> {} - {}", k, v, s);
                    counter.getAndIncrement();
                }
            });

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return counter.get();
    }

    private String generateResponse(List<Boolean> v) {
        StringBuilder sb = new StringBuilder();
        for (Boolean aBoolean : v) {
            if (aBoolean) {
                sb.append('1');

            } else
                sb.append('0');
        }
        return sb.toString();
    }
}
