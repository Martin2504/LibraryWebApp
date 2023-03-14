package com.sparta.librarywebapp.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.boot.jaxb.hbm.internal.RepresentationModeConverter;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", nullable = false)
    private Integer id;

    @Size(max = 40)
    @Column(name = "full_name", length = 40)
    private String fullName;

    // Creating the relationship between the book and the author on this side too.
    // The One-to-Many relationship.
    @OneToMany(mappedBy = "author",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}