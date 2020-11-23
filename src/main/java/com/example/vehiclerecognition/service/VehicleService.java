package com.example.vehiclerecognition.service;

import com.example.vehiclerecognition.common.Constant;
import com.example.vehiclerecognition.exception.EmptyFileException;
import com.example.vehiclerecognition.exception.InvalidVehicleClassException;
import com.example.vehiclerecognition.exception.ReadFileException;
import com.example.vehiclerecognition.exception.VehicleNotFoundException;
import com.example.vehiclerecognition.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VehicleService implements IVehicleService {

    private final FileService fileService;

    private static final Logger logger = LogManager.getLogger(VehicleService.class);

    public VehicleService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public List<Vehicle> getAllVehiclesFromFile() throws ReadFileException, EmptyFileException {
        List<String> vehiclesAsString = fileService.readFile();


        return convertStringsToVehicle(vehiclesAsString);
    }

    private List<Vehicle> convertStringsToVehicle(List<String> vehiclesAsString) {
        List<Vehicle> vehicleList = new ArrayList<>();

        for (String vehicleLine : vehiclesAsString) {
            String[] items = vehicleLine.split(Constant.SEMICOLON);
            if (items.length < 3) {
                logger.warn("Input in the file is not valid: " + vehicleLine);
                continue;
            }
            Vehicle vehicle = createVehicle(items);
            if (vehicle != null) {
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    private Vehicle createVehicle(String[] item) {
        String brand = item[0];
        String model = item[1];
        String vehicleClass = item[2];
        VehicleTypeEnum vehicleType = VehicleTypeEnum.getVehicleTypeFromString(vehicleClass);

        if (vehicleType == null) {
            return null;
        }

        switch (vehicleType) {
            case CAR:
                return new Car(model, brand);
            case BUS:
                return new Bus(model, brand);
            case TRUCK:
                return new Truck(model, brand);
            case SUV_PICK_UP:
                return new Suv(model, brand);
            default:
                return null;
        }
    }

    @Override
    public List<Vehicle> searchCriteria(SearchCriteriaRequest request) throws VehicleNotFoundException, InvalidVehicleClassException, ReadFileException, EmptyFileException {
        List<Vehicle> vehicles = getAllVehiclesFromFile();
        List<Vehicle> filteredVehicles = new ArrayList<>();

        if (request.getModel() != null && !request.getModel().isEmpty()) {
            filteredVehicles = getVehiclesByModel(vehicles, request.getModel());
        }
        if (request.getBrand() != null && !request.getBrand().isEmpty()) {
            filteredVehicles = getVehiclesByBrand(filteredVehicles == null || filteredVehicles.isEmpty() ? vehicles : filteredVehicles, request.getBrand());
        }

        if (request.getVehicleClass() != null && !request.getVehicleClass().isEmpty()) {
            filteredVehicles = getVehiclesByClass(filteredVehicles == null || filteredVehicles.isEmpty() ? vehicles : filteredVehicles, request.getVehicleClass());
        }

        if (filteredVehicles.isEmpty()) {
            String messageValues = getCriteriaMessage(request);
            throw new VehicleNotFoundException("Vehicle not found with this criteria : " + messageValues);
        }

        return filteredVehicles;

    }

    private String getCriteriaMessage(SearchCriteriaRequest request) {
        StringBuilder message = new StringBuilder();
        if (request.getBrand() != null && !request.getBrand().isEmpty()) {
            message.append("brand: ").append(request.getBrand());
        }
        if (request.getVehicleClass() != null && !request.getVehicleClass().isEmpty()) {
            message.append("class: ").append(request.getVehicleClass());
        }
        if (request.getModel() != null && !request.getModel().isEmpty()) {
            message.append("model: ").append(request.getModel());
        }

        return message.toString();
    }

    private List<Vehicle> getVehiclesByClass(List<Vehicle> vehicles, String vehicleClass) throws InvalidVehicleClassException {
        VehicleTypeEnum vehicleType = VehicleTypeEnum.getVehicleTypeFromString(vehicleClass);

        if (vehicleType == null) {
            throw new InvalidVehicleClassException("Vehicle class: " + vehicleClass + " is not valid");
        }

        return vehicles.stream()
                .filter(Objects::nonNull)
                .filter(vehicle -> vehicle.getVehicleClass() != null && vehicle.getVehicleClass() == vehicleType)
                .collect(Collectors.toList());
    }

    private List<Vehicle> getVehiclesByBrand(List<Vehicle> vehicles, String brand) {
        return vehicles.stream()
                .filter(Objects::nonNull)
                .filter(vehicle -> vehicle.getBrand() != null && vehicle.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    private List<Vehicle> getVehiclesByModel(List<Vehicle> vehicles, String model) {
        return vehicles.stream()
                .filter(Objects::nonNull)
                .filter(vehicle -> vehicle.getModel() != null && vehicle.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> searchKey(SearchKeyRequest request) throws ReadFileException, EmptyFileException {
        List<String> vehiclesAsString = fileService.readFile();

        List<String> filteredVehiclesAsString = vehiclesAsString.stream()
                .filter(Objects::nonNull)
                .filter(vehicle -> StringUtils.containsIgnoreCase(vehicle, request.getKey()))
                //(vehicle.toLowerCase().contains(request.getKey().toLowerCase())) {
                .collect(Collectors.toList());
        return convertStringsToVehicle(filteredVehiclesAsString);
    }
}