package com.epamtraining.parking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name =  "role", schema = "public")
public class RoleEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
