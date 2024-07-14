package com.alura.com.LiterAlura;

import com.alura.com.LiterAlura.Principal.Principal;
import com.alura.com.LiterAlura.Repositories.authorRepository;
import com.alura.com.LiterAlura.Repositories.bookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private bookRepository booksRepository;

	@Autowired
	private authorRepository athosRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(booksRepository, athosRepository);
		principal.showMenu();
	}
}
