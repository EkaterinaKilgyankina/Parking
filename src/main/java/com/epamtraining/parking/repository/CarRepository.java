package com.epamtraining.parking.repository;

import com.epamtraining.parking.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
