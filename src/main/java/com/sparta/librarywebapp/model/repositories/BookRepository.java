package com.sparta.librarywebapp.model.repositories;

import com.sparta.librarywebapp.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthor_Id(Integer id);

}