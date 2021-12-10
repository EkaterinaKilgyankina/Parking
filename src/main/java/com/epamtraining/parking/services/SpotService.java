package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.SpotEntity;

import java.util.List;


public interface SpotService {
    public List<SpotEntity> getAll();

    public List<SpotEntity> getFreeSpots();

    public List<SpotEntity> getAllSpotsByVacancy(boolean isFree);

}