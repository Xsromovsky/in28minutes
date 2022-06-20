package com.example.rest.webservices.in28minutes.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    //get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return UserDaoService.findAll();
    }

    //get user by id
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = UserDaoService.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    //create user with http status
    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        UserDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }


}