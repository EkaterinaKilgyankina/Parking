package com.epamtraining.parking.services.impl;

import com.epamtraining.parking.domain.entity.RoleEntity;
import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.model.ChangeRoleRequest;
import com.epamtraining.parking.model.UserRequest;
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

    @Override
    public UserEntity registerAdminAccount(UserEntity user) {
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
    public UserEntity registerNewUserAccount(UserEntity user) {

        String userEmail = user.getEmail();
        String password = user.getPassword();

        if (!password.equals(user.getMatchingPassword())){
            throw new RuntimeException("Passwords do not match.");
        }
        emailValidation(userEmail);
        if(password.length() < 5) {
            throw new RuntimeException("Password length must be more than five.");
        }
        UserEntity userNew = new UserEntity();
        userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        userNew.setEmail(user.getEmail());
        userNew.setRoles(Arrays.asList(roleRepository.findByName("role_user")));
        return userRepository.save(userNew);
    }

    @Override
    public String changeUserRole(ChangeRoleRequest role) {
        String email = role.getEmail();
        /*if(!emailExists(email)) {
            throw new RuntimeException("User not found");
        }*/
        UserEntity correctedUser = userRepository.findByEmail(email).orElseThrow();
        RoleEntity newRole = roleRepository.findByName(role.getRole());
        if(newRole == null) {
            throw new RuntimeException("Role doesn't exist.");
        }
        List<RoleEntity> newRoles = new ArrayList<>();
        newRoles.add(newRole);
        correctedUser.setRoles(newRoles);
        userRepository.save(correctedUser);

        return "success";
    }

    private boolean emailValidation(final String email) {
        if (email == null || email.length() == 0) {
            throw new RuntimeException("Field cannot be empty.");
        }
        if (!isEmail(email)) {
            throw new RuntimeException("It is not an email.");
        }
        if (emailExists(email)) {
            throw new RuntimeException("There is an account with that email address: " + email);
        }

        return true;
    }

    private boolean isEmail(final String email) {
        return email.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
    }

    private boolean emailExists(final String email) {
        return userRepository.getUserEntityByEmail(email) != null;
    }
}
