package com.isapsw.Projekat.exceptions;

public class UserAlreadyExistsResponse {
    private String message;

    public UserAlreadyExistsResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
