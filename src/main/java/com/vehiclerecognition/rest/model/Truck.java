package com.vehiclerecognition.rest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Truck extends Vehicle {
    public Truck() {
        this.vehicleClass = VehicleTypeEnum.TRUCK;
    }

    public Truck(String model, String brand) {
        this.vehicleClass = VehicleTypeEnum.TRUCK;
        this.model = model;
        this.brand = brand;
    }
}
