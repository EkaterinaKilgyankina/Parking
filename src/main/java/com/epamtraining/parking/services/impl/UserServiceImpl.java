package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.model.UserRequest;
import com.epamtraining.parking.repository.RoleRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerAdminAccount(UserRequest user) {
        emailValidation(user.getEmail());
        UserEntity userAdmin = new UserEntity();
        userAdmin.setPassword(passwordEncoder.encode(user.getPassword()));
        userAdmin.setEmail(user.getEmail());
        userAdmin.setRoles(Arrays.asList(roleRepository.findByName("role_admin")));

        return userRepository.save(userAdmin);
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setRoles(Collections.singleton(roleRepository.findByName("role_user")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUser(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public UserEntity registerNewUserAccount(UserRequest user) {
        emailValidation(user.getEmail());
        UserEntity userNew = new UserEntity();
        userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        userNew.setEmail(user.getEmail());
        userNew.setRoles(Arrays.asList(roleRepository.findByName("role_user")));
        return userRepository.save(userNew);
    }

    @Override
    public String changeUserRole(ChangeRoleRequest role) {
        String email = role.getEmail();
        UserEntity correctedUser = userRepository.findByEmail(email).orElseThrow(() -> new ApplicationException("User not found"));
        RoleEntity newRole = roleRepository.findByName(role.getRole());
        if(newRole == null) {
            throw new ApplicationException("Role doesn't exist.");
        }
        List<RoleEntity> newRoles = new ArrayList<>();
        newRoles.add(newRole);
        correctedUser.setRoles(newRoles);
        userRepository.save(correctedUser);

        return "success";
    }

    private boolean emailValidation(final String email) {
        if (emailExists(email)) {
            throw new ApplicationException("There is an account with that email address: " + email);
        }
        return true;
    }

    private boolean emailExists(final String email) {
        return userRepository.getUserEntityByEmail(email) != null;
    }
}
