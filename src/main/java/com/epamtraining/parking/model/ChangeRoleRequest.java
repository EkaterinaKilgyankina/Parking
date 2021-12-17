package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ChangeRoleRequest {

    @NotBlank(message = "email is mandatory")
    @Pattern(regexp = "^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}", message = "invalid email")
    String email;

    @NotBlank(message = "user role is mandatory")
    String role;
}
