package com.home.server.services;

import com.home.server.controllers.dto.RegisterUserDTO;
import com.home.server.entities.User;
import com.home.server.exceptions.ConstraintException;
import com.home.server.exceptions.MissingDataException;
import com.home.server.exceptions.ResourceNotFoundException;
import com.home.server.exceptions.ValidationFailedException;

import java.util.List;

public interface UserService {
    void emailExists(String email) throws ConstraintException;

    User registerUser(User user) throws ValidationFailedException;

    User findByEmail(String email);

    List<User> getAll();

    void deleteById(Long id) throws ResourceNotFoundException;

    User update(User user) throws MissingDataException, ResourceNotFoundException;
}
