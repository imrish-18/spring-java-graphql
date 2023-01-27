package com.assignment.graphql.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


import com.assignment.graphql.model.User;
import com.assignment.graphql.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Service

/** The Constant log. */
@Slf4j
@Validated
@Transactional
public class UserService {
    
    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /**
     * Creates the user.
     *
     * @param user the user
     * @return the mono
     */
    public Mono<User> createUser(@Valid User user)
    {
        user.setUserName(user.getFirstName()+user.getLastName());
        log.info("[USERS] saving user details " + user.getEmailId());
         return userRepository.save(user).onErrorReturn(user);

    }
    
    /**
     * Authenticate user with user name.
     *
     * @param userName the user name
     * @param password the password
     * @return the mono
     */
    public Mono<String> authenticateUserWithUserName(String userName, String password) {
    	
        Mono<User> user = userRepository.findByUserNameAndPassword(userName, password)
        		.switchIfEmpty(Mono.empty());
        return user.hasElement()
                .map(isNotEmpty -> {
                    if (isNotEmpty) 
                        return "user Logged In successfully";
                    else {
                        return " invalid userName and password..Please try again..";
                    }
                });
    }
    
    /**
     * Gets the user by id.
     *
     * @param id the id
     * @return the user by id
     */
    public Mono<User> getUserById(String user_id)
    {
    	 return userRepository.findById(user_id);
 			   			   		     		
    }
    }

