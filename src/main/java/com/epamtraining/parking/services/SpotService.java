package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.SpotEntity;

import java.util.List;

public interface SpotService {

    List<SpotEntity> getAll();

    List<SpotEntity> getFreeSpots();

    List<SpotEntity> getAllBookedSpots();

//    SpotEntity getSpotByBookingId(long id);

}