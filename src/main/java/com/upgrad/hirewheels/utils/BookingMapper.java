package com.upgrad.hirewheels.utils;


import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.Location;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.Vehicle;

public class BookingMapper {

    private BookingMapper(){}

    public static Booking mapBookingDTOToBooking(BookingDTO bookingDTO){
        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setAmount(bookingDTO.getAmount());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setPickupDate(bookingDTO.getPickupDate());
        booking.setDropoffDate(bookingDTO.getDropoffDate());
        Location location = new Location();
        location.setLocationId(bookingDTO.getLocationId());
        booking.setLocation(location);
        User user = new User();
        user.setUserId(bookingDTO.getUserId());
        booking.setUser(user);
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(bookingDTO.getVehicleId());
        booking.setVehicleWithBooking(vehicle);
        return booking;
    }

    public static BookingDTO mapBookingToBookingDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setAmount(booking.getAmount());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setPickupDate(booking.getPickupDate());
        bookingDTO.setDropoffDate(booking.getDropoffDate());
        bookingDTO.setLocationId(booking.getLocation().getLocationId());
        bookingDTO.setUserId(booking.getUser().getUserId());
        bookingDTO.setVehicleId(booking.getVehicleWithBooking().getVehicleId());
        return bookingDTO;
    }
}
