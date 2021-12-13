package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.SpotBooking;
import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface BookingService {

    List<BookingEntity> getAll();

    BookingEntity getByCarNumber(String carNumber);

    BookingEntity createBooking(BookingRequest bookingRequest);

    BookingEntity prolongBooking(BookingRequestForProlonging request, Long id);

    void deleteBooking (Long id);

    public List<SpotBooking> getSpotBookingsForTimePeriod();

}
