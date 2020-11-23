package com.example.vehiclerecognition.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Suv extends Vehicle {

    public Suv() {
        this.vehicleClass = VehicleTypeEnum.SUV_PICK_UP;
    }

    public Suv(String model, String brand) {
        this.vehicleClass = VehicleTypeEnum.SUV_PICK_UP;
        this.model = model;
        this.brand = brand;
    }}
