package com.epamtraining.parking.repository.impl.car;

import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.domain.model.Car;
import com.epamtraining.parking.repository.CarRepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CarRepositoryServiceImpl implements CarRepositoryService {
    private final CarRepository carRepository;

    //todo add method here
}
