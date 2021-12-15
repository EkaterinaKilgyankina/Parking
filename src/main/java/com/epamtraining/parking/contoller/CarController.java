package com.epamtraining.parking.contoller;

import com.epamtraining.parking.model.CarRequest;
import com.epamtraining.parking.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @PostMapping("/{userId}")
    @RolesAllowed("role_admin")
    public ResponseEntity addCar(@RequestBody CarRequest car,
                                 @PathVariable Long userId) {
        return ResponseEntity.ok(carService.createCar(car, userId));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("role_admin")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity("DELETE Response", HttpStatus.OK);
    }
}
