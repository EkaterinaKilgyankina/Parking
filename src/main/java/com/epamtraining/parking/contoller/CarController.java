package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.services.CarService;
import com.epamtraining.parking.services.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

    @Autowired
    private final CarServiceImpl carService;


    @PostMapping("/{userId}")
    public ResponseEntity addCar(@RequestBody CarEntity car,
                                 @PathVariable Long userId) {
        return ResponseEntity.ok(carService.createCar(car, userId));
    }
}
