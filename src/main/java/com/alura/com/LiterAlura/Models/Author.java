package com.alura.com.LiterAlura.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int dateOfBirth;
    private int dateOfDeath;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;


    public Author() {
    }

    public Author(String name, int nacimiento, int deceso) {
        this.name = name;
        this.dateOfBirth = nacimiento;
        this.dateOfDeath = deceso;
    }

    public List<Book> getBooks() {
        if (books == null) {
            books = new ArrayList<>();
        }
        return books;
    }

    // Getters and Setters
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public int getDateOfDeath() {
        return dateOfDeath;
    }
    public void setDateOfDeath(int dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    @Override
    public String toString() {
        String authorInfo = """
                ----------------------------
                Author: %s
                Date of birth: %s
                Date of Death: %s
                Books: %s
                -----------------------------
                """;

        String bookFormat = books.stream()
                .map(book -> book.getTittle())
                .collect(Collectors.joining(", "));

        return String.format(authorInfo, name, dateOfBirth, dateOfDeath, bookFormat);
    }
}
