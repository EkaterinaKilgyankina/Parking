package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.domain.SpotEntity;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.repository.impl.BookingRepository;
import com.epamtraining.parking.repository.impl.car.CarRepository;
import com.epamtraining.parking.repository.impl.SpotRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private SpotRepositoty spotRepositoty;

    @Override
    public List<BookingEntity> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingEntity createBooking(BookingRequest request) {

        BookingEntity bookingEntity = new BookingEntity();
        CarEntity car = carRepository.getById(request.getCarId());
        SpotEntity spot = spotRepositoty.getById(request.getSpotId());
        bookingEntity.setSpotEntity(spot);
        bookingEntity.setCarEntity(car);
        bookingEntity.setBookingFrom(request.getFrom());
        bookingEntity.setBookingTo(request.getTo());
        return bookingRepository.save(bookingEntity);

    }
}
