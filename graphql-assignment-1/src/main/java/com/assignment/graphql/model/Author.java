package com.assignment.graphql.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// TODO: Auto-generated Javadoc

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new author.
 */
@NoArgsConstructor

/**
 * Instantiates a new author.
 *
 * @param id the id
 * @param authorName the author name
 * @param age the age
 * @param numberOfBooksWritten the number of books written
 * @param books the books
 */
@AllArgsConstructor

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Builder
@Document

public class Author {
    
    /** The id. */
    @Id
    private String author_id;
    
    /** The author name. */
    @org.springframework.lang.NonNull
    @NotBlank
    private String authorName;
    
    /** The age. */
    @NotNull
    @Size(min=18,max=80,message = "age must be between 18 and 80")
    private int age;
    
    /** The number of books written. */
    private int numberOfBooksWritten;
    
    /** The books. */
    private List<Book> books;

    /**
     * Instantiates a new author.
     *
     * @param id the id
     */
    public Author(String author_id) {
        this.author_id = author_id;
    }
}
