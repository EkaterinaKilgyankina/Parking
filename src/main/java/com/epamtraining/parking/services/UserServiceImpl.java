package com.epamtraining.parking.services;

import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.repository.impl.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }
}
