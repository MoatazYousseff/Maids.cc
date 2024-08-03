package com.Library.LibraryMS.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
@org.springframework.web.bind.annotation.ExceptionHandler(value={NotFoundException.class})
public ResponseEntity<String> handlingNotFoundExceptions(NotFoundException notFoundException){
    String msg=notFoundException.getMessage();
    return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
}
@org.springframework.web.bind.annotation.ExceptionHandler(value={InvalidInputException.class})
    public ResponseEntity<String> handlingInvalidInputExceptions(InvalidInputException invalidInputException){
        String msg= invalidInputException.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value={AlreadyExistsException.class})
    public ResponseEntity<String> handlingAlreadyExistsExceptions(AlreadyExistsException alreadyExistsException){
        String msg= alreadyExistsException.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }
}
