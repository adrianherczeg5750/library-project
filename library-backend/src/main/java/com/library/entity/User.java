package com.library.entity;

import jakarta.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    public Long id;

    @NotBlank(message = "A név nem lehet üres")
    @Size(max = 50, message = "A név nem lehet több 50 karakter")
    @Column(length = 50, nullable = false)
    public String name;

    @NotBlank(message = "A cím nem lehet üres")
    @Size(max = 100, message = "A cím nem lehet több 50 karakter")
    @Column(length = 100, nullable = false)
    public String address;

    public User() {}

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }
}