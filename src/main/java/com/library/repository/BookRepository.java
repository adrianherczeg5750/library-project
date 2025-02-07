// BookRepository with custom queries
package com.library.repository;

import com.library.entity.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public List<Book> findByTitle(String title) {
        return list("title LIKE ?1", "%" + title + "%");
    }

    public List<Book> findByAuthor(String author) {
        return list("author LIKE ?1", "%" + author + "%");
    }
} 