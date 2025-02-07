package com.library.resource;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.entity.Loan;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import com.library.repository.LoanRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("/api/loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {

    @Inject
    LoanRepository loanRepository;

    @Inject
    BookRepository bookRepository;

    @Inject
    UserRepository userRepository;

    // List all loans
    @GET
    public List<Loan> listLoans() {
        return loanRepository.listAll();
    }

    @POST
    @Transactional
    public Response addLoan(Loan loan) {
        User user = userRepository.find("name = ?1 AND address = ?2", loan.user.name, loan.user.address)
                .firstResult();

        if (user == null) {
            user = new User(loan.user.name, loan.user.address);
            userRepository.persist(user);
        }

        Book book = bookRepository.findById(loan.book.id);
        if (book == null) {
            throw new NotFoundException("Könyv nem található");
        }

        int borrowedQuantity = loanRepository.sumBorrowedBooksByBookId(book.id);
        if (book.quantity - borrowedQuantity < loan.quantity) {
            throw new BadRequestException("Nincs elég könyv a kölcsönzéshez");
        }

        loan.book = book;
        loan.user = user;
        loan.takenOutAt = LocalDateTime.now();
        loanRepository.persist(loan);

        return Response.status(Response.Status.CREATED).entity(loan).build();
    }


    @PUT
    @Path("/{id}/return")
    @Transactional
    public Response returnLoan(@PathParam("id") Long id) {
        Loan loan = loanRepository.findById(id);
        if (loan == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nem található a kölcsönzés").build();
        }
        if (loan.broughtBackAt != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("A könyvet már visszahozták").build();
        }
        loan.broughtBackAt = LocalDateTime.now();
        return Response.ok(loan).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteLoan(@PathParam("id") Long id) {
        boolean deleted = loanRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).entity("Nem található a kölcsönzés").build();
        }
        return Response.noContent().build();
    }
}