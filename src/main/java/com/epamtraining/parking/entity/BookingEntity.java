package com.epamtraining.parking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking", schema = "public")
public class BookingEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private long carId;
    private long spotId;
    private LocalDateTime from;
    private LocalDateTime to;
}
