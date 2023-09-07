package com.spring.library.service;

import com.spring.library.model.Author;
import com.spring.library.model.Book;
import com.spring.library.repository.AuthorRepository;
import com.spring.library.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AuthorServiceTests {
    @Mock
    private AuthorRepository authorRepository;
    private AuthorService authorService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        authorService=new AuthorServiceImpl(authorRepository);
    }
    @Test
    public void createAuthor() {
        Author author=new Author();
        author.setName("Taras Shevchenko");
        author.setId(2);
        when(authorRepository.findById(author.getId())).thenReturn(Optional.empty());
        when(authorRepository.save(author)).thenReturn(author);

        Author createdAuthor=authorService.create(author);
        assertNotNull(createdAuthor);
        assertEquals(author.getId(), createdAuthor.getId());
    }

    @Test
    public void findAuthorById() {
        long authorId = 1L;
        Author author=new Author();
        author.setId(authorId);

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        Author foundAuthor = authorService.readById(authorId);

        assertNotNull(foundAuthor);
        assertEquals(authorId, foundAuthor.getId());
    }

    @Test
    public void getAllAuthors(){
        Author author1=new Author();
        author1.setName("Taras Shevchenko");
        Author author2=new Author();
        author2.setName("Lina Kostenko");
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = authorService.getAll();

        assertNotNull(authors);
        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void addBookToAuthor() {
        Author author=new Author();
        author.setId(1);
        author.setBooks(new ArrayList<>());

        Book book = new Book();
        book.setId(1L);

        when(authorRepository.existsById(1L)).thenReturn(true);
        when(authorRepository.getOne(1L)).thenReturn(author);
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        authorService.addBook(author, book);

        List<Book> authorBooks = author.getBooks();
        assert authorBooks != null;
        assert authorBooks.size() == 1;
        assert authorBooks.get(0).equals(book);

        verify(authorRepository, times(1)).save(author);
    }
}
