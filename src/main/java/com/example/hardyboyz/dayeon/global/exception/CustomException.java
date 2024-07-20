package com.example.hardyboyz.dayeon.global.exception;

public abstract class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public abstract int getStatusCode();

}
