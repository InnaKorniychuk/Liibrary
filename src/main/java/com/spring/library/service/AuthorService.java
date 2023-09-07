package com.spring.library.service;

import com.spring.library.model.Author;
import com.spring.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AuthorService {
    Author create(Author author);
    Author addBook(Author author, Book book);
    List<Book> findAllBooks(Author author);
    List<Author> getAll();
    Author readById(long id);
}
