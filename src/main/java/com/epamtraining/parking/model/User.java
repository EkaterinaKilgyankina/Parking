package com.epamtraining.parking.model;

import com.epamtraining.parking.domain.entity.UserEntity;
import lombok.Data;
// TODO to update??
@Data
public class User {
    private Long id;
    private String email;

    public static User toModel(UserEntity userEntity) {
        User model = new User();
        model.setId(userEntity.getId());
        model.setEmail(userEntity.getEmail());
        return model;
    }
}


