package com.alura.com.LiterAlura.Repositories;

import com.alura.com.LiterAlura.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface authorRepository extends JpaRepository<Author,Long> {
    Optional<Autor> findByNombreContainingIgnoreCase(String nombreAutor);
}