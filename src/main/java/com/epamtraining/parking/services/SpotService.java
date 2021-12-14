package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.model.SpotRequest;

import java.util.List;

public interface SpotService {

    List<SpotEntity> getAll();

    List<SpotEntity> getFreeSpots();

    List<SpotEntity> getAllBookedSpots();

    SpotEntity getSpotByBookingId(long id);

    SpotEntity createSpot(SpotRequest spotRequest);

    void deleteSpot(Long id);

}