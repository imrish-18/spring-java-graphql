package com.assignment.graphql.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.RepositoryDefinition;
import com.assignment.graphql.model.User;
import reactor.core.publisher.Mono;

// TODO: Auto-generated Javadoc
@RepositoryDefinition(domainClass = User.class, idClass = String.class)
public interface UserRepository extends ReactiveMongoRepository<User,String>{
   
   /**
    * Find by user name and password.
    *
    * @param userName the user name
    * @param password the password
    * @return the flux
    */
   Mono<User> findByUserNameAndPassword(String userName, String password);
}
