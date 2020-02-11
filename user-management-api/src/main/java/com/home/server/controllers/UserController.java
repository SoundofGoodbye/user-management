package com.home.server.controllers;

import com.home.server.controllers.dto.RegisterUserDTO;
import com.home.server.controllers.dto.UserTranslator;
import com.home.server.entities.User;
import com.home.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTranslator userTranslator;

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterUserDTO> createUser(@RequestBody RegisterUserDTO userDTO) {
        userService.emailExists(userDTO.getEmail());

        User user = userService.registerUser(userTranslator.buildUser(userDTO));

        return new ResponseEntity<>(userTranslator.buildUserDTO(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegisterUserDTO>> listUsers() {
        List<User> users = userService.getAll();

        return new ResponseEntity<>(userTranslator.buildUserDTOList(users), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterUserDTO> updateUser(@PathVariable("id") Long id, @RequestBody RegisterUserDTO userDTO) {
        if (userDTO.getId() == null) {
            userDTO.setId(id);
        }

        User user = userTranslator.buildUser(userDTO);
        User updatedUser = userService.update(user);
        
        return new ResponseEntity<>(userTranslator.buildUserDTO(updatedUser), HttpStatus.OK);
    }
}
