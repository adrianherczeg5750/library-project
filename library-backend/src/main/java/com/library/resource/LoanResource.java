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

    @GET
    public List<Loan> listLoans() {
        return loanRepository.listAll();
    }

    @POST
    @Transactional
    public Response addLoan(Loan loan) {
        if (loan.book == null || loan.book.id == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Hibás könyv ID").build();
        }
        if (loan.user == null || loan.user.name == null || loan.user.address == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Felhasználónév és cím szükséges").build();
        }
        if (loan.quantity == null || loan.quantity <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("A mennyiségnek 1-nél nagyobbnak kell lennie").build();
        }

        User user = userRepository.find("name = ?1 AND address = ?2", loan.user.name, loan.user.address)
                .firstResult();
        if (user == null) {
            user = new User(loan.user.name, loan.user.address);
            userRepository.persist(user);
        }

        Book book = bookRepository.findById(loan.book.id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Könyv nem található").build();
        }

        int borrowedQuantity = loanRepository.sumBorrowedBooksByBookId(book.id);
        if (book.quantity - borrowedQuantity < loan.quantity) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Nincs elég könyv a kölcsönzéshez").build();
        }

        book.quantity -= loan.quantity;
        bookRepository.persist(book);

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

        Book book = loan.book;
        book.quantity += loan.quantity;
        bookRepository.persist(book);

        loan.broughtBackAt = LocalDateTime.now();
        return Response.ok(loan).build();
    }

}
