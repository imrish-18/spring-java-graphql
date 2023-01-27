package com.assignment.graphql.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import com.assignment.graphql.input.Gender;
import com.assignment.graphql.model.User;
import com.assignment.graphql.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceTest.
 */
@WebFluxTest
public class UserServiceTest {
	
	/** The user service. */
	@InjectMocks
	private UserService userService;
	
	/** The user repository. */
	@Mock
	private UserRepository userRepository;

	/**
	 * Test create user.
	 */
	@Test
	public void testCreateUser() {
		 User user = User.builder()
	                .firstName("rishabh")
	                .lastName("sharma")
	                .userName("rishabhsharma")
	                .emailId("rish.sharma@yahooo.com")
	                .password("password123")
	                .gender(Gender.Male)
	                .contact("896757490")
	                .Id("1")
	                .build();
	       Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));
	        
			  StepVerifier.create(userService.createUser(user))
			  .assertNext(users-> 
			  {
				  assertNotNull(users,"value should not be null");
			      Assertions.assertEquals(users.getPassword(), "password123");
				  Assertions.assertEquals(users, user);
				  
			  }).
			  verifyComplete();
	}
	
	 /**
 	 * Gets the user by id test.
 	 *
 	 * @return the user by id test
 	 */
 	@Test 
	  public void getUserByIdTest() {
	  Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Mono.
			  just(new User()));
			  
			  StepVerifier.create(userService.getUserById(Mockito.anyString()))
			  .assertNext(user -> 
			  {
				  assertNotNull(user,"value should not be null");
			  }).
			  verifyComplete();
			  
	 }
	 
 	/**
 	 * Authenticate user with user name test.
 	 */
 	@Test
	 public void authenticateUserWithUserNameTest() 
 	{
		 Mockito.when(userRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString()))
		 .thenReturn(Mono.just(new User()));
		 
		 StepVerifier.create(userService.authenticateUserWithUserName(Mockito.anyString(), Mockito.anyString()))
		 .assertNext(user->{
			
			 assertNotNull(user);
		 }); 
	 }
	/**
 	 * Authenticate user with user name test.
 	 */
 	@Test
	 public void authenticateUserWithUserNameInvalidTest() 
 	{
		 Mockito.when(userRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString()))
		 .thenReturn(Mono.just(new User()));
		 
		 StepVerifier.create(userService.authenticateUserWithUserName(Mockito.anyString(), Mockito.anyString()))
		 .assertNext(user->{
			 Assertions.assertEquals(user,"Invalid userName or Password..Please enter valid userName and password");
			 assertNotNull(user);
		 }); 
	 }
}
