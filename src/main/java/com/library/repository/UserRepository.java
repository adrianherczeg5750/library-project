package com.library.repository;

import com.library.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public List<User> findByName(String name) {
        return list("name LIKE ?1", "%" + name + "%");
    }
}
