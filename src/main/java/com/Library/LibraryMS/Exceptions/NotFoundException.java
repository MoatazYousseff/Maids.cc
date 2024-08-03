package com.Library.LibraryMS.Exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg) {
        super(msg);
    }
}
