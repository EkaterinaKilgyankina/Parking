package com.epamtraining.parking;

import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.domain.SpotEntity;
import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.repository.impl.BookingRepository;
import com.epamtraining.parking.repository.impl.SpotRepositoty;
import com.epamtraining.parking.repository.impl.UserRepository;
import com.epamtraining.parking.repository.impl.car.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootTest
class ParkingApplicationTests {
    @Autowired
    CarRepository carRepository;
    @Autowired
    SpotRepositoty spotRepositoty;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookingRepository bookingRepository;

    @DynamicPropertySource
    public static void properties(final DynamicPropertyRegistry registry) {
        PostgresContainer.properties(registry);
    }
    @Test
    void contextLoads() {
        final CarEntity car = new CarEntity();
        car.setNumber("123123");
        final UserEntity user1 = new UserEntity();
        user1.setEmail("email@email.com");
        user1.setPassword("pwd");
        user1.setCars(Collections.singletonList(car));
        car.setUser(user1);
        userRepository.save(user1);

        try {
            final UserEntity user = userRepository.findAll().get(0);
            final CarEntity carEntity = user.getCars().get(0);
            final SpotEntity spot = spotRepositoty.findById(1L).get();
            final BookingEntity bookingEntity = new BookingEntity();
            bookingEntity.setCarEntity(carEntity);
            bookingEntity.setSpotEntity(spot);
            bookingEntity.setBookingFrom(LocalDateTime.now());
            bookingEntity.setBookingTo(LocalDateTime.now().plusMinutes(30));
            spot.setBookingEntity(bookingEntity);
            carEntity.setBookingEntity(bookingEntity);
//carRepository.save(carEntity)
            bookingRepository.saveAndFlush(bookingEntity);
        } catch (Exception e ) {
            System.out.println("");
        }
    }




}
