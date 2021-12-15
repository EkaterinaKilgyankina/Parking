package com.epamtraining.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "spot", schema = "public")
@EqualsAndHashCode(exclude = {"bookings"})
@ToString(exclude = {"bookings"})
public class SpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  /*  @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bookingId", referencedColumnName = "id")
    @JsonIgnore
    private BookingEntity bookingEntity;*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spotEntity")
    @JsonIgnore
    private List<BookingEntity> bookings;

    private String location;

}
