package com.example.getmesocialservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameIsRootException.class)
    public ResponseEntity<String> nameIsRootException(NameIsRootException nameIsRootException){
        return new ResponseEntity<String>(nameIsRootException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> InvalidTokenException(InvalidTokenException invalidTokenException){
        return new ResponseEntity<String>(invalidTokenException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CredentialNotMatchException.class)
    public ResponseEntity<String> CredentialNotMatchException(CredentialNotMatchException credentialNotMatchException){
        return new ResponseEntity<String>(credentialNotMatchException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
