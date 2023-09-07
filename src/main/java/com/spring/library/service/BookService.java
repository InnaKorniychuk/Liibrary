package com.spring.library.service;

import com.spring.library.model.Author;
import com.spring.library.model.Book;
import com.spring.library.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book create(Book book);
    List<Book> getAll();
    Book readById(long id);
}
