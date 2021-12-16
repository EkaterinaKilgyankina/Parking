package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.CarRequest;
import com.epamtraining.parking.repository.CarRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private UserRepository userRepository;

    @Override
    public CarEntity createCar(CarRequest car, Long userId) {

        UserEntity user = userRepository.findById(userId).get();
        CarEntity carEntity = new CarEntity();
        carEntity.setUser(user)
                .setNumber(car.getNumber());
        return carRepository.save(carEntity);
    }

    @Override
    // TODO to agree on param for delete operation - id to change to number
    public void deleteCar(Long id) {
        CarEntity car = carRepository.getById(id);
        carRepository.delete(car);
    }
}
