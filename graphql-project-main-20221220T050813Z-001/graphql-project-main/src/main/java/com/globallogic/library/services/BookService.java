package com.globallogic.library.services;

import com.globallogic.library.entities.Book;
import com.globallogic.library.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {

    /**
     * Creates book entry in database
     * @param book
     * @return Book
     */
    Book create(Book book);

    /**
     * Gets all book details from database
     * @return Books
     */
    List<Book> getAll();

    /**
     * Get book details by book id
     * @param bookId
     * @return Book
     */
    Book get(int bookId) throws BookNotFoundException;

    /**
     * Gets all books with title,ratings and publishing year
     * @param bookTitle
     * @param ratings
     * @param publishingYear
     * @return Books
     */
    List<Book> getBookByTitleRatingsAndPublishingYear(String bookTitle,double ratings,int publishingYear);
}
