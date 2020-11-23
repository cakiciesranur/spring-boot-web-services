package com.example.vehiclerecognition.exception;

public class ReadFileException extends Exception {
    public ReadFileException(Throwable e) {
        super("Error occurred while reading the file.", e);
    }
}
