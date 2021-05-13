package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.services.BookingService;
import com.upgrad.hirewheels.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.upgrad.hirewheels.utils.BookingMapper.mapBookingDTOToBooking;
import static com.upgrad.hirewheels.utils.BookingMapper.mapBookingToBookingDTO;

@RestController
@RequestMapping(value = "/hirewheels/v1")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleService vehicleService;

    /**
     * +++++++++++ CHECKPOINT - 04 +++++++++++++
     * This is HTTP POST Method which is responsible
     * for adding a new booking in the database.
     */
    @PostMapping(value = "/bookings", consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) throws Exception{

        //Map Booking DTO to Booking Entity
        Booking newBooking = mapBookingDTOToBooking(bookingDTO);

        //Call Booking service to create a new Booking
        Booking savedBooking = bookingService.addBooking(newBooking);

        //Map Vehicle Entity to Vehicle DTO
        BookingDTO savedBookingDTO = mapBookingToBookingDTO(savedBooking);

        //Returning the newly created vehicle
        return new ResponseEntity<>(savedBookingDTO, HttpStatus.CREATED);
    }
}
