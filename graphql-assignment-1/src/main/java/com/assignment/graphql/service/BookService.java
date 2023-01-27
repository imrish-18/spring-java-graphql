package com.assignment.graphql.service;
import com.assignment.graphql.model.Book;
import com.assignment.graphql.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// TODO: Auto-generated Javadoc
/**
 * The Class BookService.
 */
@Service

/** The Constant log. */
@Slf4j
public class BookService {

    /** The book repository. */
    @Autowired
    private BookRepository bookRepository;
    
    /** The author service. */
    @Autowired
    private  AuthorService authorService;

    /**
     * Publish book.
     *
     * @param book the book
     * @return the mono
     */
    public Mono<Book> publishBook(@Valid Book book){   
        log.info("books has been added to author books collections");
       
        authorService.getAuthorById(book.getAuthor().getAuthor_id())
                .map(author->{
                    author.getBooks().add(book);
                    return author;
                }).flatMap(response->authorService.createAuthor(response));
        
        log.info("[BOOK] saving book details {} " + book.getBookTitle());
        
        return bookRepository.save(book);
    }
    
    /**
     * Gets the all book.
     *
     * @return the all book
     */
    public Flux<Book> getAllBooks(){
        log.info("[BOOK] fetching all book details");
        return bookRepository.findAll();
    }

    /**
     * Gets the book by id.
     *
     * @param id the id
     * @return the book by id
     */
    public Mono<Book> getBookById(String book_id){
        log.info("[BOOK] fetching book details of {}" + book_id);
        return bookRepository.findById(book_id).
                switchIfEmpty(Mono.error(new Exception("book you are looking not found in database")));
    }
}
