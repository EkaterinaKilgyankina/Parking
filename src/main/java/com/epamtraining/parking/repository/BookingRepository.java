package com.epamtraining.parking.repository;

import com.epamtraining.parking.domain.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository <BookingEntity,Long> {

    BookingEntity findBookingEntityByCarEntity (String carNumber);
}
