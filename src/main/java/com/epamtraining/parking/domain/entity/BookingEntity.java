package com.epamtraining.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking", schema = "public")
@Accessors(chain = true)
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime bookingFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime bookingTo;

    @OneToOne
    @JoinColumn(name = "carId", referencedColumnName = "id")
    private CarEntity carEntity;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "spotId", referencedColumnName = "id")
    private SpotEntity spotEntity;
}
