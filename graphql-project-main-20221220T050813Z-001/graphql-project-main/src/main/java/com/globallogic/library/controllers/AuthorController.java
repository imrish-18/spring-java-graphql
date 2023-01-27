package com.globallogic.library.controllers;

import com.globallogic.library.entities.Author;
import com.globallogic.library.exceptions.AuthorNotFoundException;
import com.globallogic.library.model.AuthorInput;
import com.globallogic.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * Controller for create,get,serach of book details
 */
@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @MutationMapping("createAuthor")
    public Author create(@Argument(name = "author") AuthorInput authorInput) {
        Author author = Author.builder()
                .authorName(authorInput.getAuthorName())
                .age(authorInput.getAge())
                .numberOfBooksWritten(authorInput.getNumberOfBooksWritten())
                .build();
        return this.authorService.create(author);
    }

    @QueryMapping("allAuthors")
    public List<Author> getAll() {
        return this.authorService.getAll();
    }

    @QueryMapping("getAuthor")
    public Author get(@Argument int authorId) throws AuthorNotFoundException {
        return this.authorService.get(authorId);
    }

}

