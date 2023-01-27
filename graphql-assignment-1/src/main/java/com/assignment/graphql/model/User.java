package com.assignment.graphql.model;
import org.springframework.data.mongodb.core.mapping.Document;

import com.assignment.graphql.input.Gender;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@Document

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Gets the email id.
 *
 * @return the email id
 */
@Getter

/**
 * Sets the email id.
 *
 * @param emailId the new email id
 */
@Setter

/**
 * Instantiates a new user.
 */
@NoArgsConstructor

/**
 * Instantiates a new user.
 *
 * @param Id the id
 * @param firstName the first name
 * @param lastName the last name
 * @param userName the user name
 * @param password the password
 * @param Contact the contact
 * @param gender the gender
 * @param emailId the email id
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Builder

public class User {

    /** The Id. */
    @org.springframework.data.annotation.Id
    @org.springframework.lang.NonNull
    private String Id;
    
    /** The first name. */
    private String firstName;
    
    /** The last name. */
    private String lastName;
    
    /** The user name. */
    private String userName;
    
    /** The password. */
    @NotBlank
    @org.springframework.lang.NonNull
    @Size(min = 8,max=20,message = "password length must be between 8 and 20")
    private String password;
    
    /** The Contact. */
   @Size(min = 10 ,max = 10,message = "please enter 10 digit valid number")
    private String contact;
    
    /** The gender. */
    private Gender gender;
    
    /** The email id. */
    @Email
    @org.springframework.lang.NonNull
    @Pattern(regexp = "([a-z])+@([a-z])+\\.com", message = "Email should match the pattern a-z @ a-z .com")
    private String emailId;
}
