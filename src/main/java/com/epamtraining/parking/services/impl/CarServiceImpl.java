package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.repository.CarRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CarEntity createCar(CarEntity car, Long userId) {

        UserEntity user = userRepository.findById(userId).get();
        car.setUser(user);
        return carRepository.save(car);
    }
}
