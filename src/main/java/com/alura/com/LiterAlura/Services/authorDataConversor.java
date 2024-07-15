package com.alura.com.LiterAlura.Services;

import com.alura.com.LiterAlura.Services.Interfaces.interfaceDataConversor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class authorDataConversor implements interfaceDataConversor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtainData(String json, Class<T> dataClass) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsArray = rootNode.get("results");

            if (!resultsArray.isEmpty()) {
                JsonNode firstResult = resultsArray.get(0).get("authors").get(0);
                return objectMapper.treeToValue(firstResult, dataClass);
            } else {
                throw new RuntimeException("We cant find any authors in json!");
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
                for (int i = 0; i < resultsArray.size(); i++) {
                    JsonNode firstResult = resultsArray.get(i).get("authors").get(0);
                    T result = objectMapper.treeToValue(firstResult, dataClass);
                    resultList.add(result);
                }
                return resultList;
            } else {
                throw new RuntimeException("We cant find any authors in array json!");
            }
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

