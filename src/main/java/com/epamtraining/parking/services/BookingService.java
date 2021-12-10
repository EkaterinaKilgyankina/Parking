package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.model.BookingRequest;

import java.util.List;

public interface BookingService {

    public List<BookingEntity> getAll ();

    public BookingEntity createBooking (BookingRequest bookingRequest);



}
