package com.sparta.librarywebapp.model.controller;

import com.sparta.librarywebapp.model.entities.Book;
import com.sparta.librarywebapp.model.repositories.AuthorRepository;
import com.sparta.librarywebapp.model.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    // Add both repos because we will need to reference them both in the methods.
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // Return all books.
    @GetMapping(value = "/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by author id
    @GetMapping(value = "/book/{id}")
    public List<Book> getBooksByAuthorId(@PathVariable Integer id) {
        return bookRepository.findBooksByAuthor_Id(id);
    }

    // Add a book.
    @PostMapping(value = "/book")
    public String addBook(@RequestBody Book book){
        System.out.println(book.getAuthor());
        if(book.getAuthor().getId() == null){
            authorRepository.save(book.getAuthor());
        }
        else if(!authorRepository.existsById(book.getAuthor().getId()))
            return "Invalid Id";
        else if(authorRepository.existsById(book.getAuthor().getId()))
            book.setAuthor(authorRepository.findById(book.getAuthor().getId()).get());
        bookRepository.save(book);
        return "Book " + book.getTitle() + " by " + book.getAuthor().getFullName();
    }


    @GetMapping(value = "/findbook/{id}")
    public List<Book> findBook (@PathVariable Integer id) {

        return bookRepository.findBooksByAuthor_Id(id);
    }


}
