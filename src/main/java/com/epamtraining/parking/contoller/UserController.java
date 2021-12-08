package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.UserEntity;
import com.epamtraining.parking.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public UserEntity registration(@RequestBody UserEntity user) {

        return userService.createUser(user);


    }
}

