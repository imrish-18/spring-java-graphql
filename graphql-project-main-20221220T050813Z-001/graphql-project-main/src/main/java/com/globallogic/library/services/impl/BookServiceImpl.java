package com.globallogic.library.services.impl;

import com.globallogic.library.entities.Book;
import com.globallogic.library.exceptions.BookNotFoundException;
import com.globallogic.library.repositories.BookRepository;
import com.globallogic.library.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {
        log.info("[BOOKSVC] saving book details {} " + book.getBookTitle());
        return this.bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        log.info("[BOOKSVC] fetching all book details");
        return this.bookRepository.findAll();
    }

    @Override
    public Book get(int bookId) throws BookNotFoundException {
        log.info("[BOOKSVC] fetching book details of {}" + bookId);
        Optional<Book> optionalBook = this.bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException("Book you are looking for not found on server !!");
        }
        return optionalBook.get();
    }

    public List<Book> getBookByTitleRatingsAndPublishingYear(String bookTitle,double ratings,int publishingYear) {
        log.info("[BOOKSVC] fetching book details of {}{}{}" + bookTitle,ratings,publishingYear);
        return this.bookRepository.findByBookTitleContainingAndRatingsAndPublishingYear(bookTitle,ratings,publishingYear);
    }
}
