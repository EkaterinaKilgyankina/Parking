package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.model.BookedSpot;
import com.epamtraining.parking.model.SpotRequest;
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

    private final SpotService spotService;

    @PostMapping
    public ResponseEntity addSpot(@RequestBody @Valid SpotRequest request) {
        return ResponseEntity.ok(spotService.createSpot(request));
    }

    // TODO кейс: спот, занятый с 9 утра,не попадает в выборку пустых спотов с 8 до 9 утра
    @GetMapping("/free-spots")
    public List<SpotEntity>  getFreeSpots(@RequestParam("from")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime,
                                          @RequestParam("to")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime){
        List<SpotEntity> spotBookings = spotService.getFreeSpotsForTimePeriod(fromDateTime, toDateTime);
        return spotBookings;
    }

    // TODO all following should be reviewed

    @GetMapping("/booked-spots")
    public List<BookedSpot> getAllBookedSpots(@RequestParam("from")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime,
                                              @RequestParam("to")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime) {
        return spotService.getAllBookedSpots(fromDateTime, toDateTime);
    }

    // TODO all following should be reviewed
    @DeleteMapping("{id}")
    @RolesAllowed("role_admin")
    public ResponseEntity deleteSpot(@PathVariable Long id) {
        spotService.deleteSpot(id);
        return new ResponseEntity("DELETE Response", HttpStatus.OK);
    }
}
