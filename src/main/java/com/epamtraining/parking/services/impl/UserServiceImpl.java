package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.repository.RoleRepository;
import com.epamtraining.parking.repository.UserRepository;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    // TODO createAdmin

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
    public UserEntity registerNewUserAccount(UserEntity user) {

        String userEmail = user.getEmail();

        if (userEmail == null || userEmail.length() == 0) {
            throw new RuntimeException("Field cannot be empty.");
        }
        if (!isEmail(userEmail)) {
            throw new RuntimeException("It is not an email.");
        }
        if (emailExists(user.getEmail())) {
            throw new RuntimeException("There is an account with that email address: " + user.getEmail());
        }
        UserEntity userNew = new UserEntity();
        userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        userNew.setEmail(user.getEmail());
        userNew.setRoles(Arrays.asList(roleRepository.findByName("role_user")));
        return userRepository.save(userNew);
    }

    @Override
    public UserEntity changeUserRole(RoleEntity role, Long userId) {
        userRepository.findById(userId);
        return null;
    }


    private boolean isEmail(final String email) {
        return email.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
