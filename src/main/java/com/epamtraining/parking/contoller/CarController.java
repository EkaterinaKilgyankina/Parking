package com.epamtraining.parking.contoller;

import com.epamtraining.parking.model.CarRequest;
import com.epamtraining.parking.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @PostMapping("/{userId}")
    public ResponseEntity addCar(@RequestBody @Valid CarRequest car,
                                 @PathVariable Long userId) {
        return ResponseEntity.ok(carService.createCar(car, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity("DELETE Response", HttpStatus.OK);
    }
}
