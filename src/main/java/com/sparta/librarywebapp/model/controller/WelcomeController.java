package com.sparta.librarywebapp.model.controller;

import com.sparta.librarywebapp.model.entities.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class WelcomeController {

    @GetMapping("/")        // ...localhost:8080/
    public String welcome() {
        return "../static/welcome";     // Pointing to and returning the static directory's file.
    }

    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(defaultValue = "World") String name) {
                // Model is a connection layer between the controller and the template.

        // Adding attributes to the model and mapping them.
        model.addAttribute("dateTime", LocalDateTime.now());
        model.addAttribute("name", name);

        return "hello";
    }
}
