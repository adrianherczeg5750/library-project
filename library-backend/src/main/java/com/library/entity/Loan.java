package com.library.entity;

import jakarta.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    public Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Column(nullable = false)
    public Integer quantity;

    @Column(name = "taken_out_at", nullable = false)
    public LocalDateTime takenOutAt;

    @Column(name = "brought_back_at")
    public LocalDateTime broughtBackAt;

    public Loan() {}

}