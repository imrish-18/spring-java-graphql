package com.assignment.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * Gets the author.
 *
 * @return the author
 */
@Getter

/**
 * Sets the author.
 *
 * @param author the new author
 */
@Setter

/**
 * Instantiates a new book.
 */
@NoArgsConstructor

/**
 * Instantiates a new book.
 *
 * @param id the id
 * @param bookTitle the book title
 * @param publishingYear the publishing year
 * @param ratings the ratings
 * @param price the price
 * @param author the author
 */
@AllArgsConstructor
@Document

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Builder
public class Book {
    
    /** The id. */
	@NotNull
	@NotBlank
    @Id
    private String book_id;
    
	@NotBlank
    /** The book title. */
    private String bookTitle;
    
    /** The publishing year. */
    private int publishingYear;
    
    /** The ratings. */
    private double ratings;
    
    /** The price. */
    private double price;
    
    /** The author. */
    @NotNull
    private Author author;
}
