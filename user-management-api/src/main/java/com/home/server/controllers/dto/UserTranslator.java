package com.home.server.controllers.dto;

import com.home.server.entities.User;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTranslator {
    public User buildUser(RegisterUserDTO userDTO) {
        User user = new User();

        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }

        if (userDTO.getFirstName() != null && !userDTO.getFirstName().isEmpty()) {
            user.setFirstName(userDTO.getFirstName());
        }

        if (userDTO.getLastName() != null && !userDTO.getLastName().isEmpty()) {
            user.setLastName(userDTO.getLastName());
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getBirthDate() != null) {
            user.setBirthDate(new Date(userDTO.getBirthDate().getTime()));
        }

        return user;
    }

    public RegisterUserDTO buildUserDTO(User user) {
        RegisterUserDTO userDTO = new RegisterUserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    public List<RegisterUserDTO> buildUserDTOList(List<User> users) {
        return users.stream().map(this::buildUserDTO).collect(Collectors.toList());
    }
}
