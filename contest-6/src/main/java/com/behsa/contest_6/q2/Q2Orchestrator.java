package com.behsa.contest_6.q2;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class Q2Orchestrator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Q2Orchestrator.class);
    private final DataReader2 dataReader;

    public Q2Orchestrator(DataReader2 dataReader) {
        this.dataReader = dataReader;
    }

    @PostConstruct
    void postConstruct() throws JsonProcessingException {
        LOGGER.info("Q2Orchestrator called");
        int n = 10;
        String pointsString = """
                [
                [0, 0],
                [4, 2],
                [6, 6],
                [0 ,5],
                [9,9]
                ]
                """;

        Dateset2 inputDataset = dataReader.getInputDataset(pointsString);
        solve(n, n, inputDataset.data());

        generate(5);
        generate(10);
        generate(20);

    }

    private static void generate(int count) {
        int n = count * 4;
        List<int[]> randomPoints = GenRandomPoints.generateRandomPoints(n, n, count);
        solve(n, n, randomPoints);
    }

    private static void solve(int n, int m, List<int[]> data) {
        int[][] matrix = generateMatrix(n, m, data);
//        MatrixFromPoints.printMatrix(matrix);
        int footPrint = MatrixFromPoints.sumMatrix(matrix);
        LOGGER.info("foot print: {}", footPrint);
    }

    private static int[][] generateMatrix(int n, int m, List<int[]> points) {
        int[][] matrix = MatrixFromPoints.generateMatrixWithPaths(n, m, points);

        String output = points.stream()
                .map(Arrays::toString)
                .collect(Collectors.joining(", "));
        LOGGER.info(output);
        return matrix;
    }
}
