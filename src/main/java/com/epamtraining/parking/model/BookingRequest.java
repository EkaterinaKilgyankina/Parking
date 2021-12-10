package com.epamtraining.parking.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BookingRequest {
    private Long id;
        private long carId;
        private long spotId;
    private LocalDateTime from;
    private LocalDateTime to;
}
