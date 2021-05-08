package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/hirewheels/v1")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * +++++++++++ CHECKPOINT - 01 +++++++++++++
     * This is HTTP GET Method which is responsible
     * for returning all vehicles available inside the database.
     */
    @GetMapping(value = "/vehicles",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleDTO>> getVehicles(){

          //calling vehicle service to get all vehicles available inside the database
          List<Vehicle> allVehicleList = vehicleService.getAllVehicles();

          //Initiate Vehicle DTO
          List<VehicleDTO> allVehicleDTOList = new ArrayList<>();

          //Map Vehicle Entity to Vehicle DTO
          for(Vehicle vehicle : allVehicleList){
              allVehicleDTOList.add(modelMapper.map(vehicle,VehicleDTO.class));
          }

          //Returning the List of Vehicle
          return new ResponseEntity<>(allVehicleDTOList, HttpStatus.OK);
    }
}
