package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.SpotEntity;
import com.epamtraining.parking.repository.impl.SpotRepositoty;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotRepositoty spotRepositoty;

    @Override
    public List<SpotEntity> getAll() {
        return spotRepositoty.findAll();
    }

    @Override
    public List<SpotEntity> getFreeSpots() {
        return spotRepositoty.findAllByVacancyIsTrue();
    }

    @Override
    public List<SpotEntity> getAllSpotsByVacancy(boolean isFree) {
        return spotRepositoty.findAllByVacancy(isFree);
    }


}
