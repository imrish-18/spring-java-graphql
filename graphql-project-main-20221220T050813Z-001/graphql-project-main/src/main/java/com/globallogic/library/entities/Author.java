package com.globallogic.library.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authorName;
    private int age;
    private int numberOfBooksWritten;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(int id) {
        this.id = id;
    }
}
