package com.globallogic.library.services;

import com.globallogic.library.entities.Author;
import com.globallogic.library.exceptions.AuthorNotFoundException;

import java.util.List;

public interface AuthorService {
    /**
     * Creates author entry in Database
     * @param book
     * @return Author
     */
    Author create(Author book);

    /**
     * Gets all author details from Database
     * @return Authors
     */

    List<Author> getAll();

    /**
     * Gets author details by Id
     * @param authorId
     * @return Author
     */

    Author get(int authorId) throws AuthorNotFoundException;
}
