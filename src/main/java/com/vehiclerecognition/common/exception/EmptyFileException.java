package com.vehiclerecognition.common.exception;

public class EmptyFileException extends Exception{
    public EmptyFileException() {
        super("The file is empty!");
    }
}
