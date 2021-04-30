package com.lawrence.assign2.services;

import com.lawrence.assign2.dtos.UserGetDto;
import com.lawrence.assign2.dtos.UserPostDto;
import com.lawrence.assign2.entities.UserEntity;
import com.lawrence.assign2.exceptions.UserNotFoundException;
import com.lawrence.assign2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void createUser(UserPostDto userPostDto) {
        UserEntity newUser = UserEntity.builder()
                .email(userPostDto.getEmail())
                .username(userPostDto.getUsername())
                .password(userPostDto.getPassword())
                .build();

        userRepository.addUser(newUser);
    }


    public UserGetDto login(String email, String password) {
        UserEntity foundUser = userRepository.findByEmail(email);

        if(foundUser == null) {
            throw new UserNotFoundException("User not found");
        }

        if(password.equals(foundUser.getPassword())) {
            UserGetDto userLoginDto = UserGetDto.builder()
                    .email(email)
                    .username(foundUser.getUsername())
                    .build();
            return userLoginDto;
        }
        return null;
    }
}
