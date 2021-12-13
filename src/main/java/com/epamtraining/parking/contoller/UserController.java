package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.entity.UserEntity;
import com.epamtraining.parking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public UserEntity registration(@RequestBody UserEntity user) {

        return userService.createUser(user);

    }

    @GetMapping
    @RolesAllowed("role_admin")
    public List<UserEntity> getALlUsers() {

        return userService.getAllUsers();
    }

}

