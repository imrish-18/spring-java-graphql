package com.globallogic.library;

import com.globallogic.library.entities.Book;
import com.globallogic.library.exceptions.BookNotFoundException;
import com.globallogic.library.repositories.BookRepository;
import com.globallogic.library.services.impl.BookServiceImpl;
import graphql.com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TestBookServiceImpl {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test(expected = BookNotFoundException.class)
    public void testBookNotFound() throws BookNotFoundException {
        Mockito.when(bookRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());
        bookService.get(1);
    }

    @Test
    public void getBook() throws BookNotFoundException {
        Book book = Book.builder()
                .id(1)
                .bookTitle("Harry porter")
                .publishingYear(2000)
                .price(50)
                .ratings(4.5)
                .build();
        Mockito.when(bookRepository.findById(1))
                .thenReturn(Optional.of(book));
        Assert.assertEquals("Harry porter", bookService.get(1).getBookTitle());
    }

    @Test
    public void testCreateBook() {
        Book book = Book.builder()
                .bookTitle("Harry porter")
                .publishingYear(2000)
                .price(50)
                .ratings(4.5)
                .build();
        Mockito.when(bookRepository.save(Mockito.any()))
                .thenAnswer(invocationOnMock -> {
                    Book bookMock = (Book) invocationOnMock.getArguments()[0];
                    bookMock.setId(1);
                    return bookMock;
                });
        Assert.assertEquals(1, bookService.create(book).getId());
    }

    @Test
    public void testGetAllBooks() {
        Book bookOne = Book.builder()
                .bookTitle("Harry porter")
                .publishingYear(2000)
                .price(50)
                .ratings(4.5)
                .build();
        Book bookTwo = Book.builder()
                .bookTitle("Harry porter 2")
                .publishingYear(2002)
                .price(50)
                .ratings(4.6)
                .build();
        List<Book> books = ImmutableList.of(bookOne, bookTwo);

        Mockito.when(bookRepository.findAll())
                .thenReturn(books);
        Assert.assertEquals(2, bookService.getAll().size());
    }
}
