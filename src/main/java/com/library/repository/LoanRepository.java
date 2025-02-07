package com.library.repository;

import com.library.entity.Loan;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoanRepository implements PanacheRepository<Loan> {

    public int sumBorrowedBooksByBookId(Long bookId) {
        Long result = find("book.id = ?1 AND broughtBackAt IS NULL", bookId)
                .project(Long.class)
                .firstResult();
        return result != null ? result.intValue() : 0;
    }
}
