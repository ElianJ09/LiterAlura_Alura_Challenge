package com.alura.com.LiterAlura.Repositories;

import com.alura.com.LiterAlura.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface bookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitleContainingIgnoreCase(String bookName);
}
