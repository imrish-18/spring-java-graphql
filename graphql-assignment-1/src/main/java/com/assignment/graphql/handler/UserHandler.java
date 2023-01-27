package com.assignment.graphql.handler;

import org.springframework.stereotype.Component;

import com.assignment.graphql.model.User;
import com.assignment.graphql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
@Component
public class UserHandler {
    
    @Autowired
    private UserService userService;
    /**
   * Data.
   *
   * @param serverRequest the server request
   * @return the mono
   */
  public Mono<ServerResponse> createUser(ServerRequest serverRequest) {

    return serverRequest.bodyToMono(User.class).
    		flatMap(user->{
          return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        		  .body(userService.createUser(user), User.class);
              })
    		.onErrorResume(e -> Mono.just("Error " + e.getMessage())
    		        .flatMap(s -> ServerResponse.status(HttpStatusCode.valueOf(400))
    		                .contentType(MediaType.APPLICATION_JSON)
    		                .bodyValue(s)));
    }

    public Mono<ServerResponse> authenticateUser(ServerRequest serverRequest){
        final String userName=serverRequest.pathVariable("userName");
        final String password=serverRequest.pathVariable("password");
        return  ServerResponse.ok().
                body(userService.authenticateUserWithUserName(userName,password),String.class);
    }
    
    public Mono<ServerResponse> getUserById(ServerRequest serverRequest){
    	 final String user_id = serverRequest.pathVariable("user_id");

         return userService.getUserById(user_id)
                 .flatMap(response -> ServerResponse.ok().body(Mono.just(response), User.class))
                 .switchIfEmpty(Mono.error(new Exception("data is not present...")))
                 .switchIfEmpty(ServerResponse.noContent().build())
                 .onErrorResume(err -> ServerResponse.status(204).contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(err), Throwable.class));
    }
    
}
