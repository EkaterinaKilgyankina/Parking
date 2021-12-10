package com.epamtraining.parking.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking", schema = "public")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    private long carId;
    //    private long spotId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime bookingFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime bookingTo;

    @OneToOne // что будет если удалю бронирование?
    @JoinColumn(name = "carId", referencedColumnName = "id")
    private CarEntity carEntity;

    @OneToOne
    @JoinColumn(name = "spotId", referencedColumnName = "id")
    private SpotEntity spotEntity;
}
