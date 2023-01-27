package com.globallogic.library;


import com.globallogic.library.entities.Author;
import com.globallogic.library.exceptions.AuthorNotFoundException;
import com.globallogic.library.repositories.AuthorRepository;
import com.globallogic.library.services.impl.AuthorServiceImpl;
import graphql.com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TestAuthorServiceImpl {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Test(expected = AuthorNotFoundException.class)
    public void testAuthorNotFound() throws AuthorNotFoundException {
        Mockito.when(authorRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());
        authorService.get(1);
    }

    @Test
    public void getAuthor() throws AuthorNotFoundException {
        Author author = Author.builder()
                .id(1)
                .numberOfBooksWritten(5)
                .authorName("J.K Rowling")
                .age(45)
                .build();
        Mockito.when(authorRepository.findById(1))
                .thenReturn(Optional.of(author));
        Assert.assertEquals("J.K Rowling", authorService.get(1).getAuthorName());
    }

    @Test
    public void testCreateAuthor() {
        Author author = Author.builder()
                .numberOfBooksWritten(5)
                .authorName("J.K Rowling")
                .age(45)
                .build();
        Mockito.when(authorRepository.save(Mockito.any()))
                .thenAnswer(invocationOnMock -> {
                    Author authorMock = (Author) invocationOnMock.getArguments()[0];
                    authorMock.setId(1);
                    return authorMock;
                });
        Assert.assertEquals(1, authorService.create(author).getId());
    }

    @Test
    public void testGetAllAuthors() {
        Author authorOne = Author.builder()
                .numberOfBooksWritten(5)
                .authorName("J.K Rowling")
                .age(45)
                .build();
        Author authorTwo = Author.builder()
                .numberOfBooksWritten(9)
                .authorName("George R.R Martin")
                .age(70)
                .build();
        List<Author> authors = ImmutableList.of(authorOne, authorTwo);

        Mockito.when(authorRepository.findAll())
                        .thenReturn(authors);
        Assert.assertEquals(2, authorService.getAll().size());
    }
}
