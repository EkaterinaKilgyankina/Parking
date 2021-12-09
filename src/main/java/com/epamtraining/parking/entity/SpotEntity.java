package com.epamtraining.parking.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "spot", schema = "public")
public class SpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;


}
