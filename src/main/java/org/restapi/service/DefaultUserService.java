package org.restapi.service;

import org.restapi.entity.User;
import org.restapi.exception.UserNotFoundException;
import org.restapi.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Inject
    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) throws UserNotFoundException {
    User user = userRepository.find("id", id).firstResult();
    if (user == null) {
        throw new UserNotFoundException("The user doesn't exist");
    }
    return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    @Transactional
    @Override
    public User updateUser(String id, User user) throws UserNotFoundException {
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setCountry(user.getCountry());
        userRepository.persist(existingUser);
        return existingUser;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        userRepository.persist(user); //Database generate an ID
        return user;
    }

    @Transactional
    @Override
    public void deleteUser(String id) throws UserNotFoundException {
        userRepository.delete(getUserById(id));
    }
}
