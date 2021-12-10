package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.repository.impl.car.CarRepository;
import com.epamtraining.parking.repository.impl.UserRepository;
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
