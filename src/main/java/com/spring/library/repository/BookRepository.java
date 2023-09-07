package com.spring.library.repository;

import com.spring.library.model.Author;
import com.spring.library.model.Book;
import com.spring.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthor(Author author);
    List<Book> findByUser(User user);
}
