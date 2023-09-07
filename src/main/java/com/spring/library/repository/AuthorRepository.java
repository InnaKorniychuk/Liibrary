package com.spring.library.repository;

import com.spring.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.library.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    // Book findBookByTitle(Book book);

}
