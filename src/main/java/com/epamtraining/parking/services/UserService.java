package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.RoleEntity;
import com.epamtraining.parking.domain.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser (UserEntity user);

    public List<UserEntity> getAll();
    public UserEntity getUser(String email);
    public UserEntity registerNewUserAccount(UserEntity user);
    public UserEntity changeUserRole(RoleEntity role, Long userId);
}
