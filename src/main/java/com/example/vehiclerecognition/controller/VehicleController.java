package com.example.vehiclerecognition.controller;

import com.example.vehiclerecognition.exception.EmptyFileException;
import com.example.vehiclerecognition.exception.ReadFileException;
import com.example.vehiclerecognition.model.SearchCriteriaRequest;
import com.example.vehiclerecognition.model.SearchKeyRequest;
import com.example.vehiclerecognition.model.Vehicle;
import com.example.vehiclerecognition.service.FileService;
import com.example.vehiclerecognition.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleController {

    @Autowired
    private VehicleControllerUtil controllerUtil;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello!", HttpStatus.OK);
    }

    @GetMapping(path = "readFile", produces = "application/json")
    public ResponseEntity<List<String>> readFile() throws ReadFileException, EmptyFileException {
        return new ResponseEntity<>(controllerUtil.readFile(), HttpStatus.OK);
    }

    @GetMapping(path = "allVehicles")
    public ResponseEntity<List<Vehicle>> allVehicles() throws ReadFileException, EmptyFileException {
        return new ResponseEntity<>(controllerUtil.getAllVehiclesFromFile(), HttpStatus.OK);
    }

    @PostMapping(path = "searchCriteria")
    public ResponseEntity<List<Vehicle>> searchCriteria(@RequestBody SearchCriteriaRequest request) throws Exception {

        List<Vehicle> vehicles = controllerUtil.searchCriteria(request);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @PostMapping(path = "searchKey")
    public ResponseEntity<List<Vehicle>> searchKey(@RequestBody SearchKeyRequest request) throws Exception {

        List<Vehicle> vehicles = controllerUtil.searchKey(request);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}