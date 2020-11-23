package com.vehiclerecognition.common.exception;

public class InvalidRequestException extends Exception {
    public InvalidRequestException() {
        super("Enter at least one of brand, model or vehicle class value");
    }
}
