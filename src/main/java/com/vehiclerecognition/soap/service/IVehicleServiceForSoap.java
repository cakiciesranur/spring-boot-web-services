package com.vehiclerecognition.soap.service;

import com.vehiclerecognition.common.exception.EmptyFileException;
import com.vehiclerecognition.common.exception.InvalidVehicleClassException;
import com.vehiclerecognition.common.exception.ReadFileException;
import com.vehiclerecognition.common.exception.VehicleNotFoundException;
import com.vehiclerecognition.soap.dto.SearchCriteriaRequest;
import com.vehiclerecognition.soap.dto.SearchKeyRequest;
import com.vehiclerecognition.soap.dto.Vehicle;

import java.util.List;

public interface IVehicleServiceForSoap {

    List<Vehicle> getAllVehiclesFromFile() throws ReadFileException, EmptyFileException;

    List<Vehicle> searchKey(SearchKeyRequest request) throws ReadFileException, EmptyFileException;

    List<Vehicle> searchCriteria(SearchCriteriaRequest request) throws VehicleNotFoundException, InvalidVehicleClassException, ReadFileException, EmptyFileException;
}
