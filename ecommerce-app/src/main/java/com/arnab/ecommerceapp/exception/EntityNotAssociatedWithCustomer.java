package com.arnab.ecommerceapp.exception;

public class EntityNotAssociatedWithCustomer extends RuntimeException{
    public EntityNotAssociatedWithCustomer() {
    }

    public EntityNotAssociatedWithCustomer(String message) {
        super(message);
    }
}
