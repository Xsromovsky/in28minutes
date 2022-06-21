package com.example.rest.webservices.in28minutes.user;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<User> getUser(@PathVariable Integer id) {
        User user = UserDaoService.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("User not found");
        }
        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));



        return resource;
    }

    //create user
    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        UserDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    //delete user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        if(UserDaoService.deleteById(id) == null){
            throw new UserNotFoundException("User not found");
        }
        UserDaoService.deleteById(id);
    }



}
