package com.epamtraining.parking.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "spot", schema = "public")
public class SpotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean vacancy;


}
