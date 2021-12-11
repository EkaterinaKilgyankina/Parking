package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.repository.RoleRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUser(String email) {
        return userRepository.getUserEntityByEmail(email);
    }

    @Override
    public UserEntity registerNewUserAccount(UserEntity user) {

        if (emailExists(user.getEmail())) {
            throw new RuntimeException("There is an account with that email address: " + user.getEmail());
        }
        UserEntity userNew = new UserEntity();
        userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        userNew.setEmail(user.getEmail());
        userNew.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(userNew);
    }

    private boolean emailExists(final String email) {
        return userRepository.getUserEntityByEmail(email) != null;
    }
}
