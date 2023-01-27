package com.assignment.graphql.router;

import com.assignment.graphql.model.Author;
import com.assignment.graphql.service.AuthorService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthorController.
 */
@Controller
@Slf4j
public class AuthorController {

    /** The author service. */
    @Autowired
    private AuthorService authorService;

    /**
     * Creates the.
     *
     * @param authorInput the author input
     * @return the mono
     */
    @MutationMapping("addAuthor")
    public Mono<Author> create(@Argument(name = "author") AuthorInput authorInput) {
    	log.info("[author] saving  author details ");
        Author author = Author.builder()
        		.author_id(authorInput.getId())               
                .authorName(authorInput.getAuthorName())
                .age(authorInput.getAge())
                .numberOfBooksWritten(authorInput.getNumberOfBooksWritten())
                .build();
        return authorService.createAuthor(author);
    }
    
    /**
     * Gets the all.
     *
     * @return the all
     */
    @QueryMapping("allAuthors")
    public Flux<Author> getAll() {
    	log.info("[author] fething all  author details ");
        return authorService.getAllAuthors();
    }

    /**
     * Gets the.
     *
     * @param authorId the author id
     * @return the mono
     */
    @QueryMapping("getAuthor")
    public Mono<Author> get(@Argument String authorId)  {
    	log.info("[author] fething  author details by Id ");
        return authorService.getAuthorById(authorId).
        		switchIfEmpty(Mono.error(new Exception("author details are not avaible ")));
    }

}

 class AuthorInput {
	 @Id
	private String id;
	private String authorName;
    private int age;
    private int numberOfBooksWritten;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

     public String getAuthorName() {
         return authorName;
     }

     public void setAuthorName(String authorName) {
         this.authorName = authorName;
     }

     public int getAge() {
         return age;
     }

     public void setAge(int age) {
         this.age = age;
     }

     public int getNumberOfBooksWritten() {
         return numberOfBooksWritten;
     }

     public void setNumberOfBooksWritten(int numberOfBooksWritten) {
         this.numberOfBooksWritten = numberOfBooksWritten;
     }
 }
