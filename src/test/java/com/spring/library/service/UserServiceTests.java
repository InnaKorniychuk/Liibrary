package com.spring.library.service;

import com.spring.library.model.Book;
import com.spring.library.model.User;
import com.spring.library.repository.BookRepository;
import com.spring.library.repository.UserRepository;
import com.spring.library.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository,bookRepository);
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.create(user);

        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    public void createUserAlreadyExists() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User createdUser = userService.create(user);

        assertNull(createdUser);
    }

    @Test
    public void findUserById() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(userId);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
    }

    @Test
    public void deleteUser() {
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setEmail("inna@gmail.com");
        user.setLastName("Korniychuk");
        user.setFirstName("Inna");

        when(userRepository.getOne(userId)).thenReturn(user);

        userService.delete(userId);
        assertEquals(false,userRepository.existsById(1L));

    }

    @Test
    public void updateUser() {
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setEmail("inna@gmail.com");

        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.getOne(userId)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setEmail("updated@gmail.com");

        User result = userService.update(updatedUser);

        assertEquals(updatedUser.getEmail(), result.getEmail());
    }

    @Test
    public void getAllUsers() {
        User user1 = new User();
        user1.setEmail("inna@gmail.com");
        User user2 = new User();
        user2.setEmail("inna1@gmail.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAll();

        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void addBookToUser() {
        User user = new User();
        user.setId(1L);
        user.setBooks(new ArrayList<>());

        Book book = new Book();
        book.setId(1L);

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getOne(1L)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.addBook(user, book);

        List<Book> userBooks = user.getBooks();
        assert userBooks != null;
        assert userBooks.size() == 1;
        assert userBooks.get(0).equals(book);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void deleteBookFromUser(){
        User user = new User();
        user.setId(1L);
        user.setBooks(new ArrayList<>());

        Book book = new Book();
        book.setId(1L);

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getOne(1L)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.addBook(user, book);
        userService.givebackBook(user,book);

        List<Book> userBooks = user.getBooks();
        assertEquals(0,userBooks.size());
    }

}