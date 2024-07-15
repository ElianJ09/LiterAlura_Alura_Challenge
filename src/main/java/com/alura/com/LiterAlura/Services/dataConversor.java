package com.alura.com.LiterAlura.Services;

import com.alura.com.LiterAlura.Services.Interfaces.interfaceDataConversor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class dataConversor implements interfaceDataConversor {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T obtainData(String json, Class<T> dataClass) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsArray = rootNode.get("results");

            if (!resultsArray.isNull() && resultsArray.isArray() && !resultsArray.isEmpty()) {
                JsonNode firstResult = resultsArray.get(0);
                System.out.println(firstResult);
                T result = objectMapper.treeToValue(firstResult, dataClass);
                System.out.println("Deserialized object: " + result);
                return result;
            } else {
                throw new RuntimeException("We cant find any book with that name in json!");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obtainArrayData(String json, Class<T> dataClass) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsArray = rootNode.get("results");

            if (!resultsArray.isEmpty()) {
                List<T> resultList = new ArrayList<>();
                for (JsonNode node : resultsArray) {
                    T result = objectMapper.treeToValue(node, dataClass);
                    resultList.add(result);
                }
                return resultList;
            } else {
                throw new RuntimeException("We cant find any book with that name in the array json!");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
