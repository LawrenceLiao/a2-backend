package com.lawrence.assign2.controllers;

import com.lawrence.assign2.dtos.ErrorDto;
import com.lawrence.assign2.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException e) {

        ErrorDto errorDto = ErrorDto.builder()
                .message("User is not found!")
                .details(e.getLocalizedMessage())
                .build();
        return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleAllOtherException(Exception e) {

        ErrorDto errorDto = ErrorDto.builder()
                .message("Server Error")
                .details(e.getLocalizedMessage())
                .build();
        return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
