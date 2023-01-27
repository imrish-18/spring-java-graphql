package com.assignment.graphql.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import com.assignment.graphql.handler.UserHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class UserRouter.
 */
@Configuration
@EnableWebFlux
public class UserRouter {
    
    /**
     * Creates the user.
     *
     * @param userHandler the user handler
     * @return the router function
     */
    @Bean
    public RouterFunction<ServerResponse> createUser(UserHandler userHandler)
    {
        return RouterFunctions.route(RequestPredicates.POST("/create/user")
        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), 
        userHandler::createUser);
    }
    
    /**
     * Authenticate user.
     *
     * @param userHandler the user handler
     * @return the router function
     */
    @Bean
    public RouterFunction<ServerResponse> authenticateUser(UserHandler userHandler)
    {
        return RouterFunctions.
                route(RequestPredicates.GET("/authenticate/user/{userName}/{password}")
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
                userHandler::authenticateUser);
    }
    
    /**
     * Gets the user by id.
     *
     * @param userHandler the user handler
     * @return the user by id
     */
    @Bean
    public RouterFunction<ServerResponse> getUserById(UserHandler userHandler){
        return RouterFunctions.
                route(RequestPredicates.GET("/authenticate/user/{user_id}")
                                .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
                        userHandler::getUserById);
    }
}
