package com.sparta.librarywebapp.model.controller;

import com.sparta.librarywebapp.model.entities.Author;
import com.sparta.librarywebapp.model.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthorController {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Let's create an author
    @PostMapping(value = "/author")
    public String setAuthor(@RequestBody Author author) {   // The @RequestBody annotation means in the body of the
                                                            // HTTP request, there will be an author object.
        // Saving the author to the repository
        authorRepository.save(author);
        return "Author " + author.getFullName() + " added";
    }

    // Get author by id.
    @GetMapping("/author/{id}")
    public String getAuthorById(Model model, @PathVariable Integer id) {
        model.addAttribute("author", authorRepository.findById(id).orElse(null));
        return "author";    // This calls the template
    }

    // Get all authors
    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

}
