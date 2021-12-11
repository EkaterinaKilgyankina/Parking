package com.epamtraining.parking.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "car", schema = "public")
@Data
@EqualsAndHashCode(exclude = {"user", "bookingEntity"})
@ToString(exclude = {"user", "bookingEntity"})
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name = "user_id")
//    private long userId;
    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    UserEntity user;
    //long userId

    @OneToOne(mappedBy = "carEntity")
    @JsonIgnore
    private BookingEntity bookingEntity;

}
