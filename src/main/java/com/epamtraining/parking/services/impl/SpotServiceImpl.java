package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.SpotRequest;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.SpotRepository;
import com.epamtraining.parking.services.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SpotServiceImpl implements SpotService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private SpotRepository spotRepository;

    @Override
    public List<SpotEntity> getAll() {
        return spotRepository.findAll();
    }

    @Override
    public SpotEntity createSpot(SpotRequest spotRequest) {
        SpotEntity spot = new SpotEntity();
        spot.setLocation(spotRequest.getLocation());
        spotRepository.save(spot);
        return spot;
    }

    @Override
    public void deleteSpot(Long id) {
        SpotEntity spot = spotRepository.getById(id);
        spotRepository.delete(spot);
    }

    public List<SpotEntity> getFreeSpotsForTimePeriod(LocalDateTime from, LocalDateTime to) {
        List<BookingEntity> bookings = bookingRepository.findAll();
        List<Long> anyBookedSpotId = new ArrayList<>();
        for (BookingEntity booking: bookings) {
            anyBookedSpotId.add(booking.getSpotEntity().getId());
        }
        List<SpotEntity> freeSpots = new ArrayList<>();
        List<SpotEntity> spots = spotRepository.findAll();
        for (SpotEntity spot: spots) {
            if(!anyBookedSpotId.contains(spot.getId())) freeSpots.add(spot);
        }
        for (BookingEntity booking: bookings) {
            if(booking.getBookingTo().isBefore(from) || booking.getBookingFrom().isAfter(to)) {
                freeSpots.add(spotRepository.findByLocation(booking.getSpotEntity().getLocation()).get());
            }
        }
        Collections.sort(freeSpots, (o1, o2) -> o1.getId().intValue() - o2.getId().intValue());

        return freeSpots;
    }

    // TODO implement
    @Override
    public List<SpotEntity> getAllBookedSpots(LocalDateTime from, LocalDateTime to) {
        List<SpotEntity> bookedSpots = new ArrayList<>();
        return bookedSpots;
    }

  /*  @Override
    public SpotEntity getSpotByBookingId(long id) {
        return spotRepository.findByBookingEntity_Id(id);
    }*/
}
