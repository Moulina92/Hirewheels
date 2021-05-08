package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.VehicleDTO;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hirewheels/v1")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * +++++++++++ CHECKPOINT - 02 +++++++++++++
     * This is HTTP POST Method which is responsible
     * for adding a new vehicles in the database.
     */
    @PostMapping(value = "/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO) throws Exception{

        //Map Vehicle DTO to Vehicle Entity
        Vehicle newVehicle = modelMapper.map(vehicleDTO,Vehicle.class);

        //Call Admin service to register a new Vehicle
        Vehicle savedVehicle = adminService.registerVehicle(newVehicle);

        //Map Vehicle Entity to Vehicle DTO
        VehicleDTO savedVehicleDTO = modelMapper.map(savedVehicle,VehicleDTO.class);

        //Returning the newly created vehicle
        return new ResponseEntity<>(savedVehicleDTO, HttpStatus.CREATED);
    }

    /**
     * +++++++++++ CHECKPOINT - 03 +++++++++++++
     * This is HTTP PUT Method which is responsible
     * for updating a already present vehicle in the database.
     */
    @PutMapping(value = "/vehicles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable(value = "id") int id,
                                                    @RequestBody VehicleDTO vehicleDTO){

        //Map Vehicle DTO to Vehicle Entity
        Vehicle newVehicle = modelMapper.map(vehicleDTO,Vehicle.class);

        //Call Admin service to change availability status a existing Vehicle by vehicle id
        Vehicle updatedVehicle = adminService.changeAvailability(id,newVehicle.getAvailabilityStatus());

        //Map Vehicle Entity to Vehicle DTO
        VehicleDTO updatedVehicleDTO = modelMapper.map(updatedVehicle,VehicleDTO.class);

        //Returning the newly created vehicle
        return new ResponseEntity<>(updatedVehicleDTO, HttpStatus.ACCEPTED);
    }
}
