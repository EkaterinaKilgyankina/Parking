package com.epamtraining.parking.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user", schema = "public")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

}
