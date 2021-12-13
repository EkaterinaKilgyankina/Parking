package com.epamtraining.parking.repository;


import com.epamtraining.parking.domain.SpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<SpotEntity, Long> {

    List<SpotEntity> findAllByBookingEntityIsNull();

    List<SpotEntity> findAllByBookingEntityIsNotNull();

//    SpotEntity findByBookingEntity_Id(long id);
}
