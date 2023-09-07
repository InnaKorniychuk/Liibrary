package com.spring.library.service;

import com.spring.library.model.Book;
import com.spring.library.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface UserService {
    User create (User user);
    User findById(long id);
    void delete (long id);
    User update(User user);
    List<User> getAll();
    User addBook(User user,Book book);
    void givebackBook(User user, Book book);
}
