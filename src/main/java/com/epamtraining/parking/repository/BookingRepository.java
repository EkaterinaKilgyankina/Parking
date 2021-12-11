package com.epamtraining.parking.repository;


import com.epamtraining.parking.domain.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository <BookingEntity,Long> {
    //Iterable<BookingEntity> findBookingEntityByFromAfterAndToBefore(LocalDateTime from, LocalDateTime to);

    BookingEntity findBookingEntityByCarEntity(String carNumber);
}
