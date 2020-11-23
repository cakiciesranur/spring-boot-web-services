package com.vehiclerecognition.rest.service;

import com.vehiclerecognition.common.exception.EmptyFileException;
import com.vehiclerecognition.common.exception.InvalidVehicleClassException;
import com.vehiclerecognition.common.exception.ReadFileException;
import com.vehiclerecognition.common.exception.VehicleNotFoundException;
import com.vehiclerecognition.rest.model.SearchCriteriaRequest;
import com.vehiclerecognition.rest.model.SearchKeyRequest;
import com.vehiclerecognition.rest.model.Vehicle;

import java.io.IOException;
import java.util.List;

public interface IVehicleService {

    List<Vehicle> getAllVehiclesFromFile() throws IOException, ReadFileException, EmptyFileException;

    List<Vehicle> searchCriteria(SearchCriteriaRequest request) throws VehicleNotFoundException, InvalidVehicleClassException, ReadFileException, EmptyFileException;

    List<Vehicle> searchKey(SearchKeyRequest request) throws ReadFileException, EmptyFileException;
}