package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.model.UserRequest;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    UserEntity createUser (UserEntity user);

    public List<UserEntity> getAll();
    public UserEntity getUser(String email);
    public UserEntity registerAdminAccount(UserRequest user);
    public UserEntity registerNewUserAccount(UserRequest user);
    public String changeUserRole(Long userId, ChangeRoleRequest role);
}
