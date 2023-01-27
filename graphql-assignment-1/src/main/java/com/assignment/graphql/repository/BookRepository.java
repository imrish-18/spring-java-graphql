package com.assignment.graphql.repository;

import com.assignment.graphql.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.RepositoryDefinition;


/**
 * The Interface BookRepository.
 */
@RepositoryDefinition(domainClass = Book.class, idClass = String.class)
public interface BookRepository extends ReactiveMongoRepository<Book,String> {
}
