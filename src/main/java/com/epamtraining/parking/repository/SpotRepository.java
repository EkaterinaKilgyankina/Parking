package com.epamtraining.parking.repository;

import com.epamtraining.parking.entity.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<SpotEntity, Long> {
}
