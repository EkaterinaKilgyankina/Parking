package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Optional<CarEntity> findByNumber(String number);
}
