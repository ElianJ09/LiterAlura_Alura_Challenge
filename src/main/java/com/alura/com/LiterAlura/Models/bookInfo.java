package com.alura.com.LiterAlura.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record bookInfo(
        @JsonAlias("title") String title,
        @JsonAlias("name") Author author,
        @JsonAlias("languages") List<String> language,
        @JsonAlias("download_count") Double downloads){
}