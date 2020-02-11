package com.home.server.validation;

import com.home.server.controllers.dto.RegisterUserDTO;
import com.home.server.entities.User;
import com.home.server.exceptions.ValidationFailedException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class UserValidation {

    public void validateUser(User user) throws ValidationFailedException {
        if (!validateEmail(user.getEmail())) {
            throw new ValidationFailedException("Invalid email.");
        }

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new ValidationFailedException("First name must not be empty.");
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new ValidationFailedException("Last name must not be empty.");
        }



        if (user.getBirthDate() == null) {
            throw new ValidationFailedException("Birth date must not be empty.");
        }


    }

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return EmailValidator.getInstance().isValid(email);
    }
}
