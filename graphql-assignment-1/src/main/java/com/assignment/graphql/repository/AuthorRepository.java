package com.assignment.graphql.repository;

import com.assignment.graphql.model.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.RepositoryDefinition;


/**
 * The Interface AuthorRepository.
 */
@RepositoryDefinition(domainClass = Author.class, idClass = String.class)
public interface AuthorRepository extends ReactiveMongoRepository<Author,String> {
}
