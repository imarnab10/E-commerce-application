package com.arnab.ecommerceapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class DefaultControllerAdvice{

    //Entity with given ID doesn't exist
    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(ItemNotFoundException ex) {
        RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,
                "The requested resource was not found.",
                ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    //Address or Contact isn't associated with Customer
    @ExceptionHandler(EntityNotAssociatedWithCustomer.class)
    protected ResponseEntity<Object> handleEntityNotAssociated(EntityNotAssociatedWithCustomer ex) {
        RestApiError apiError = new RestApiError(HttpStatus.BAD_REQUEST,
                "The requested resource was not associated with Customer.",
                ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    //Not enough product left in our inventory
    @ExceptionHandler(NotEnoughProduct.class)
    protected ResponseEntity<Object> handleEntityNotEnough(NotEnoughProduct ex) {
        RestApiError apiError = new RestApiError(HttpStatus.BAD_REQUEST,
                "The requested product is not enough in our Inventory.",
                ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(UniqueKeyException.class)
    protected ResponseEntity<Object> UniqueKeyExp(UniqueKeyException ex) {
        RestApiError apiError = new RestApiError(HttpStatus.BAD_REQUEST,
                "Already exists in database",
                ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}