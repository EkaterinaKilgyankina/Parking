package com.epamtraining.parking;

import com.epamtraining.parking.domain.BookingEntity;
import com.epamtraining.parking.domain.CarEntity;
import com.epamtraining.parking.domain.SpotEntity;
import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.repository.BookingRepository;
import com.epamtraining.parking.repository.CarRepository;
import com.epamtraining.parking.repository.SpotRepositoty;
import com.epamtraining.parking.repository.UserRepository;
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
        
    }

}
