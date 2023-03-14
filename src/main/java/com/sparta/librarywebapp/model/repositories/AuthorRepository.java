package com.sparta.librarywebapp.model.repositories;

import com.sparta.librarywebapp.model.entities.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findAuthorByFullName(String name);

    // For Updating and Deleting you need these two extra annotations.
    @Modifying
    @Transactional
    @Query(value = "UPDATE authors SET full_name = :newName where author_id = :id", nativeQuery = true)
    void updateAuthorNameById(String newName, Integer id);

    @Modifying
    @Transactional
    void deleteAuthorByFullName(String name);

}