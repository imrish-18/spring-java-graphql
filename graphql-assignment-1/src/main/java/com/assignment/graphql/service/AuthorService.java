package com.assignment.graphql.service;

import com.assignment.graphql.model.Author;
import com.assignment.graphql.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthorService.
 */
@Service

/** The Constant log. */
@Slf4j
public class AuthorService {

    /** The author repository. */
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Creates the author.
     *
     * @param author the author
     * @return the mono
     */
    public Mono<Author> createAuthor(@Valid Author author){
        log.info("[AUTHOR] savings author details {} " + author.getAuthorName());
        return authorRepository.save(author);
    }

    /**
     * Gets the all authors.
     *
     * @return the all authors
     */
    public Flux<Author> getAllAuthors() {
        log.info("[AUTHOR] getting all author details ");
        return authorRepository.findAll();
    }
    
    /**
     * Gets the author by id.
     *
     * @param id the id
     * @return the author by id
     */
    public Mono<Author> getAuthorById(String author_id){
        return  authorRepository.findById(author_id).
                switchIfEmpty(Mono.error(new Exception("author is not in the server db")));
    }
}
