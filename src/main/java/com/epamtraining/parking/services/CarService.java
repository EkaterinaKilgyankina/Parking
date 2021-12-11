package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.CarEntity;

public interface CarService {

    public CarEntity createCar(CarEntity car, Long userId);
}
