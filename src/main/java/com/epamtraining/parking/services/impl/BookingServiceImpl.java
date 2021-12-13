package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.domain.SpotBooking;
import com.epamtraining.parking.domain.SpotEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.CarRepository;
import com.epamtraining.parking.repository.SpotRepository;
import com.epamtraining.parking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    public BookingServiceImpl(SpotRepository spotRepository, CarRepository carRepository, BookingRepository bookingRepository) {
        this.spotRepository = spotRepository;
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingEntity> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingEntity getByCarNumber(String carNumber) {
        return bookingRepository.findBookingEntityByCarEntity(carNumber);
    }

    @Override
    @Transactional // если больше 1 запросов на запись в базу
    public BookingEntity createBooking(BookingRequest request) {
        SpotEntity spot = spotRepository.findById(request.getSpotNumber())
                .orElseThrow(() -> new ApplicationException("Spot not found"));

        if (spot.getBookingEntity() != null) {
            throw new RuntimeException("");// создать ошибки
        }

        CarEntity car = carRepository.findByNumber(request.getCarNumber())
                .orElseThrow(() -> new RuntimeException(""));

        if (car.getBookingEntity() != null) {
            throw new ApplicationException("Car has booking");
        }

        BookingEntity booking = new BookingEntity()
                .setCarEntity(car)
                .setSpotEntity(spot)
                .setBookingFrom(request.getFrom())
                .setBookingTo(request.getFrom().plusMinutes(request.getDuration()));

        spotRepository.save(spot.setBookingEntity(booking));

        return bookingRepository.save(booking);
    }

    @Override
    public BookingEntity prolongBooking(BookingRequestForProlonging request, Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ApplicationException("Booking with requested Id does not exist"));
        LocalDateTime localDateTime = bookingEntity.getBookingTo().plusMinutes(request.getDuration());
        BookingEntity bookingEntity1 = bookingEntity.setBookingTo(localDateTime);

        return bookingRepository.save(bookingEntity1);
    }

    @Override
    public void deleteBooking(Long id) {
//        SpotEntity byBookingEntity_id = spotRepositoty.findByBookingEntity_Id(id);
//        byBookingEntity_id.getBookingEntity().setId(0L);
        bookingRepository.deleteById(id);
    }

    // TODO shows spot bookings based on period of time
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
            //SpotBooking spotBooking = spotBookingMap.get(booking.getSpotId());
            //spotBooking.setFrom(from);
            //spotBooking.setTo(to);
            //CarEntity car = this.carRepository.findById(booking.getCarId()).get();
            //spotBooking.setNumber(car.getNumber());
        });

        List<SpotBooking> spotBookings = new ArrayList<>();
        for(Long id: spotBookingMap.keySet()) {
            spotBookings.add(spotBookingMap.get(id));
        }

        return spotBookings;
    }


}
