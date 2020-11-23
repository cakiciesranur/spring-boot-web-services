package com.example.vehiclerecognition.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SearchKeyRequest {
    @NotNull
    private String key;
}