package com.xpresspayments.api.core.exception;

public class GenericException extends RuntimeException {
    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }
}
