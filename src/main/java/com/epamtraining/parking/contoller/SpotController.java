package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.model.SpotRequest;
import com.epamtraining.parking.services.BookingService;
import com.epamtraining.parking.services.SpotService;
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
@RequestMapping("/spots")
public class SpotController {
    private final BookingService bookingService;
    private final SpotService spotService;

    @GetMapping("/free_spots")
    public List<SpotEntity>  getFreeSpots(@RequestParam("from")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime,
                                          @RequestParam("to")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime){
        List<SpotEntity> spotBookings = bookingService.getSpotBookingsForTimePeriod(fromDateTime, toDateTime);
        return spotBookings;
    }

    // TODO all following should be reviewed
    @GetMapping
    @RolesAllowed("{role_user,role_admin}")
    public List<SpotEntity> getAllSpots() {
        return spotService.getAll();
    }

/*
    @GetMapping("/booked")
    @RolesAllowed("{role_user,role_admin}")
    public List<SpotEntity> getAllBookedSpots() {
        return spotService.getAllBookedSpots();
    }*/

    @PostMapping
    @RolesAllowed("role_admin")
    public ResponseEntity addSpot(@RequestBody @Valid SpotRequest request) {
        return ResponseEntity.ok(spotService.createSpot(request));
    }

    @DeleteMapping("{id}")
    @RolesAllowed("role_admin")
    public ResponseEntity deleteSpot(@PathVariable Long id) {
        spotService.deleteSpot(id);
        return new ResponseEntity("DELETE Response", HttpStatus.OK);
    }
}
