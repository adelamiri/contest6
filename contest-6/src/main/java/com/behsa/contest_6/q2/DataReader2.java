package com.behsa.contest_6.q2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataReader2 {
    public Dateset2 getInputDataset(String input) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<int[]> data = mapper.readValue(
                input,
                new TypeReference<List<int[]>>() {
                }
        );
        return new Dateset2(data);
    }
}
