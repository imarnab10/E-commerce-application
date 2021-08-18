package com.arnab.ecommerceapp.exception;

public class NotEnoughProduct extends RuntimeException{
    public NotEnoughProduct() {
    }

    public NotEnoughProduct(String message) {
        super(message);
    }
}
