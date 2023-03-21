package com.example.getmesocialservice.exception;

public class NameIsRootException extends Exception{
    @Override
    public String getMessage() {
        return "The value of name cannot be \"root\"";
    }
}
