package org.restapi.service;

import org.restapi.entity.User;
import org.restapi.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    User getUserById(long id) throws UserNotFoundException;

    List<User> getAllUsers();

    User updateUser(long id, User user) throws UserNotFoundException;

    User saveUser(User user);

    void deleteUser(long id) throws UserNotFoundException;

}
