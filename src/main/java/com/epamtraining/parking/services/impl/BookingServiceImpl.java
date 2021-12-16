package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.domain.exception.RuntimeException;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.CarRepository;
import com.epamtraining.parking.repository.SpotRepository;
import com.epamtraining.parking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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
        CarEntity car = carRepository.findByNumber(request.getCarNumber())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        String spotLocation = request.getSpotLocation();
        SpotEntity spot = spotRepository.findByLocation(request.getSpotLocation())
                .orElseThrow(() -> new RuntimeException("Spot not found"));

        long parkingTime = Math.abs(Duration.between(request.getTo(), request.getFrom()).toMinutes());
        long allowedParking = 1440L;
        if (parkingTime - allowedParking > 0)
            throw new RuntimeException("Cannot make a booking more than for 24 hours.");

        List<BookingEntity> bookings = spot.getBookings();

        for (BookingEntity booking: bookings) {
            if(!booking.getBookingFrom().isAfter(request.getTo()) || !booking.getBookingTo().isBefore(request.getFrom())) {
                throw new RuntimeException("Spot is busy");
            }
        }

        BookingEntity carBookings = car.getBookingEntity();

        if (carBookings != null) throw new RuntimeException("Spot for this car is already booked.");

        BookingEntity booking = new BookingEntity()
                .setCarEntity(car)
                .setSpotEntity(spot)
                .setBookingFrom(request.getFrom())
                .setBookingTo(request.getTo());

        return bookingRepository.save(booking);
    }

    @Override
    public BookingEntity prolongBooking(BookingRequestForProlonging request, Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking with requested Id does not exist"));
        LocalDateTime localDateTime = bookingEntity.getBookingTo().plusMinutes(request.getDuration());
        BookingEntity bookingEntity1 = bookingEntity.setBookingTo(localDateTime);

        return bookingRepository.save(bookingEntity1);
    }

    @Override
    public void deleteBooking(Long id) {
        //SpotEntity spot = spotRepository.findByBookingEntity_Id(id);
        //spot.setBookingEntity(null);
        //spotRepository.save(spot);
        BookingEntity booking = bookingRepository.findById(id).get();
        bookingRepository.delete(booking);
    }
}
