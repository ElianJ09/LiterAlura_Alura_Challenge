package com.alura.com.LiterAlura.Controller;

import com.alura.com.LiterAlura.DTO.BookDTO;
import com.alura.com.LiterAlura.Services.serviceAPIBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class booksController {
    @Autowired
    private serviceAPIBook booksService;

    @GetMapping()
    public List<BookDTO> getBooks(){
        return booksService.getBooks();
    }
}
