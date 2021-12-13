package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity user);

    List<UserEntity> getAllUsers();
}
