package com.arnab.ecommerceapp.exception;

public class UniqueKeyException extends RuntimeException{
    public UniqueKeyException() {
    }

    public UniqueKeyException(String message) {
        super(message);
    }
}
