package com.alura.com.LiterAlura.Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
    private String language;
    private Double downloads;

    public Book() {
    }

    public Book(String tittle, Author author, List<String> languages, Double downloads) {
        this.title = tittle;
        this.author = author;
        this.language = languages != null && !languages.isEmpty() ? String.join(",", languages) : null;
        this.downloads = OptionalDouble.of(downloads).orElse(0);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public Double getDownloads() {
        return downloads;
    }
    public void setDownloads(Double downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        String bookInfo = """
                ------ Book -------
                Title: %s
                Author: %s
                Language: %s
                Downloads: %d
                -------------------
                """;
        return String.format(bookInfo, title, author.getName(), language, downloads);
    }
}
