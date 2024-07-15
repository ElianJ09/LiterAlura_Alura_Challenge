package com.alura.com.LiterAlura.Principal;

import com.alura.com.LiterAlura.Models.Author;
import com.alura.com.LiterAlura.Models.Book;
import com.alura.com.LiterAlura.Models.authorData;
import com.alura.com.LiterAlura.Models.bookData;
import com.alura.com.LiterAlura.Repositories.authorRepository;
import com.alura.com.LiterAlura.Repositories.bookRepository;
import com.alura.com.LiterAlura.Services.APIServices.APIservice;
import com.alura.com.LiterAlura.Services.authorDataConversor;
import com.alura.com.LiterAlura.Services.dataConversor;

import java.util.*;

public class Principal {

    private APIservice API = new APIservice();
    private dataConversor dataConversor = new dataConversor();
    private authorDataConversor authorDataConversor = new authorDataConversor();
    private Scanner scanner = new Scanner(System.in);
    private final String urlAPI = "https://gutendex.com/books/";
    private bookRepository booksRepository;
    private authorRepository authorsRepository;
    private List<Book> books;
    private List<Author> authors;
    private Optional<Author> searchedAuthor;

    public Principal(bookRepository booksRepository, authorRepository athosRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = athosRepository;
    }

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ________________________________________________________
                                        Select a option
                                        _______________
                            a)  Select a number from the menu (1-9)
                            b)  If you want to exit the program select 0
                    ________________________________________________________
                    
                    1   -   Search a book with the Title
                    2   -   Registered Books list
                    3   -   Registered Authors list
                    4   -   Living authors List by a certain date (year)
                    5   -   Book List by language
                    
                    _________________________EXTRAS_________________________
                    
                    6   -   Search Author by name
                    7   -   Top 10 books from the API (online)
                    8   -   Top 5 books from the DataBase
                    9   -   Authors of public law
                    0   -   Exit the program
                    
                    ________________________________________________________
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    showRegisterBooks();
                    break;
                case 3:
                    //showRegisterAuthors();
                    break;
                case 4:
                    //showLivingsAuthorsByYear();
                    break;
                case 5:
                    //showBooksByLanguage();
                    break;
                case 6:
                    //searchAuthorByName();
                    break;
                case 7:
                    //showTop10BooksAPI();
                    break;
                case 8:
                    //showTop5BooksDB();
                    break;
                case 9:
                    //showAuthorsPublicLaw();
                    break;
                case 0:
                    System.out.println("Thank you for using LiterAlura! \nExiting program...");
                    break;
                default:
                    System.out.println("Invalid option selected!");
            }
        }
        System.exit(0);
    }

    private String askToUser(){
        System.out.println("What is the name of the book");
        String bookName = scanner.nextLine();
        return getBooksData(bookName).toString();
    }

    //functions to get Books and Authors
    private bookData getBooksData(String bookName){
        var json = APIservice.obtainData(urlAPI + "?search="+ bookName.replace(" ", "+"));
        return dataConversor.obtainData(json, bookData.class);
    }
    private authorData getAuthorsData(String bookName) {
        var json = APIservice.obtainData(urlAPI + "?search="+ bookName.replace(" ", "+"));
        return authorDataConversor.obtainData(json, authorData.class);
    }

    //Option 1: search a book by title
    private void searchBookByTitle() {
        showRegisterBooks();
        String bookNameSelected = askToUser();

        books = books != null ? books : new ArrayList<>();

        Optional<Book> book = books.stream()
                .filter(l -> l.getTitle().toLowerCase()
                        .contains(bookNameSelected.toLowerCase()))
                .findFirst();
        if(book.isPresent()) {
            var bookFounded = book.get();
            System.out.println(bookFounded);
            System.out.println("The book was updated, try with another one!");
        }else{
            try {
                bookData bookData = getBooksData(bookNameSelected);
                System.out.println(bookData);

                if (bookData != null) {
                    authorData authorData = getAuthorsData(bookNameSelected);
                    if (authorData != null) {
                        List<Author> authors = authorsRepository.findAll();
                        authors = authors != null ? authors : new ArrayList<>();

                        Optional<Author> author = authors.stream()
                                .filter(a -> authorData.name() != null &&
                                        a.getName().toLowerCase().contains(authorData.name().toLowerCase()))
                                .findFirst();

                        Author AuthorCollected;
                        if (author.isPresent()) {
                            AuthorCollected = author.get();
                        } else {
                            AuthorCollected = new Author(
                                    authorData.name(),
                                    authorData.dateOfBirth(),
                                    authorData.dateOfDeath()
                            );
                            authorsRepository.save(AuthorCollected);
                        }

                        Book bookSelected = new Book(
                                bookData.title(),
                                AuthorCollected,
                                bookData.language() != null ? bookData.language() : Collections.emptyList(),
                                bookData.downloads()
                        );

                        books.add(bookSelected);
                        AuthorCollected.setBooks(books);

                        System.out.println(bookSelected);
                        booksRepository.save(bookSelected);

                        System.out.println("Book saved!");
                    } else {
                        System.out.println("We cant found the name of the author book");
                    }

                } else {
                    System.out.println("We cant found the book");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    //Option 2: show registered books that the user searched
    private void showRegisterBooks() {
        try{
            List<Book> books = booksRepository.findAll();
            books.stream()
                    .sorted(Comparator.comparing(Book::getDownloads))
                    .forEach(System.out::println);
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            books = new ArrayList<>();
        }
    }
}
