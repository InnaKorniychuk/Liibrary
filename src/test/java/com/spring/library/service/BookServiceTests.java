package com.spring.library.service;

import com.spring.library.model.Book;
import com.spring.library.repository.BookRepository;
import com.spring.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BookServiceTests {
    @Mock
    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        bookService=new BookServiceImpl(bookRepository);
    }
    @Test
    public void createBook(){
        Book book=new Book();
        book.setId(2);
        book.setTitle("The Cinderella");
        bookRepository.save(book);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());
        when(bookRepository.save(book)).thenReturn(book);

        Book createdBook = bookService.create(book);

        assertNotNull(createdBook);
        assertEquals(book.getId(), createdBook.getId());
    }

    @Test
    public void findBookById() {
        long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book foundBook = bookService.readById(bookId);

        assertNotNull(foundBook);
        assertEquals(bookId, foundBook.getId());
    }

    @Test
    public void getAllBooks(){
        Book book1=new Book();
        book1.setId(1);
        book1.setTitle("Kobzar");
        Book book2=new Book();
        book2.setId(2);
        book2.setTitle("Alphabet");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAll();

        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }
}
