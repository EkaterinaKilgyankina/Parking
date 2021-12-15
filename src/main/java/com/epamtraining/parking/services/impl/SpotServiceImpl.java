package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.SpotRequest;
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

    /*@Override
    public List<SpotEntity> getFreeSpots() {
        return spotRepository.findAllByBookingEntityIsNull();
    }

    @Override
    public List<SpotEntity> getAllBookedSpots() {
        return spotRepository.findAllByBookingEntityIsNotNull();
    }

    @Override
    public SpotEntity getSpotByBookingId(long id) {
        return spotRepository.findByBookingEntity_Id(id);
    }*/

    @Override
    public SpotEntity createSpot(SpotRequest spotRequest) {
        SpotEntity spot = new SpotEntity();
        spot.setLocation(spotRequest.getLocation());
        spotRepository.save(spot);
        return spot;
    }

    @Override
    public void deleteSpot(Long id) {
        //TODO to check the logic, if its possible to delete with booking?
        SpotEntity spot = spotRepository.getById(id);
        /*if (spot.getBookingEntity() != null) {
            throw new ApplicationException("Spot is busy with booking, please wait");
        } else */ spotRepository.delete(spot);

    }

}
