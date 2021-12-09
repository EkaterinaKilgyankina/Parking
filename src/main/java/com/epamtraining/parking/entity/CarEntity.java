package com.epamtraining.parking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "car", schema = "public")
@Data
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "user_id")
    private long userId;
    @Column (name = "number")
    private String number;

}
