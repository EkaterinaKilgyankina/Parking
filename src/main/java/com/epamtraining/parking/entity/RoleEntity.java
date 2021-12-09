package com.epamtraining.parking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name =  "role")
public class RoleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
