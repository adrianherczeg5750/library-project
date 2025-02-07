package com.library.repository;

import com.library.entity.Loan;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class LoanRepository implements PanacheRepository<Loan> {

    @PersistenceContext
    EntityManager entityManager;

    public int sumBorrowedBooksByBookId(Long bookId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(l.quantity), 0) FROM Loan l WHERE l.book.id = :bookId AND l.broughtBackAt IS NULL",
                Long.class);
        query.setParameter("bookId", bookId);

        return query.getSingleResult().intValue();
    }

}
