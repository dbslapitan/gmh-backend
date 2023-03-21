package com.example.getmesocialservice.exception;

public class CredentialNotMatchException extends Exception{
    @Override
    public String getMessage() {
        return "Created by and current user doesn't match.";
    }
}
