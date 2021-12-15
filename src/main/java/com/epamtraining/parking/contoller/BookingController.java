package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import com.epamtraining.parking.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private BookingService bookingService;

    @PostMapping
    public BookingEntity createBooking(@RequestBody @Valid BookingRequest bookingRequest) {
        return bookingService.createBooking(bookingRequest);
    }

    // TODO needs review
    @PutMapping("/{bookingId}")
    public BookingEntity prolongBooking(@RequestBody @Valid BookingRequestForProlonging request
            , @PathVariable Long bookingId) {
        return bookingService.prolongBooking(request, bookingId);
    }

    // TODO needs review
    @DeleteMapping("{id}")
    public ResponseEntity cancelBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity("DELETE Response", HttpStatus.OK);
    }

    // TODO do we need these requests?
    @GetMapping
    public List<BookingEntity> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("/{carNumber}")
    public BookingEntity getBookingByCarNumber(@PathVariable String carNumber) {
        return bookingService.getByCarNumber(carNumber);
    }
}
