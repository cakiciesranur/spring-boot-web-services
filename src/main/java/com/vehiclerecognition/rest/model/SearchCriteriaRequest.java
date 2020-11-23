package com.vehiclerecognition.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SearchCriteriaRequest {
    private String model;
    private String brand;
    private String vehicleClass;
}

