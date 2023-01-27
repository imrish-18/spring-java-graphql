package com.globallogic.library.controllers;

import com.globallogic.library.entities.Author;
import com.globallogic.library.entities.Book;
import com.globallogic.library.exceptions.BookNotFoundException;
import com.globallogic.library.model.BookInput;
import com.globallogic.library.repositories.BookRepository;
import com.globallogic.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controller for create,get,search of book details
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;


    @MutationMapping("createBook")
    public Book create(@Argument(name = "book") BookInput bookInput) {
        Book book = Book.builder()
                .bookTitle(bookInput.getBookTitle())
                .publishingYear(bookInput.getPublishingYear())
                .ratings(bookInput.getRatings())
                .price(bookInput.getPrice())
                .author(new Author(bookInput.getAuthorId()))
                .build();
        return this.bookService.create(book);
    }

    @QueryMapping("allBooks")
    public List<Book> getAll() {
        return this.bookService.getAll();
    }

    @QueryMapping("getBook")
    public Book get(@Argument int bookId) throws BookNotFoundException {
        return this.bookService.get(bookId);
    }
    @QueryMapping("getBookByTitleRatingsAndPublishingYear")
    public List<Book> getBookByTitleRatingsAndPublishingYear(@Argument String bookTitle,@Argument double ratings,@Argument int publishingYear) {
        return this.bookService.getBookByTitleRatingsAndPublishingYear(bookTitle,ratings,publishingYear);
    }

}

