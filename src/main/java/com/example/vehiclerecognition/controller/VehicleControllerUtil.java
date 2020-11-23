package com.example.vehiclerecognition.controller;

import com.example.vehiclerecognition.exception.*;
import com.example.vehiclerecognition.model.SearchCriteriaRequest;
import com.example.vehiclerecognition.model.SearchKeyRequest;
import com.example.vehiclerecognition.model.Vehicle;
import com.example.vehiclerecognition.service.FileService;
import com.example.vehiclerecognition.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleControllerUtil {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private FileService fileService;

    private static final Logger logger = LogManager.getLogger(VehicleControllerUtil.class);

    public List<Vehicle> searchCriteria(SearchCriteriaRequest request) throws InvalidRequestException, InvalidVehicleClassException, VehicleNotFoundException, ReadFileException, EmptyFileException {
        if (request == null ||
                ((request.getBrand() == null || request.getBrand().isEmpty()) &&
                        (request.getModel() == null || request.getModel().isEmpty()) &&
                        (request.getVehicleClass() == null || request.getVehicleClass().isEmpty()))) {
            throw new InvalidRequestException();
        }

        return vehicleService.searchCriteria(request);
    }

    public List<Vehicle> searchKey(SearchKeyRequest request) throws InvalidRequestException, ReadFileException, VehicleNotFoundException, EmptyFileException {
        if (request == null || request.getKey() == null) {
            logger.error("Request search key is null.");
            throw new InvalidRequestException();
        }

        List<Vehicle> result = vehicleService.searchKey(request);

        if (result.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle not found with key: " + request.getKey());
        }

        return result;
    }

    public List<String> readFile() throws ReadFileException, EmptyFileException {
        return fileService.readFile();
    }

    public List<Vehicle> getAllVehiclesFromFile() throws ReadFileException, EmptyFileException {
        return vehicleService.getAllVehiclesFromFile();
    }
}