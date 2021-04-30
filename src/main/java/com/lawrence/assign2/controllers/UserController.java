package com.lawrence.assign2.controllers;

import com.lawrence.assign2.dtos.UserGetDto;
import com.lawrence.assign2.dtos.UserPostDto;
import com.lawrence.assign2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserPostDto userPostDto) {

        if (userService.emailExists(userPostDto.getEmail())) {
            return new ResponseEntity("The email already exists!",HttpStatus.CONFLICT);
        }

        userService.createUser(userPostDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserPostDto userPostDto) {
        UserGetDto userGetDto = userService.login(userPostDto.getEmail(),userPostDto.getPassword());
        if (userGetDto == null) {
            return new ResponseEntity("Password is incorrect!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userGetDto, HttpStatus.OK);
    }


}
