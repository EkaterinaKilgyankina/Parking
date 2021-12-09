package com.epamtraining.parking.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user", schema = "public")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;
/*
    @Enumerated(EnumType.STRING)
    @CollectionTable(name =  "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<RoleEntity> role;
*/
}
