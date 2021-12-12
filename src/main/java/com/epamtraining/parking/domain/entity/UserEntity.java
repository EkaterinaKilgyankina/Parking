package com.epamtraining.parking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "user", schema = "public")
@Entity
@EqualsAndHashCode(exclude = {"cars"})
@ToString(exclude = {"cars"})
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<CarEntity> cars;

}
