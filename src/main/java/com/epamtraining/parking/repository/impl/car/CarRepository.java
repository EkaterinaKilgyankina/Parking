package com.epamtraining.parking.repository.impl.car;

import com.epamtraining.parking.domain.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository <CarEntity, Long> {
}
