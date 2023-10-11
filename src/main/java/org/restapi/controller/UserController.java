package org.restapi.controller;

import org.restapi.entity.User;
import org.restapi.exception.UserNotFoundException;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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

    private static final SortedSet<User> myUsers = new TreeSet<>();

    //Hårdkodad array - byt mot databasen senare
    static {
        myUsers.addAll(Set.of(
            createMyUser(1, "TestUser1", "TestCountry1"),
            createMyUser(2, "TestUser2", "TestCountry2"),
            createMyUser(3, "TestUser3", "TestCountry3"))
        );
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
    }
    
    //Endpoints
    //GET all users
    @GET
    public Set<User> getUsers() {
    return myUsers;
    }

    //POST create a new user
    @POST
    public User createUser(@Valid UserDto userDto) {
        User user = createMyUser(myUsers.last().getId() + 1, userDto.name, userDto.country);
        myUsers.add(user);
        return user;
    }

    //GET a specific user
    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") int id) throws UserNotFoundException {
        return getUserById(id);
    }
    
    //Update a specific user by id
    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") int id, @Valid UserDto userDto) throws UserNotFoundException {
        User user = getUserById(id);
        user.setName(userDto.name);
        user.setCountry(userDto.country);
        return user;
    }

    //Delete a user by id
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws UserNotFoundException {
        myUsers.remove(getUserById(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }
  
    private User getUserById(int id) throws UserNotFoundException {
        return myUsers.stream().filter(user -> user.getId() == id).findFirst()
            .orElseThrow(() -> new UserNotFoundException("The user doesn't exist"));
    }


    private static User createMyUser(int id, String name, String country) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCountry(country);
        return user;
    }
}