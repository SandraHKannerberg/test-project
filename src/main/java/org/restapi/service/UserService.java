package org.restapi.service;

import org.restapi.entity.User;
import org.restapi.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    User getUserById(String id) throws UserNotFoundException;

    List<User> getAllUsers();

    User updateUser(String id, User user) throws UserNotFoundException;

    User saveUser(User user);

    void deleteUser(String id) throws UserNotFoundException;

    User getUserByName(String name);

    boolean isUserNameUnique(String name);
}
