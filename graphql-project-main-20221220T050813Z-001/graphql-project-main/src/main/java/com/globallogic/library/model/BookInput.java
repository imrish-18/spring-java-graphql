package com.globallogic.library.model;

import lombok.Data;

@Data
public class BookInput {
    private String bookTitle;
    private int publishingYear;
    private double ratings;
    private double price;
    private int authorId;
}
