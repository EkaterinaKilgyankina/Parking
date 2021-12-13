package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.CarEntity;

public interface CarService {

    CarEntity createCar(CarEntity car, Long userId);
}
