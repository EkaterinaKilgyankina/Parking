package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository <BookingEntity,Long> {
    //Iterable<BookingEntity> findBookingEntityByFromAfterAndToBefore(LocalDateTime from, LocalDateTime to);

    BookingEntity findBookingEntityByCarEntity(String carNumber);
}
