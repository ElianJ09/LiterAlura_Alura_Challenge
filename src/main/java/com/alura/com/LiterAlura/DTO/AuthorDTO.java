package com.alura.com.LiterAlura.DTO;

public record AuthorDTO(
        Long id,
        String name,
        int dateOfBirth,
        int dateOfDeath) {
}
