package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import com.epamtraining.parking.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private BookingService bookingService;
// TODO ИЗМЕНИТЬ РЕГИСТР РОЛЕЙ
    @GetMapping
    @RolesAllowed("{role_user,role_admin}")
    public List<BookingEntity> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("/{carNumber}")
    @RolesAllowed("{role_user,role_admin}")
    public BookingEntity getBookingByCarNumber(@PathVariable String carNumber) {
        return bookingService.getByCarNumber(carNumber);
    }

    @PostMapping
    @RolesAllowed("{role_user,role_admin}")
    public BookingEntity createBooking(@RequestBody @Valid BookingRequest bookingRequest) {
        return bookingService.createBooking(bookingRequest);
    }

    @PutMapping("/{bookingId}")
    public BookingEntity prolongBooking(@RequestBody @Valid BookingRequestForProlonging request
            , @PathVariable Long bookingId) {
        return bookingService.prolongBooking(request, bookingId);
    }

    @DeleteMapping("/cancel/{id}")
    public @ResponseBody
    ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }

}
