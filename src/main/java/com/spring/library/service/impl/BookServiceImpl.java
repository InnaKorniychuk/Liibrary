package com.spring.library.service.impl;

import com.spring.library.model.Author;
import com.spring.library.model.Book;
import com.spring.library.model.User;
import com.spring.library.repository.BookRepository;
import com.spring.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    @Override
    public Book create(Book book) {
        if(!bookRepository.existsById(book.getId()))
            return bookRepository.save(book);

        return new Book();
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book readById(long id) {
        return bookRepository.findById(id).orElse(new Book());
    }

//    @Override
//    public Book findByAuthor(Author author) {
//        return bookRepository
//    }

//    @Override
//    public void delete(Book book) {
//
//    }

//    @Override
//    public Book findByUser(User user) {
//        return null;
//    }
}
