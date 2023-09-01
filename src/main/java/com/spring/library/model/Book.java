package com.spring.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToOne
    @JoinColumn(name = "uer_id",nullable = false)
    private User user;
}
