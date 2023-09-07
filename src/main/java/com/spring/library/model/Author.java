package com.spring.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Author {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    @OneToMany
    private List<Book> books;
}
