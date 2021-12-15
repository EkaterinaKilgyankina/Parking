package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.model.SpotRequest;
import com.epamtraining.parking.services.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/spots")
public class SpotController {
    private final SpotService spotService;

    @GetMapping
    @RolesAllowed("{role_user,role_admin}")
    public List<SpotEntity> getAllSpots() {
        return spotService.getAll();
    }

  /*  @GetMapping("/free")
    @RolesAllowed("{role_user,role_admin}")
    public List<SpotEntity> getAllFreeSpots() {
        return spotService.getFreeSpots();
    }

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
