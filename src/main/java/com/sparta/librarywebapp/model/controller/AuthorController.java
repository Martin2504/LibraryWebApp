package com.sparta.librarywebapp.model.controller;

import com.sparta.librarywebapp.model.entities.Author;
import com.sparta.librarywebapp.model.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // Requesting to edit an author
    @GetMapping("/author/edit/{id}")
    public String getAuthorToEdit(@PathVariable Integer id, Model model) {
        Author author = authorRepository.findById(id).orElse(null);
        model.addAttribute("authorToEdit", author); // Creating model object which is sent to the form
        return "author-edit-form";
    }

    // Receives object to edit from a form.
    @PostMapping("/updateAuthor")
    public String updateAuthor(@ModelAttribute("authorToEdit")Author editedAuthor) {    // ModelAttribute reads the object form the form instead of needing to dissect the URL.
        authorRepository.saveAndFlush(editedAuthor);    // saveAndFlush swaps objects with the same id. (like an update)
        return "edit-success";
    }

    // Receives object to delete from a form
    @PostMapping("/author/delete/{id}")
    public String getAuthorToDelete(@PathVariable Integer id) {
        authorRepository.deleteById(id);
        return "delete-success";
    }


}
