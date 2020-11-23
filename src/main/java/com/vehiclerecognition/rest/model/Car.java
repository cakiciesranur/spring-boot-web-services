package com.vehiclerecognition.rest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Car extends Vehicle {

    public Car() {
        this.vehicleClass = VehicleTypeEnum.CAR;
    }

    public Car(String model, String brand) {
        this.vehicleClass = VehicleTypeEnum.CAR;
        this.model = model;
        this.brand = brand;
    }

}
