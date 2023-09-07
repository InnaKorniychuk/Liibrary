package com.spring.library.service.impl;

import com.spring.library.model.Book;
import com.spring.library.model.User;
import com.spring.library.repository.BookRepository;
import com.spring.library.repository.UserRepository;
import com.spring.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    BookRepository bookRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository){
        this.userRepository=userRepository;
        this.bookRepository=bookRepository;
    }
    @Override
    public User create(User user) {
        if(userRepository.findById(user.getId()).isEmpty())
        return userRepository.save(user);

        return null;
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public void delete(long id) {
        if(userRepository.findById(id).isPresent())
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        if(userRepository.existsById(user.getId())){
            User userUpd = userRepository.getOne(user.getId());
//            userUpd.setId(user.getId());
            userUpd.setEmail(user.getEmail());
            userUpd.setBooks(user.getBooks());
            userUpd.setRole(user.getRole());
            userUpd.setPassword(user.getPassword());
            userUpd.setFirstName(user.getFirstName());
            userUpd.setLastName(user.getLastName());
            userRepository.save(userUpd);
            return userUpd;
        }

        return new User();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User addBook(User user,Book book){
//      if(userRepository.existsById(user.getId())&&!book.isHasOwner()){
//          User userUpd = userRepository.getOne(user.getId());
//          book.setHasOwner(true);
//          book.setUser(user);
//          bookRepository.save(book);
//          userUpd.getBooks().add(book);
//          userRepository.save(userUpd);
//          return userUpd;
//      }
        if(userRepository.existsById(user.getId())&&!book.isHasOwner()){
            User userUpd = userRepository.getOne(user.getId());
            book.setHasOwner(true);
            userUpd.getBooks().add(book);
            userRepository.save(userUpd);
            return userUpd;
        }
      return new User();
    }

    @Override
    public void givebackBook(User user, Book book) {
        if(userRepository.existsById(user.getId())&&book.isHasOwner()){
            book.setHasOwner(false);
            user.getBooks().remove(book);
            book.setUser(null);
        }
    }
}
