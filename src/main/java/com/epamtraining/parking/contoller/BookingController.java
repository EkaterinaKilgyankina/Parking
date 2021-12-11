package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import com.epamtraining.parking.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private BookingService bookingService;

    @GetMapping
    public List<BookingEntity> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("/{carNumber}")
    public BookingEntity getBookingByCarNumber(@PathVariable String carNumber) {
        return bookingService.getByCarNumber(carNumber);
    }

    @PostMapping
    public BookingEntity createBooking(@RequestBody @Valid BookingRequest bookingRequest) {
        return bookingService.createBooking(bookingRequest);
    }

    @PutMapping("/{bookingId}")
    public BookingEntity prolongBooking(@RequestBody @Valid BookingRequestForProlonging request
            , @PathVariable Long bookingId) {
        return bookingService.prolongBooking(request, bookingId);
    }

}
