package com.library.resource;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import com.library.repository.LoanRepository;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @Inject
    LoanRepository loanRepository;

    @GET
    public List<Book> listBooks() {
        return bookRepository.listAll();
    }

    @GET
    @Path("/search")
    public List<Book> searchBooks(@QueryParam("title") String title, @QueryParam("author") String author) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitle(title);
        } else if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthor(author);
        } else {
            throw new BadRequestException("Adjon meg egy címet vagy szerzőt");
        }
    }

    @POST
    @Transactional
    public Response addBook(@Valid Book book) {
        bookRepository.persist(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateBook(@PathParam("id") Long id, @Valid Book updatedBook) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Könyv " + id + " nem található").build();
        }
        book.title = updatedBook.title;
        book.author = updatedBook.author;
        book.quantity = updatedBook.quantity;
        return Response.ok(book).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteBook(@PathParam("id") Long id) {
        loanRepository.delete("book.id", id);
        boolean deleted = bookRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).entity("Könyv " + id + " nem található").build();
        }
        return Response.noContent().build();
    }
}
