package com.example.vehiclerecognition.exception;

public class EmptyFileException extends Exception{
    public EmptyFileException() {
        super("The file is empty!");
    }
}
