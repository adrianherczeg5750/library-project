package com.library.resource;

import com.library.entity.User;
import com.library.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import com.library.repository.LoanRepository;


@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;

    @Inject
    LoanRepository loanRepository;

    @GET
    public List<User> listUsers() {
        return userRepository.listAll();
    }

    @GET
    @Path("/search")
    public List<User> searchUsers(@QueryParam("name") String name) {
        if (name != null && !name.isEmpty()) {
            return userRepository.findByName(name);
        } else {
            throw new BadRequestException("Adja meg a könyv nevét");
        }
    }

    @POST
    @Transactional
    public Response addUser(@Valid User user) {
        userRepository.persist(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, @Valid User updatedUser) {
        User user = userRepository.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Felhasználó " + id + " nem található").build();
        }
        user.name = updatedUser.name;
        user.address = updatedUser.address;
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        loanRepository.delete("user.id", id);
        boolean deleted = userRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).entity("Felhasználó " + id + " nem található").build();
        }
        return Response.noContent().build();
    }
}
