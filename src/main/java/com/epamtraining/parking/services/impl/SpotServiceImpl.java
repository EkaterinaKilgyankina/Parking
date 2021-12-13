package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.SpotEntity;
import com.epamtraining.parking.repository.SpotRepository;
import com.epamtraining.parking.services.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpotServiceImpl implements SpotService {
    @Autowired
    private SpotRepository spotRepository;

    @Override
    public List<SpotEntity> getAll() {
        return spotRepository.findAll();
    }

    @Override
    public List<SpotEntity> getFreeSpots() {
        return spotRepository.findAllByBookingEntityIsNull();
    }

    @Override
    public List<SpotEntity> getAllBookedSpots() {
        return spotRepository.findAllByBookingEntityIsNotNull();
    }

//    @Override
//    public SpotEntity getSpotByBookingId(long id) {
//        return spotRepositoty.findByBookingEntity_Id(id);
//    }


}
