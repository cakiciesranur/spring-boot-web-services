package com.vehiclerecognition.rest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Bus extends Vehicle {
    public Bus() {
        this.vehicleClass = VehicleTypeEnum.BUS;
    }

    public Bus(String model, String brand) {
        this.vehicleClass = VehicleTypeEnum.BUS;
        this.model = model;
        this.brand = brand;
    }
}
