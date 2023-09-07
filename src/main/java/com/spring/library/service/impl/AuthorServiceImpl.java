package com.spring.library.service.impl;

import com.spring.library.model.Author;
import com.spring.library.model.Book;
import com.spring.library.repository.AuthorRepository;
import com.spring.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author addBook(Author author, Book book) {
        if(authorRepository.existsById(author.getId())){
            Author author1 = authorRepository.getOne(author.getId());
            author1.getBooks().add(book);
            authorRepository.save(author1);
            return author1;
        }
        return new Author();
    }

    @Override
    public List<Book> findAllBooks(Author author) {
        return author.getBooks();
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author readById(long id) {
        return authorRepository.findById(id).orElse(new Author());
    }
}
