package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.domain.UserEntity;


public interface CarService {
    public CarEntity createCar(CarEntity car, Long userId);
}
