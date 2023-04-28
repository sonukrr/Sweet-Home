package com.upgrad.booking.exceptions;

public class PaymentException extends RuntimeException {
    private int statusCode;
    private String message;

    public PaymentException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "PaymentException{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
