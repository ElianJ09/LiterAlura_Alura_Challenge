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
    public <data> data obtainData(String json, Class<data> dataClass) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsArray = rootNode.get("results");

            if (resultsArray != null && !resultsArray.isEmpty()) {
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
    public <data> List<data> obtainArrayData(String json, Class<data> dataClass) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode resultsArray = rootNode.get("results");
            if (resultsArray != null && !resultsArray.isEmpty()) {
                List<data> resultList = new ArrayList<>();
                for (int i = 0; i < resultsArray.size(); i++) {
                    JsonNode firstResult = resultsArray.get(i).get("authors").get(0);
                    data result = objectMapper.treeToValue(firstResult, dataClass);
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

