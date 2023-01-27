package com.assignment.graphql.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import com.assignment.graphql.model.Author;
import com.assignment.graphql.model.Book;
import com.assignment.graphql.repository.AuthorRepository;
import com.assignment.graphql.repository.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

// TODO: Auto-generated Javadoc
/**
 * The Class BookServiceTest.
 */
@WebFluxTest
public class BookServiceTest {

	
	/** The book service. */
	@InjectMocks
	private BookService bookService;
	
	/** The book repository. */
	@Mock
	private BookRepository bookRepository;
	
    /** The author service. */
    @Mock
	private AuthorService authorService;
	
	/** The author repository. */
	@Mock
	AuthorRepository authorRepository;
	
	/**
	 * Test publish book.
	 */
	@Test
	public void testPublishBook() {
		Author author=new Author();
		author.setBooks(new ArrayList<>());
		author.setAuthor_id("");
		Book book=Book.builder().
				author(author).
				bookTitle("")
				.book_id("")
				.price(2536)
				.publishingYear(1990)
				.ratings(10)
				.build();

		Mockito.when(authorRepository.findById(Mockito.anyString())).thenReturn(Mono.just(author));
		Mockito.when(authorService.getAuthorById(Mockito.anyString())).thenReturn(Mono.just(author));
		Mockito.when(authorService.createAuthor(author)).thenReturn(Mono.just(author));
		
       Mockito.when(bookRepository.save(book)).thenReturn(Mono.just(book));
		
		 StepVerifier.create(bookService.publishBook(book))
		  .assertNext(books-> 
		  {
			  assertNotNull(books,"value should not be null");
			  
		  }).
		  verifyComplete();
		
	}
	 
 	/**
 	 * Gets the book by id test.
 	 *
 	 * @return the book by id test
 	 */
 	@Test 
	  public void getBookByIdTest() {
	  Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(Mono.
			  just(new Book()));
			  
			  StepVerifier.create(bookService.getBookById(Mockito.anyString()))
			  .assertNext(book -> 
			  {
				  assertNotNull(book,"value should not be null");
			  }).
			  verifyComplete();
			  
	 }
	  
  	/**
  	 * Gets the all book test.
  	 *
  	 * @return the all book test
  	 */
  	@Test 
	  public void getAllBookTest() {
		  Mockito.when(bookRepository.findAll()).thenReturn(Flux.just(new Book())); 
		  
		  StepVerifier.create(bookService.getAllBooks()).
		  assertNext(res ->
		  assertNotNull(res)).
		  verifyComplete(); 
	  }
}
