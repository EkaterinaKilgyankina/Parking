package com.epamtraining.parking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "spot", schema = "public")
@EqualsAndHashCode (exclude = {"bookingEntity"})
@ToString (exclude = {"bookingEntity"})
public class SpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean vacancy;

    @OneToOne(mappedBy = "spotEntity")
    @JsonIgnore
    private BookingEntity bookingEntity;


}
