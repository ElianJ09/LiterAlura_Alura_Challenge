package com.alura.com.LiterAlura.DTO;

import com.alura.com.LiterAlura.Models.Author;

public record BookDTO(
        Long id,
        String title,
        Author author,
        String language,
        Double downloads){
}
