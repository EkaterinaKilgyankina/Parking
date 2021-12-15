package com.epamtraining.parking.model;

import lombok.Data;

@Data
public class ChangeRoleRequest {

    String email;

    String role;
}
