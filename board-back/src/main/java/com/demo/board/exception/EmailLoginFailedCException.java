package com.demo.board.exception;

public class EmailLoginFailedCException extends RuntimeException {
    public EmailLoginFailedCException() {
        super();
    }

    public EmailLoginFailedCException(String message) {
        super(message);
    }

    public EmailLoginFailedCException(String message, Throwable cause) {
        super(message, cause);
    }
}