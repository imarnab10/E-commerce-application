package com.arnab.ecommerceapp.exception;

import org.springframework.http.HttpStatus;

public class RestApiError {
    HttpStatus httpStatus;

    String errorMessage;

    String errorDetails;

    public RestApiError() {
    }

    public RestApiError(HttpStatus httpStatus, String errorMessage, String errorDetails) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}
