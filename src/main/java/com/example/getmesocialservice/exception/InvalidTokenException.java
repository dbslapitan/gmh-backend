package com.example.getmesocialservice.exception;

public class InvalidTokenException extends Exception{
    @Override
    public String getMessage() {
        return "Invalid Token";
    }
}
