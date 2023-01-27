package com.globallogic.library.model;

import lombok.Data;

@Data
public class AuthorInput {
    private String authorName;
    private int age;
    private int numberOfBooksWritten;
}
