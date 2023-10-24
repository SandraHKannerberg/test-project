package org.restapi.controller;

import org.restapi.entity.User;
import org.restapi.exception.UserNotFoundException;
import org.restapi.service.UserService;

import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;
   
    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Endpoints
    //GET all users
    @GET
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    //POST create a new user
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(@Valid UserDto userDto) {
        User user = userDto.toUser();
        user.setId(generateUniqueId());
        return userService.saveUser(user);
    }

    //Give the new user a random id 
    private String generateUniqueId() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
    
    //GET a specific user
    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") String id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    //Update a specific user by id
    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") String id, @Valid UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(id, userDto.toUser());
    }

    //Delete a user by id
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) throws UserNotFoundException {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    //Data Transfer Object. User entity but without id since we dont't want the consumer to determine the id
    public static class UserDto {

        @NotBlank
        private String name;
    
        @NotBlank
        private String country;
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getCountry() {
            return country;
        }
    
        public void setCountry(String country) {
            this.country = country;
        }

        public User toUser() {
        User user = new User();
        user.setName(name);
        user.setCountry(country);
        return user;
        }
    }
}