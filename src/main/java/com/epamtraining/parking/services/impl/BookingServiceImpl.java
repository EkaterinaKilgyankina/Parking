package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.BookingEntity;
import com.epamtraining.parking.domain.entity.CarEntity;
import com.epamtraining.parking.domain.entity.SpotEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.BookingRequest;
import com.epamtraining.parking.model.BookingRequestForProlonging;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.CarRepository;
import com.epamtraining.parking.repository.SpotRepository;
import com.epamtraining.parking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //TODO изменить на поиск по id
        CarEntity car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new ApplicationException("Car not found"));

        if (car.getCurrentStatus().equals(CarEntity.Status.requested)) {
            throw new ApplicationException("Car is not yet approved");
        }

        SpotEntity spot = spotRepository.findById(request.getSpotId())
                .orElseThrow(() -> new ApplicationException("Spot not found"));

        List<BookingEntity> bookings = spot.getBookings();

        for (BookingEntity booking : bookings) {
            if (!booking.getBookingTo().isBefore(request.getFrom().plusNanos(1)) && !booking.getBookingFrom().isAfter(request.getTo().minusNanos(1))) {
                throw new ApplicationException("Spot is busy");
            }
        }

        BookingEntity carBookings = car.getBookingEntity();

        if (carBookings != null) throw new ApplicationException("Spot for this car is already booked.");

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
                .orElseThrow(() -> new ApplicationException("Booking with requested Id does not exist"));
        LocalDateTime localDateTime = bookingEntity.getBookingTo().plusMinutes(request.getDuration());
        BookingEntity bookingEntity1 = bookingEntity.setBookingTo(localDateTime);

        return bookingRepository.save(bookingEntity1);
    }

    @Override
    public void deleteBooking(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String s = auth.getAuthorities().toString();
        if(s.substring(1, s.length() - 1).equals("role_user")) {
            CarEntity car = bookingRepository.findById(id).get().getCarEntity();
            String principal = auth.getPrincipal().toString();
            if (car.getUser().getEmail().equals(principal)) {
                BookingEntity booking = bookingRepository.findById(id).get();
                bookingRepository.delete(booking);
            } else throw new ApplicationException("User is allowed to delete only his own bookings.");
        } else {
            BookingEntity booking = bookingRepository.findById(id).get();
            bookingRepository.delete(booking);
        }
    }
}
