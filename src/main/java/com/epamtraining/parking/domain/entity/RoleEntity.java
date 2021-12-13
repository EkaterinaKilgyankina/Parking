package com.epamtraining.parking.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "role", schema = "public")
public class RoleEntity {
    @Id
    private String name;
}

