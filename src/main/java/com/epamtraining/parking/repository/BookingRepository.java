package com.epamtraining.parking.repository;

import com.epamtraining.parking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDateTime;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    Iterable<BookingEntity> findBookingEntityByFromAfterAndToBefore(LocalDateTime from, LocalDateTime to);
}
