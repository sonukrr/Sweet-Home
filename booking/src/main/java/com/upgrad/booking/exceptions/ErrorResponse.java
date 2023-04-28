package com.upgrad.booking.exceptions;

public class ErrorResponse {
    private String message;
    private int code;

    public ErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
