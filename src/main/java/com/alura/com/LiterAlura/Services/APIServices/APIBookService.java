package com.alura.com.LiterAlura.Services.APIServices;

import com.alura.com.LiterAlura.DTO.BookDTO;
import com.alura.com.LiterAlura.Models.Book;
import com.alura.com.LiterAlura.Repositories.bookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class APIBookService {
    @Autowired
    private bookRepository booksRepository;

    public List<BookDTO> getBooks() {
        return dataConverse(booksRepository.findAll());
    }

    public List<BookDTO> dataConverse(List<Book> books) {
        return books.stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getLanguage(),
                        book.getDownloads()
                ))
                .collect(Collectors.toList());
    }
}
