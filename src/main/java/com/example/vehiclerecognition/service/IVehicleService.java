package com.example.vehiclerecognition.service;

import com.example.vehiclerecognition.exception.EmptyFileException;
import com.example.vehiclerecognition.exception.InvalidVehicleClassException;
import com.example.vehiclerecognition.exception.ReadFileException;
import com.example.vehiclerecognition.exception.VehicleNotFoundException;
import com.example.vehiclerecognition.model.SearchCriteriaRequest;
import com.example.vehiclerecognition.model.SearchKeyRequest;
import com.example.vehiclerecognition.model.Vehicle;

import java.io.IOException;
import java.util.List;

public interface IVehicleService {

    List<Vehicle> getAllVehiclesFromFile() throws IOException, ReadFileException, EmptyFileException;

    List<Vehicle> searchCriteria(SearchCriteriaRequest request) throws VehicleNotFoundException, InvalidVehicleClassException, ReadFileException, EmptyFileException;

    List<Vehicle> searchKey(SearchKeyRequest request) throws ReadFileException, EmptyFileException;

}