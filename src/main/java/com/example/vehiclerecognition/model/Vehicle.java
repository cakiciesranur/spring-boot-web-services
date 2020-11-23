package com.example.vehiclerecognition.model;

import lombok.Data;

@Data
public class Vehicle {
    protected VehicleTypeEnum vehicleClass;
    protected String model;
    protected String brand;
}
