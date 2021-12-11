package com.epamtraining.parking.services;

<<<<<<< HEAD
import com.epamtraining.parking.domain.SpotBooking;
import com.epamtraining.parking.entity.BookingEntity;
import com.epamtraining.parking.entity.CarEntity;
import com.epamtraining.parking.entity.SpotEntity;
import com.epamtraining.parking.entity.UserEntity;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.CarRepository;
import com.epamtraining.parking.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookingService {
    private SpotRepository spotRepository;
    private CarRepository carRepository;
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(SpotRepository spotRepository, CarRepository carRepository, BookingRepository bookingRepository) {
        this.spotRepository = spotRepository;
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<SpotBooking> getSpotBookingsForTimePeriod() {
        Iterable<SpotEntity> spots = this.spotRepository.findAll();
        Map<Long, SpotBooking> spotBookingMap = new HashMap<>();
        spots.forEach(spot -> {
            SpotBooking spotBooking = new SpotBooking();
            spotBooking.setLocation(spot.getLocation());
            spotBookingMap.put(spot.getId(), spotBooking);
        });
        Iterable<BookingEntity> bookings = this.bookingRepository.findAll();
        bookings.forEach(booking -> {
            SpotBooking spotBooking = spotBookingMap.get(booking.getSpotId());
            //spotBooking.setFrom(from);
            //spotBooking.setTo(to);
            CarEntity car = this.carRepository.findById(booking.getCarId()).get();
            spotBooking.setNumber(car.getNumber());
        });

        List<SpotBooking> spotBookings = new ArrayList<>();
        for(Long id: spotBookingMap.keySet()) {
            spotBookings.add(spotBookingMap.get(id));
        }

        return spotBookings;
    }
=======
import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    List<BookingEntity> getAll();

    BookingEntity getByCarNumber(String carNumber);

    BookingEntity createBooking(BookingRequest bookingRequest);

    BookingEntity prolongBooking(BookingRequestForProlonging request, Long id);

    void deleteBooking (Long id);

>>>>>>> toMerge
}
