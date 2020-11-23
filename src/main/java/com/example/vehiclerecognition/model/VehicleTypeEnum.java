package com.example.vehiclerecognition.model;

public enum VehicleTypeEnum {
    CAR("Otomobil"),
    BUS("Otobüs"),
    SUV_PICK_UP("Arazi,SUV&Pick-up"),
    TRUCK("Kamyon&Kamyonet");

    public final String type;

    VehicleTypeEnum(String type) {
        this.type = type;
    }

    public static VehicleTypeEnum getVehicleTypeFromString(String fromString) {
        for (VehicleTypeEnum vehicleType : VehicleTypeEnum.values()) {
            if (vehicleType.type.equalsIgnoreCase(fromString) || vehicleType.type.contains(fromString)) {
                return vehicleType;
            }
        }
        return null;
    }
}
