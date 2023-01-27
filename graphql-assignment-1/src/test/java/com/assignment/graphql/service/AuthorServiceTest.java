package com.assignment.graphql.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import com.assignment.graphql.model.Author;
import com.assignment.graphql.repository.AuthorRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthorServiceTest.
 */
@WebFluxTest
public class AuthorServiceTest {

	/** The author service. */
	@InjectMocks
	private AuthorService authorService;
	
	/** The author repository. */
	@Mock
	private AuthorRepository authorRepository;
	
	 /**
 	 * Test create author.
 	 */
 	@Test
	    public void testCreateAuthor() {
	        Author author = Author.builder()
	                .numberOfBooksWritten(7)
	                .authorName("kumar,Mittal")
	                .age(49)
	                .build();
	        Mockito.when(authorRepository.save(Mockito.any()))
	                .thenReturn(Mono.just(author));
	  	  StepVerifier.create(authorService.createAuthor(author))
		  .assertNext(authr-> 
		  {
			  assertNotNull(authr,"value should not be null");
			  
		  }).
		  verifyComplete();
	 
	    }
	 
 	/**
 	 * Gets the author by id test.
 	 *
 	 * @return the author by id test
 	 */
 	@Test 
	  public void getAuthorByIdTest() {
	  Mockito.when(authorRepository.findById(Mockito.anyString())).thenReturn(Mono.
			  just(new Author()));
			  
			  StepVerifier.create(authorService.getAuthorById(Mockito.anyString()))
			  .assertNext(res -> 
			  {
				  assertNotNull(res,"value should not be null");
			  }).
			  verifyComplete();
			  
			  
			  }
	  
  	/**
  	 * Gets the all authors test.
  	 *
  	 * @return the all authors test
  	 */
  	@Test 
	  public void getAllAuthorsTest() {
		  Mockito.when(authorRepository.findAll()).thenReturn(Flux.just(new Author())); 
		  
		  StepVerifier.create(authorService.getAllAuthors()).
		  assertNext(res ->
		  assertNotNull(res)).
		  verifyComplete(); 
		  }
}
