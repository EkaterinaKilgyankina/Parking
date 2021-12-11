package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    List<BookingEntity> getAll();

    BookingEntity getByCarNumber(String carNumber);

    BookingEntity createBooking(BookingRequest bookingRequest);

    BookingEntity prolongBooking(BookingRequestForProlonging request, Long id);

    void deleteBooking (Long id);

}
