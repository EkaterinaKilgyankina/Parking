package com.epamtraining.parking.repository.impl;

import com.epamtraining.parking.domain.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository <BookingEntity,Long> {
}
