package com.library.scheduler;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    @Transactional
    public void addBooks(List<Book> books) {
        for (Book book : books) {
            bookRepository.persist(book);
        }
    }
}
