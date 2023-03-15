package com.sparta.librarywebapp.model.controller;

import com.sparta.librarywebapp.model.entities.Author;
import com.sparta.librarywebapp.model.entities.Book;
import com.sparta.librarywebapp.model.repositories.AuthorRepository;
import com.sparta.librarywebapp.model.repositories.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebsiteController {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public WebsiteController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/createAuthorPage")
    public String createAuthorPage(Model model) {
        Author author = new Author();
        model.addAttribute("authorToCreate", author);
        return "author-create-form";
    }

    // Receives Author object to create from a form.
    @PostMapping("/createAuthor")
    public String createAuthor(@ModelAttribute("authorToCreate") Author createdAuthor) {    // ModelAttribute reads the object form the form instead of needing to dissect the URL.
        authorRepository.save(createdAuthor);
        return "welcome";
    }

    @GetMapping("/createBookPage")
    public String createBookPage(Model model) {
        Book book = new Book();
        model.addAttribute("bookToCreate", book);
        return "book-create-form";
    }

    // Receives Book object to create from a form.
    @PostMapping("/createBook")
    public String createBook(@ModelAttribute("bookToCreate") Book book) {
        Optional<Author> optionalAuthor = authorRepository.findAuthorByFullName(book.getAuthor().getFullName());
        Author author;
        if (optionalAuthor.isPresent()) {
            author = optionalAuthor.get();
        } else {
            author = authorRepository.save(book.getAuthor());
        }
        book.setAuthor(author);
        bookRepository.save(book);
        return "welcome";
    }

    @GetMapping("/findAuthorPage")
    public String findAuthorPage() {
        return "find-author-page";
    }

    @GetMapping("/findAuthor")
    public String findAuthor(Model model, @RequestParam Integer id) {
        model.addAttribute("author", authorRepository.findById(id).orElse(null));
        return "author";
    }

    @GetMapping("/findBooksPage")
    public String findBookPage() {
        return "find-book-page";
    }

    @GetMapping("/findBook")
    public String findBook(Model model, @RequestParam Integer id) {
        model.addAttribute("book", bookRepository.findById(id).orElse(null));
        return "book";
    }

    // Show all books
    @GetMapping("/allBooks")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("bookToCreate", new Book());
        return "books";
    }

    // Requesting to edit a book
    @GetMapping("/book/edit/{id}")
    public String getAuthorToEdit(@PathVariable Integer id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("bookToEdit", book);
        return "book-edit-form";
    }

    // Receives object to edit from a form.
    @PostMapping("/updateBook")
    public String updateAuthor(@ModelAttribute("bookToEdit") Book editedBook) {
        bookRepository.saveAndFlush(editedBook);
        return "welcome";
    }

    @PostMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "welcome";
    }

    // Get all authors
    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

}
