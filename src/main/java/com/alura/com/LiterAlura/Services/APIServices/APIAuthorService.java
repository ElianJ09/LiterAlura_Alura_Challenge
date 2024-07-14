package com.alura.com.LiterAlura.Services.APIServices;

import com.alura.com.LiterAlura.DTO.AuthorDTO;
import com.alura.com.LiterAlura.Models.Author;
import com.alura.com.LiterAlura.Repositories.authorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class APIAuthorService {
    @Autowired
    private authorRepository authorsRepository;

    public List<AuthorDTO> getAuthors() {
        return dataConverse(authorsRepository.findAll());
    }

    public List<AuthorDTO> dataConverse(List<Author> authors) {
        return authors.stream()
                .map(author -> new AuthorDTO(
                        author.getId(),
                        author.getName(),
                        author.getDateOfBirth(),
                        author.getDateOfDeath()
                ))
                .collect(Collectors.toList());
    }
}
