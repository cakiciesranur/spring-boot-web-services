package com.example.vehiclerecognition.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(VehicleNotFoundException.class)
    public final ResponseEntity<ExceptionMessage> VehicleNotFoundException(VehicleNotFoundException ex) {
        ExceptionMessage error = new ExceptionMessage("Vehicle not found", ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<ExceptionMessage> InvalidRequestException(InvalidRequestException ex) {
        ExceptionMessage error = new ExceptionMessage("Invalid Request!", ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidVehicleClassException.class)
    public final ResponseEntity<ExceptionMessage> InvalidVehicleClassException(InvalidVehicleClassException ex) {
        ExceptionMessage error = new ExceptionMessage("Invalid Vehicle Class!", ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReadFileException.class)
    public final ResponseEntity<ExceptionMessage> ReadFileException(ReadFileException ex) {
        ExceptionMessage error = new ExceptionMessage("Reading File Error!", ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EmptyFileException.class)
    public final ResponseEntity<ExceptionMessage> ReadFileException(EmptyFileException ex) {
        ExceptionMessage error = new ExceptionMessage("Reading File Error!", ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
