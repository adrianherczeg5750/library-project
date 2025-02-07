package com.library.entity;

import jakarta.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "books")
public class Book extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public Long id;

    @Column(length = 100, nullable = false)
    public String title;

    @Column(length = 100, nullable = false)
    public String author;

    @Column(nullable = false)
    public Integer quantity;

    public Book() {}

    public Book(String title, String author, Integer quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
}