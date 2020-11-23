package com.vehiclerecognition.soap.endpoint;

import com.vehiclerecognition.soap.dto.SearchCriteriaRequest;
import com.vehiclerecognition.soap.dto.SearchCriteriaResponse;
import com.vehiclerecognition.soap.dto.SearchKeyRequest;
import com.vehiclerecognition.soap.dto.SearchKeyResponse;
import com.vehiclerecognition.soap.service.VehicleServiceForSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SoapVehicleEndpoint {

    private static final String NAMESPACE_URI = "http://vehiclerecognition.com/soap/dto";

    private VehicleServiceForSoap vehicleService;

    @Autowired
    public SoapVehicleEndpoint(VehicleServiceForSoap vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchKeyRequest")
    @ResponsePayload
    public SearchKeyResponse searchKeyRequest(@RequestPayload SearchKeyRequest request) throws Exception {
        SearchKeyResponse response = new SearchKeyResponse();
        response.getVehicleList().addAll(vehicleService.searchKey(request));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchCriteriaRequest")
    @ResponsePayload
    public SearchCriteriaResponse searchCriteriaRequest(@RequestPayload SearchCriteriaRequest request) throws Exception {
        SearchCriteriaResponse response = new SearchCriteriaResponse();
        response.getVehicleList().addAll(vehicleService.searchCriteria(request));

        return response;
    }
}