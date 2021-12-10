package com.epamtraining.parking.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {
    private Long id;
    private LocalDateTime from;
    private LocalDateTime to;
    private Car car;
    private Spot spot;
}
