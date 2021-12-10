package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.services.BookingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;



    @GetMapping
    public List<BookingEntity> getAll() {
        return bookingService.getAll();
    }

    @PostMapping
    public BookingEntity createBooking(@RequestBody BookingRequest bookingRequest) {
        return bookingService.createBooking(bookingRequest);
    }

}
