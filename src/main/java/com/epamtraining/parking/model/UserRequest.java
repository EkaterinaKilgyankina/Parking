package com.epamtraining.parking.model;

import com.epamtraining.parking.domain.entity.UserEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
    private Long id;
    private String email;

    public static UserRequest toModel(UserEntity userEntity) {
        UserRequest model = new UserRequest();
        model.setId(userEntity.getId());
        model.setEmail(userEntity.getEmail());
        return model;
    }
}


