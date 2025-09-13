package com.behsa.contest_6.q1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataReader {
    public Dateset getInputDataset(String input) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<List<Integer>> data = mapper.readValue(
                input,
                new TypeReference<List<List<Integer>>>() {}
        );

        return new Dateset(data);
    }
}
