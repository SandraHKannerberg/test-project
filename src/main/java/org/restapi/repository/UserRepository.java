package org.restapi.repository;

import java.util.List;

import org.restapi.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository {

    public List<User> listAll() {
        return null;
    }

    public void persist(User existingUser) {
    }

    public void persistAndFlush(User user) {
    }

    public void delete(User userById) {
    }

    public Object findByIdOptional(long id) {
        return null;
    }

    public User findById(long id) {
        return null;
    }

}
