package com.home.server.services.impl;

import com.home.server.entities.User;
import com.home.server.exceptions.ConstraintException;
import com.home.server.exceptions.MissingDataException;
import com.home.server.exceptions.ResourceNotFoundException;
import com.home.server.exceptions.ValidationFailedException;
import com.home.server.persistence.repository.UserRepository;
import com.home.server.services.UserService;
import com.home.server.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidation userValidation;

    @Override
    public void emailExists(String email) throws ConstraintException {
        userValidation.validateEmail(email);
        User user = findByEmail(email);
        if (user != null) {
            throw new ConstraintException("Account with this email already exists.");
        }
    }

    public User findByEmail(String email) {
        return userRepository.findByEmailAndDeletedFalse(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllByDeletedFalse();
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User with specified id does not exist.", HttpStatus.NOT_FOUND);
        }

        userRepository.softDelete(id);
    }

    @Override
    public User update(User updatedUser) {
        if (updatedUser.getId() == null)
            throw new MissingDataException("User ID must not be null.", HttpStatus.BAD_REQUEST);

        Optional<User> byId = userRepository.findById(updatedUser.getId());
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("User with specified id does not exist.", HttpStatus.NOT_FOUND);
        }

        User user = byId.get();
        partiallyUpdateUser(updatedUser, user);

        return userRepository.save(user);
    }

    private void partiallyUpdateUser(User updatedUser, User existingUser) {
        if (updatedUser.getFirstName() != null && !updatedUser.getFirstName().isEmpty()) {
            existingUser.setFirstName(updatedUser.getFirstName());
        }

        if (updatedUser.getLastName() != null && !updatedUser.getLastName().isEmpty()) {
            existingUser.setLastName(updatedUser.getLastName());
        }

        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getBirthDate() != null) {
            existingUser.setBirthDate(updatedUser.getBirthDate());
        }
    }

    @Override
    public User registerUser(User user) throws ValidationFailedException {
        userValidation.validateUser(user);
        return userRepository.save(user);
    }
}
