package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @PostMapping("/{userId}")
    public ResponseEntity addCar(@RequestBody CarEntity car,
                                 @PathVariable Long userId) {
        return ResponseEntity.ok(carService.createCar(car, userId));
    }
}
