package com.alura.com.LiterAlura.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record authorInfo(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") int dateOfBirth,
        @JsonAlias("death_year") int dateOfDeath) {
}
