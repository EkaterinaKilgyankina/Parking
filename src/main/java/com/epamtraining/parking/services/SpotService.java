package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.model.SpotRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface SpotService {

    List<SpotEntity> getFreeSpotsForTimePeriod(LocalDateTime from, LocalDateTime to);

    List<SpotEntity> getAll();

    List<SpotEntity> getAllBookedSpots(LocalDateTime from, LocalDateTime to);

    //SpotEntity getSpotByBookingId(long id);

    SpotEntity createSpot(SpotRequest spotRequest);

    void deleteSpot(Long id);

}